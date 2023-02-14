package controller.pay;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import controller.home.HomeController;
import database.api.bike.BikeAPI;
import database.api.bike.BikeInterface;
import entities.bike.Bike;
import entities.rent.Rent;
import utils.DateUtils;
public class PayController {

	@FXML
	private ImageView img;
	@FXML
	private Button rent;

	@FXML
	private Pane payPane;

	@FXML
	private Label remove;

	@FXML
	private Label excessCash;

	@FXML
	private Label total;

	@FXML
	private Label deposit;

	@FXML
	private Text bikeName;

	@FXML
	private Label time;

	@FXML
	private Label rentFee;

	@FXML
	private Label cost;
	@FXML
	private CheckBox cash;

	@FXML
	private CheckBox credit;

	@FXML
	private Label total1;
	private Rent rent1;

	public void setOrder(Rent rent2) {
		this.rent1 = rent2;

	}

	@FXML
	public void back(MouseEvent event) {
		// back to shop
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/home/Home.fxml"));
		try {
			Parent root = loader.load();
			HomeController shop = loader.getController();
//			shop.setId(rent1.getCustId());

			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void rentItem(MouseEvent event) {
		// check select pay method
		if (credit.isSelected() || cash.isSelected()) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/controller/pay/BankGate.fxml"));

			try {
				Parent root = loader.load();

				BankGateController controller = loader.getController();
				controller.setRent(rent1);
				// load
				Stage stage = (Stage) rent.getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Pick payment method first");
		}
	}

	public void initData(Rent rent) {
//		BikeInterface bikeInterface = new BikeAPI();
		
//		Bike bike = bikeInterface.getBikeById(Integer.toString(rent.getBike().getId()));
		Bike bike = rent.getBike();
		bikeName.setText(bike.getBike_name());
		deposit.setText(DateUtils.formatter.format(bike.getdeposit()));
		rentFee.setText(DateUtils.formatter.format(rent.getTotal()));
		cost.setText(Integer.toString(bike.getCost()));
		img.setImage(bike.getUrlImage());
		
		if (rent.getId() != 0) {
		
			excessCash.setText(DateUtils.formatter.format(bike.getdeposit()));
			time.setText(DateUtils.date(rent.getTime()));
			double a = rent.getTotal();
			total.setText(DateUtils.formatter.format(a));
			total1.setText(DateUtils.formatter.format(a));

		} else {
			total.setText(DateUtils.formatter.format(bike.getdeposit()));
			total1.setText(DateUtils.formatter.format(bike.getdeposit()));
		}
		
		// load pane
		payPane.toFront();

	}

}
