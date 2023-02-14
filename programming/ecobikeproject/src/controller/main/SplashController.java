package controller.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SplashController implements Initializable {


	@FXML
	private Pane pane;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		new ShowSplashScreen().start();
	}

	class ShowSplashScreen extends Thread {

		@Override
		public void run() {
			try {
				Thread.sleep(1000);

				Platform.runLater(() -> {
					Stage stage = new Stage();
					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource("/controller/main/Main.fxml"));
					} catch (IOException ex) {
						Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
					}
					Scene scene = new Scene(root);
					Image icon = new Image("view/images/icon.png");
					stage.getIcons().add(icon);
					stage.setTitle("Ecobike");
					stage.setScene(scene);
					stage.show();
					pane.getScene().getWindow().hide();
				});
			} catch (InterruptedException ex) {
				Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}
}
