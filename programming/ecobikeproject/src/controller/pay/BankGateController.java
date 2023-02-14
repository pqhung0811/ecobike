package controller.pay;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import database.api.bike.BikeAPI;
import database.api.bike.BikeInterface;
import database.api.rent.RentAPI;
import database.api.rent.RentInterface;
import database.api.station.StationAPI;
import database.api.station.StationInterface;
import database.api.transaction.TransactionAPI;
import database.api.transaction.TransactionInterface;
import database.api.transaction.ValidTransactionAPI;
import database.api.transaction.ValidTransactionInterface;
import entities.bike.Bike;
import entities.card.Card;
import entities.card.PayByCard;
import entities.card.PayService;
import entities.rent.Rent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.DateUtils;

public class BankGateController implements Initializable {

	@FXML
	private Button payBtn;
	@FXML
	private Button backButton;
	@FXML
	private TextArea messageA;
	@FXML
	private TextField bank;
	@FXML
	private TextField cardNum;
	@FXML
	private DatePicker date;
	@FXML
	private TextField name;
	private Rent rent; // need to pass order from
	private String formatDateTime;
    private	Bike bike;
    private static int tmp=0;
	public void setRent(Rent rent1) {
		this.rent = rent1;

	}
     
	// return to pay scene
	@FXML
	public void backPay(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/pay/Pay.fxml"));
		try {
			Parent root = loader.load();
			
			PayController controller= loader.getController();
			controller.setOrder(rent);
			controller.initData(rent);
	
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// handle pay update balance
	@FXML
	public void pay() {
		tmp++;
		boolean isSuccess = false;
		if(validateCard()) {
			 bike = rent.getBike();
			 
			// get input
               
			Card card = new Card(tmp, bank.getText(), name.getText(), cardNum.getText().replaceAll("\\s", ""), date.getValue());
			PayService payService = new PayService(new PayByCard(card));// can luu the vao bang transaction
			formatDateTime = LocalDateTime.now().format(DateUtils.format);
                 
			if (rent.getId() == 0) {
				// thread save transaction - function // to insert and update bike Table
				isSuccess = payService.pay(bike.getdeposit());  // rent
				if (isSuccess) {
					rent.setTimeCreate(formatDateTime);
					threadRent(bike.getId());
					//threadEmail(bike.getDeposit());

				}
			} else if (rent.getId() != 0) {
				isSuccess = payService.pay(bike.getdeposit(), rent.getTotal());
				if (isSuccess) {
					threadReturn();  
					//threadEmail(order.getTotal());
				}
			}
			
			if (isSuccess) {
				System.out.print(card.getBalance());
				createResultPane();
			} else
				JOptionPane.showMessageDialog(null, "Not enough money");
		
		}
	}
 
	public void createResultPane() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/pay/Result.fxml"));
		try {
			Parent root = loader.load();
			ResultController result = loader.getController();
			
            String money = null;
			if (rent.getTimeFinish()==null) {
				money = DateUtils.formatter.format(bike.getdeposit());
			} else {
				money = DateUtils.formatter.format(rent.getTotal());
			}
			
			result.initResultPane(money,messageA.getText() ,formatDateTime);
			Stage stage = (Stage) payBtn.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		date.setEditable(false);
	}
	
	public void threadRent(int bikeId) {
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				TransactionInterface tranInterface = new TransactionAPI();
		        RentInterface rentInterface = new RentAPI();
		        BikeInterface bikeInterface = new BikeAPI();
		        Bike bike = bikeInterface.getBikeById(Integer.toString(bikeId));
		        tranInterface.saveTransacToDB(rent);
				int rentId = rentInterface.getRentId(bikeId);
				rent.setId(rentId);
				rent.setBike(bike);
				tranInterface.saveTransaction(tmp, messageA.getText(),bike.getdeposit(),
						cardNum.getText().replaceAll("\\s", ""));
			}

		});
		t2.start(); // end thread 2
		// tinh tien phan rent
	}
   
	public void threadReturn() {
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
			    TransactionInterface transactionInterface = new TransactionAPI();
			    StationInterface stationInterface = new StationAPI();
				transactionInterface.saveTransaction(rent.getId(), messageA.getText(), rent.getTotal(),
						cardNum.getText().replaceAll("\\s", ""));

				/* TO DO IMPLEMENT ORDER.GETTOTAL */
				transactionInterface.updateReturn(rent.getId(), bike.getId(), rent.getTotal(),
						rent.getTimeFinish(), rent.getReturnId()); // order.get total thay cho 0 // them returnId vao
				/* TO DO IMPLEMENT pick store return o ORDER CONTROLLER */
				System.out.println("\n");
				System.out.println(Integer.valueOf(rent.getReturnId()));
				System.out.println("\n");
				System.out.println("a " + bike.getId() + " a");
				stationInterface.updateStoreReturn(bike.getId(), Integer.valueOf(rent.getReturnId())); 
	
			}
		});
		t2.start(); // end thread 2

	}

   public boolean validateCard() {
	   ValidTransactionInterface iCheck = new ValidTransactionAPI();
	   if (name.getText() == "" || cardNum.getText() == "" || bank.getText() == ""
				|| DateUtils.validateDate(date.getValue()) 
				|| cardNum.getText().replaceAll("\\s", "").length() != 16
				|| !cardNum.getText().replaceAll("\\s", "").matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Enter full infomation and card number is 16 digit !");
		}
	   
	   else if (!iCheck.checkSameCard(cardNum.getText().replaceAll("\\s", ""),Integer.toString(rent.getId()))) { // check same card
			JOptionPane.showMessageDialog(null, "You need to same card as first transaction");
		} 
	   
	   else if (iCheck.checkCardUsed(cardNum.getText().replaceAll("\\s", ""),Integer.toString(rent.getId()))) {// check cardUsed
			JOptionPane.showMessageDialog(null, "Card in used");
	   } else return true;
	   
	  return false;
   }
}
