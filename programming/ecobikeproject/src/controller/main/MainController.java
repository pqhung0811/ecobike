package controller.main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import controller.history.HistoryController;
import controller.home.HomeController;
import controller.order.OrderController;
import controller.order.OrderListController;
import database.api.rent.RentAPI;
import database.api.rent.RentInterface;
import database.api.transaction.ValidTransactionAPI;
import database.api.transaction.ValidTransactionInterface;
import entities.rent.Rent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private Button btnHistory;;
	@FXML
	private Button btnReturn;
	@FXML
	private Button btnSearch;

//	public void setId(int i) {
//		this.custId = i;
//	}

	@FXML
	void loadHistory(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/history/History.fxml"));
		try {
			Parent root = loader.load();
			HistoryController his = loader.getController();
//			his.setId(custId);
			his.initHistory();
			Stage stage = (Stage) (Stage) btnHistory.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void loadSearch(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/home/Home.fxml"));
		try {
			Parent root = loader.load();
//			HomeController home =  loader.getController();
//			home = loader.getController();
//			home.setId(custId);
			Stage stage = (Stage) (Stage) btnSearch.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void loadReturn(ActionEvent event) {
		ValidTransactionInterface validTransactionInterface = new ValidTransactionAPI();
           
		if (validTransactionInterface.checkTransactFinish()) {

			JOptionPane.showMessageDialog(null, "RENT FIRST");
		} else {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/controller/order/OrderList.fxml"));
			try {
				Parent root = loader.load();
				OrderListController or = loader.getController();
//				RentInterface rentInterface = new RentAPI();
//				Rent rent = rentInterface.getRent();
//				order.setCustId(custId);
//				or.setRent(rent);
//				or.initPane(rent);
				Stage stage = (Stage) (Stage) btnReturn.getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
    
	public void initButton() {
		ValidTransactionInterface validTransactionInterface = new ValidTransactionAPI();
			if (validTransactionInterface.checkTransactFinish()) {
			   btnReturn.setVisible(false);
			}else  	btnReturn.setVisible(true);
	}
	


}
