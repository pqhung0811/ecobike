package controller.pay;

import java.io.IOException;

import controller.main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ResultController {

	@FXML
	private Button homeBtn;
	@FXML
	private Label money;
	@FXML
	private TextArea msg;
	@FXML
	private Label time;
	
//	public void setCustId(int custId) {
//		this.custId = custId;
//	}

	@FXML
	void backHome(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/controller/main/Main.fxml"));
		try {
			Parent root = loader.load();
			MainController shop = loader.getController();
   
			shop.initButton();
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initResultPane(String m, String message, String t) {
		money.setText(m);
		msg.setText(message);
		time.setText(t);
	}

}
