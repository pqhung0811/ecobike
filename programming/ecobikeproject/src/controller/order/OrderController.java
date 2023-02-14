package controller.order;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import controller.main.MainController;
import controller.pay.PayController;
import database.api.bike.BikeAPI;
import database.api.bike.BikeInterface;
import database.api.station.StationAPI;
import database.api.station.StationInterface;
import entities.bike.Bike;
import entities.rent.Rent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.DateUtils;

public class OrderController implements Initializable {


	@FXML
	private Button returnBtn;
	@FXML
	private ImageView bikeImg;
	@FXML
	private Text bikeName;
	@FXML
	private Label deposit;
	@FXML
	private Label orderCode;
	@FXML
	private Label deposit1;
	@FXML
	private Label time;

	@FXML
	private Label totalTime;
	@FXML
	private Label total;
	private Rent rent;
	@FXML
	private ChoiceBox<String> choice = new ChoiceBox<String>();
	
	public void setRent(Rent rent1) {
		this.rent = rent1;
	}

	@FXML
	void returnBike(MouseEvent event) {
		// change detail
		String storeReturn = (String) choice.getValue();
		if(storeReturn == null) {
			JOptionPane.showMessageDialog(null, "Choose store to return");
		}else {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/pay/Pay.fxml"));
		try {
			Parent root = loader.load();
			PayController pay = loader.getController();
		
			StationInterface stationInterface = new StationAPI();

			rent.setReturnId(stationInterface.getStoreId(storeReturn)); 
			LocalDateTime dateTime = LocalDateTime.now();
			rent.getTotalTime(dateTime); // tinh tong thoi gian thanh toan
			rent.setTimeFinish(dateTime.format(DateUtils.format)); // set String right format // time finish
			pay.setOrder(rent);
			
			Bike bike = rent.getBike();
			pay.initData(rent);

			Stage stage = (Stage) (Stage) returnBtn.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	@FXML
	void back(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/main/Main.fxml"));
		try {
			Parent root = loader.load();
			MainController home = new MainController();
			home = loader.getController();
//			home.setId(rent.getCustId());
			home.initButton();
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initPane(Rent rent) {
		Bike bike = rent.getBike();
		LocalDateTime s = LocalDateTime.now();
		s.format(DateUtils.format);
		
		orderCode.setText(Integer.toString(rent.getId()));
		totalTime.setText(rent.getTotalTime(s));
		time.setText(rent.getTimeCreate());
		deposit.setText(DateUtils.formatter.format(bike.getdeposit()));
		total.setText(DateUtils.formatter.format(rent.getTotal()));
		bikeName.setText(bike.getBike_name());
		bikeImg.setImage(bike.getUrlImage()); // getImage
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		StationInterface stationInterface = new StationAPI();
		// chi tra ve store co the nhan xe
		for (String s : stationInterface.getStoreAvai()) {
			choice.getItems().add(s);
		}
	}
	

    @FXML
    void refresh(MouseEvent event) {
    	LocalDateTime s = LocalDateTime.now();
		s.format(DateUtils.format);
		totalTime.setText(rent.getTotalTime(s));
		total.setText(DateUtils.formatter.format(rent.getTotal()));
    }

}
