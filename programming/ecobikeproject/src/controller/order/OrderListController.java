package controller.order;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import controller.main.MainController;
import database.api.rent.RentAPI;
import database.api.rent.RentInterface;
import database.api.station.StationAPI;
import database.api.station.StationInterface;
import entities.bike.Bike;
import entities.rent.Rent;
import entities.rent.RentInfo;
import entities.station.Station;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;

public class OrderListController implements Initializable {
	@FXML
	private Pane overviewPane;
	@FXML
	private Pane searchPane;
	@FXML
	private Button btnSearch;
	@FXML
	private TextField searchField;
	@FXML
	private Button btnBack;
	@FXML
	private TableView<RentInfo> rentTable;
	@FXML
	private TableColumn<RentInfo, Integer> orderID;
	@FXML
	private TableColumn<RentInfo, String> timecreate;
	@FXML
	private TableColumn<RentInfo, String> bikename;
	@FXML
	private TableColumn<RentInfo, String> biketype;
	@FXML
	private TableColumn<RentInfo, String> stationname;
	@FXML
	private TableColumn<RentInfo, String> address;

	@FXML
	public void back(ActionEvent event) {
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderID.setCellValueFactory(new PropertyValueFactory<>("idInteger"));
		timecreate.setCellValueFactory(new PropertyValueFactory<>("timecreateString"));
		bikename.setCellValueFactory(new PropertyValueFactory<>("bikenameString"));
		biketype.setCellValueFactory(new PropertyValueFactory<>("typeString"));
		stationname.setCellValueFactory(new PropertyValueFactory<>("stationnameString"));
		address.setCellValueFactory(new PropertyValueFactory<>("addressString"));
		RentInterface rentInterface = new RentAPI();

		overviewPane.toFront();
		ObservableList<RentInfo> rentInfos = rentInterface.getAllRents();
		
		FilteredList<RentInfo> filteredData = new FilteredList<>(rentInfos, b -> true);		
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(rentinfo -> {
				// If filter text is empty, display all persons.

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (rentinfo.getStationnameString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} else if (rentinfo.getAddressString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}

				else
					return false; // Does not match.
			});
		});
		
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(rentinfo -> {
				// If filter text is empty, display all persons.

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (rentinfo.getBikenameString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				}

				else
					return false; // Does not match.
			});
		});
		
		SortedList<RentInfo> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		// Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(rentTable.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		rentTable.setItems(sortedData);
		rentTable.toFront();
	}
		
	@FXML
	public void chooseRow(MouseEvent event) {
		RentInterface rentInterface = new RentAPI();
		Rent rent = rentInterface.getRentbyID(rentTable.getSelectionModel().getSelectedItem().getIdInteger());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/order/Order.fxml"));
		try {
			Parent root = loader.load();
			OrderController or = loader.getController();
//			RentInterface rentInterface = new RentAPI();
//			Rent rent = rentInterface.getRent();
//			order.setCustId(custId);
			or.setRent(rent);
			or.initPane(rent);
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
