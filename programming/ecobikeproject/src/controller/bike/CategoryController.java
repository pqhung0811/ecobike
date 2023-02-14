package controller.bike;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputFilter.Status;
import java.net.URL;
import java.util.ResourceBundle;

import controller.home.HomeController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import entities.bike.Bike;
import database.api.bike.BikeInterface;
import database.api.bike.BikeAPI;
import entities.station.Station;
import database.api.station.StationAPI;
import database.api.station.StationInterface;
import entities.rent.Rent;

public class CategoryController {

	@FXML
	private FlowPane itemFlow;
	@FXML
	private Label storeText;
	@FXML
	private Pane pane11;

//	public int getCustId() {
//		return custId;
//	}
//
//	public void setCustId(int custId) {
//		this.custId = custId;
//	}
    public void setStoreName(String name) {
    	storeText.setText(name);
    }
    
	public void initBike(ObservableList<Bike> bikeList) {
		for (Bike s : bikeList) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/controller/bike/BikeCat.fxml"));
			try {
				File file = new File("view/image/" + s.getBike_name() + ".jpeg");
				Image image = new Image(file.toURI().toString());
				// add bike info to pane
				Pane view = loader.load();
				BikeCatController storeP = loader.getController();
//				storeP.setCustId(custId);
				String status;
//				if(s.isRenting()) status = "renting";
//				else status = "norenting";
				storeP.initBikePane(s.getStatus(), s.getBike_name(), image);
				itemFlow.getChildren().add(view);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@FXML
	public void back(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/home/Home.fxml"));
		try {
			Parent root = loader.load();
			HomeController home = new HomeController();
			home = loader.getController();
//			home.setId(custId);
			// load
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
