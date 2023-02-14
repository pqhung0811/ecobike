package controller.bike;

import java.io.File;
import java.io.IOException;

import controller.item.ItemController;
import database.api.bike.BikeAPI;
import database.api.bike.BikeInterface;
import entities.bike.Bike;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import database.api.bike.BikeAPI;
import database.api.bike.BikeInterface;
import entities.bike.Bike;
import entities.rent.Rent;

public class BikeCatController {

	@FXML
	private ImageView bikeImg;

	@FXML
	private Text bikeText;

	@FXML
	private Text status;

//	public void setCustId(int custId) {
//		this.custId = custId;
//	}

	public void initBikePane(String sta, String bikeName, Image image) {
		status.setText(sta);
		bikeText.setText(bikeName);
		bikeImg.setImage(image);
	}

	@FXML
	void loadDetail(MouseEvent event) {
		BikeInterface bikeInterface = new BikeAPI();
		Bike bike = bikeInterface.getBikeFromDB(bikeText.getText()); // get selected

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/item/Item.fxml"));
		try {

			Parent root = loader.load();
			ItemController itemController = loader.getController();
			itemController.initItem(bike);
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}