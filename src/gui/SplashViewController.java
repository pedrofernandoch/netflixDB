package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SplashViewController implements Initializable {

	private Scene mainScene; 
	@FXML
	private StackPane rootPane;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		new SplashScreen().start();
	}

	class SplashScreen extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(1500); // Wait 2 seconds
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						ScrollPane root = null;
						try { // Load MainView page
							root = FXMLLoader.load(getClass().getResource("/gui/MainView.fxml"));
							root.setFitToWidth(true);
							root.setFitToHeight(true);
							mainScene = new Scene(root);
							mainScene.getStylesheets().add(getClass().getResource("MainViewStyleSheet.css").toString());
							Stage stage = new Stage();
							stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/netflixIcon.png")));
							stage.setScene(mainScene);
							stage.setResizable(false);
							stage.setTitle("Netflix DB");
							stage.show();
						} catch (IOException e) {
							Alerts.showAlert("IOException", "Erro ao carregar a página principal", e.getMessage(),AlertType.ERROR);
						}
						rootPane.getScene().getWindow().hide();
					}
				});
			} catch (InterruptedException e) {
				Alerts.showAlert("InterruptedException", "Erro ao carregar a página principal", e.getMessage(),AlertType.ERROR);
			}
		}

	}

}