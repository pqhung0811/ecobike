package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import database.connection.Context;
import database.connection.MySQLConnection;

public class Main extends Application {

	public Main() {

		Context.selectDataBase(new MySQLConnection());
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("/controller/main/Splash.fxml"));
		Scene scene = new Scene(root);
		Image icon = new Image("view/images/icon.png");
		stage.getIcons().add(icon);
		stage.setTitle("Ecobike");
		stage.setScene(scene);
		stage.show();
	}

}
