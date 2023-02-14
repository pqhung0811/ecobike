package controller;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Controller implements Initializable {
	@FXML
	private AnchorPane pane2;
	@FXML
	private Button he;
	@FXML
	private Button hi;
	@FXML
	private ImageView exit;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pane2.setTranslateX(-160);
		he.setVisible(true);
		hi.setVisible(true);
	}

	@FXML
	void run(MouseEvent event) {
		TranslateTransition slide = new TranslateTransition();
		slide.setDuration(Duration.seconds(0.4));
		slide.setNode(pane2);
		slide.setToX(-160);
		slide.play();

		pane2.setTranslateX(0);
		slide.setOnFinished((ActionEvent e) -> {
			he.setVisible(false);
			hi.setVisible(true);

		});
	}

	@FXML
	void run1(MouseEvent event) {
		TranslateTransition slide = new TranslateTransition();
		slide.setDuration(Duration.seconds(0.4));
		slide.setNode(pane2);

		slide.setToX(0);
		slide.play();

		pane2.setTranslateX(0);
		slide.setOnFinished((ActionEvent e) -> {
			he.setVisible(false);
			hi.setVisible(true);
		});
	}
}
