package controller.history;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import controller.main.MainController;
import database.api.history.HistoryAPI;
import database.api.history.HistoryInterface;
import entities.history.ItemHistory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.DateUtils;

public class HistoryController {

	@FXML
	private FlowPane rentFlow;
	@FXML
	private FlowPane returnFlow;
	@FXML
	private Button btnBack;
	
	private HistoryInterface history = new HistoryAPI(); 
	
	private int custId;
	public void setId(int i) {
		this.custId = i;
	}

	public void init(FlowPane pane, ArrayList<ItemHistory> list) {
		for (ItemHistory i : list) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/controller/history/RentHistory.fxml"));
			try {
				File file = new File("src/view/images/" + i.getBikeName() + ".jpeg");
				Image image = new Image(file.toURI().toString());
				// add bike info to pane
				Pane view = loader.load();
				RentHistoryController storeP = loader.getController();
				storeP.initItem(i.getBikeName(), i.getTime(), DateUtils.formatter.format(i.getMoney()), image);
				pane.getChildren().add(view);
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
		
			home.initButton();
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initHistory() {
		init(rentFlow, history.getRentHistory()); // init rent his
		init(returnFlow, history.getReturnHistory()); // init return his
	}

}
