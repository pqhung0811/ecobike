package controller.item;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import controller.home.HomeController;
import controller.pay.PayController;
import database.api.bike.BikeAPI;
import database.api.bike.BikeInterface;
import database.api.transaction.ValidTransactionAPI;
import database.api.transaction.ValidTransactionInterface;
import entities.bike.Bike;
import entities.rent.Rent;

public class ItemController {

	@FXML
	private ImageView img;
	@FXML
	private Label license_plate;
	@FXML
	private Label cost;
	@FXML
	private Button backBtn;
	@FXML
	private Label name;
	@FXML
	private AnchorPane paneItem;
	@FXML
	private Button rentbike;
	@FXML
	private Label type;
	@FXML
	private Label station;
	
//	private static int rentid=0;
	private Bike bikeRent;
//	public void setId(int i) {
//		this.custId = i;
//	}

	@FXML
	public void rentItem(MouseEvent event) {
        BikeInterface bikeInterface = new BikeAPI();
        ValidTransactionInterface checkInterface = new ValidTransactionAPI();
//		if (checkInterface.checkTransactFinish()) {
			// check bike rent or not befrore add
			if (bikeInterface.checkBikeRent(name.getText())) {
				JOptionPane.showMessageDialog(null, "On rent");
			} else {

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/controller/pay/Pay.fxml"));

				try {
					JOptionPane.showMessageDialog(null, "Add to rent");
					Parent root = loader.load();
					
					// access the controller and call a method
					// access the controller and call a method
					PayController pay = loader.getController();
					Rent rent = new Rent();
					
//					rentid++;
					int bikeId =  bikeInterface.getIdByName(name.getText());
//					rent.setId(rentid);
					Bike bike = bikeInterface.getBikeFromDB(name.getText());
					rent.setBike(bike);
			        pay.setOrder(rent);
	                pay.initData(rent);
					Stage stage = (Stage) (Stage) rentbike.getScene().getWindow();
					stage.setScene(new Scene(root));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//		} else {
//			JOptionPane.showMessageDialog(null, "Finish transact first");
//		}
	}

	// lan sau vao lai k con luu bike rent
	@FXML
	public void back(MouseEvent event) {
		// setBike(bikeRent);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/home/Home.fxml"));
		try {
			Parent root = loader.load();
			HomeController home = new HomeController();
			home = loader.getController();
			// load
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public void initItem(Bike bike) {
		img.setImage(bike.getUrlImage());
		name.setText(bike.getBike_name());
		type.setText(bike.getType());
		license_plate.setText(bike.getLicence_plate());
		cost.setText(Integer.toString(bike.getCost()));
		station.setText(Integer.toString(bike.getStationId()));
	}

}
