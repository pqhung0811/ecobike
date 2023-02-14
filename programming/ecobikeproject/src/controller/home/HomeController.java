package controller.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.item.ItemController;
import database.api.bike.BikeAPI;
import database.api.bike.BikeInterface;
import database.api.station.StationAPI;
import database.api.station.StationInterface;
import entities.bike.Bike;
import entities.station.Station;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import controller.main.MainController;
import javafx.scene.control.Button;

public class HomeController implements Initializable {
	
	@FXML
	private TableColumn<Station, String> stationName;
	@FXML
	private TableColumn<Station, String> stationAddress;
	@FXML
	private TableColumn<Station, Double> stationArea;
	@FXML
	private TableColumn<Station, Integer> bikeCol;
	@FXML
	private TableColumn<Station, Integer> eBikeCol;
	@FXML
	private TableColumn<Station, Integer> twinBikeCol;
	@FXML
	private TableColumn<Station, Integer> emptyDockCol;
	@FXML
	private TableView<Station> storeTable;
	@FXML 
	private TableColumn<Bike, String> bikeName;
	@FXML
	private TableColumn<Bike, String> bikeType;
	@FXML
	private TableColumn<Bike, String> licence_plate;
	@FXML 
	private TableColumn<Bike, Integer> status;
//	@FXML 
//	private TableColumn<Bike, String> barcode;
	@FXML
	private Pane searchPane;
	@FXML 
	private TableColumn<Bike, Integer> bikeCost;
	@FXML 
	private TableView<Bike> bikeTable;
	@FXML
	private Pane overviewPane;
	@FXML 
	private TextField searchField;
	@FXML
	private Button btnBack;
	private BikeInterface bikeInterface = new BikeAPI();

	public void initialize(URL location, ResourceBundle resources) {
		stationName.setCellValueFactory(new PropertyValueFactory<>("stationName"));
		stationAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		stationArea.setCellValueFactory(new PropertyValueFactory<>("area"));
		bikeCol.setCellValueFactory(new PropertyValueFactory<>("bike"));
		eBikeCol.setCellValueFactory(new PropertyValueFactory<>("electricBike"));
		twinBikeCol.setCellValueFactory(new PropertyValueFactory<>("twinBike"));
		emptyDockCol.setCellValueFactory(new PropertyValueFactory<>("emptyDock")); // empty dock
		StationInterface stationInterface = new StationAPI();

		overviewPane.toFront();
	
		bikeName.setCellValueFactory(new PropertyValueFactory<>("bike_name")); // map with type in class BikeType
		bikeType.setCellValueFactory(new PropertyValueFactory<>("type"));
		licence_plate.setCellValueFactory(new PropertyValueFactory<>("licence_plate"));
		status.setCellValueFactory(new PropertyValueFactory<>("status"));
		bikeCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
	
	
		
		ObservableList<Station> dataList = stationInterface.getAllStations();
		// Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Station> filteredData = new FilteredList<>(dataList, b -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(station -> {
				// If filter text is empty, display all persons.

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (station.getStationName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} else if (station.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}

				else
					return false; // Does not match.
			});
		});

		// 3. Wrap the FilteredList in a SortedList.
		SortedList<Station> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		// Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(storeTable.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		storeTable.setItems(sortedData);
		storeTable.toFront();
	}
	
	@FXML
	void chooseRow() {
		ObservableList<Bike> dataList = bikeInterface.getListFromDB(storeTable.getSelectionModel().getSelectedItem().getStationName());
		bikeTable.setItems(dataList);

		// Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Bike> filteredData = new FilteredList<>(dataList, b -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(bike -> {
				// If filter text is empty, display all persons.

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (bike.getBike_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				}

				else
					return false; // Does not match.
			});
		});
          
		// 3. Wrap the FilteredList in a SortedList.
		SortedList<Bike> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		// Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(bikeTable.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		bikeTable.setItems(sortedData);
		bikeTable.toFront();
	}

	@FXML
	void back(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/main/Main.fxml"));
		try {
			Parent root = loader.load();
			controller.main.MainController home = new MainController();
			home = loader.getController();
			home.initButton();
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// click bike load detail of that bike
	@FXML
	void chooseBike(MouseEvent event) {

		Bike bike = bikeInterface.getBikeFromDB(bikeTable.getSelectionModel().getSelectedItem().getBike_name());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/item/Item.fxml"));
		try {

			Parent root = loader.load();
			ItemController controller = loader.getController();
			controller.initItem(bike);
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
