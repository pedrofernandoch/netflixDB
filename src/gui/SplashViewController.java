package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SplashViewController implements Initializable {

	private Scene mainScene;
	private Stage stage;
	//public static java.awt.TrayIcon trayIcon;

	@FXML
	private StackPane rootPane;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//javax.swing.SwingUtilities.invokeLater(this::addAppToTray);
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
							//mainScene.getStylesheets().add(getClass().getResource("MainViewStyleSheet.css").toString());
							stage = new Stage();
							//stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.png")));
							stage.setScene(mainScene);
							stage.setResizable(false);
							stage.setTitle("Zematec AACR");
							stage.show();
						} catch (IOException e) {
							e.printStackTrace();
							//EventLog.newEventLog(new Date(), "Sistema", LogActivities.ERROR, e.getMessage(),LogTypes.ERROR);
							//Alerts.showAlert("IOException", "Erro ao carregar a página principal", e.getMessage(),AlertType.ERROR);
						}
						rootPane.getScene().getWindow().hide();
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
				//EventLog.newEventLog(new Date(), "Sistema", LogActivities.ERROR, e.getMessage(), LogTypes.ERROR);
				//Alerts.showAlert("InterruptedException", "Erro ao carregar a página principal", e.getMessage(),AlertType.ERROR);
			}
		}

	}

	/*private void addAppToTray() {
		try {
			java.awt.Toolkit.getDefaultToolkit();
			if (!java.awt.SystemTray.isSupported()) {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Esse sistema operacional não suporta system tray");
				Platform.exit();
			}
			java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
			java.awt.Image img = ImageIO.read(new File(System.getProperty("user.dir") + "/src/resources/trayIcon.jpg"));
			trayIcon = new java.awt.TrayIcon(img);
			trayIcon.setImageAutoSize(true);

			trayIcon.addActionListener(event -> Platform.runLater(this::showStage));

			java.awt.MenuItem openItem = new java.awt.MenuItem("Open");
			openItem.addActionListener(event -> Platform.runLater(this::showStage));

			java.awt.Font defaultFont = java.awt.Font.decode(null);
			java.awt.Font boldFont = defaultFont.deriveFont(java.awt.Font.BOLD);
			openItem.setFont(boldFont);

			java.awt.MenuItem exitItem = new java.awt.MenuItem("Exit");
			exitItem.addActionListener(event -> {				
				Platform.exit();
				tray.remove(trayIcon);
			});

			final java.awt.PopupMenu popup = new java.awt.PopupMenu();
			popup.add(openItem);
			popup.addSeparator();
			popup.add(exitItem);
			trayIcon.setPopupMenu(popup);

			tray.add(trayIcon);
		} catch (java.awt.AWTException | IOException e) {
			System.out.println("Unable to init system tray");
			e.printStackTrace();
		}
	}

	private void showStage() {
		if (stage != null) {
			stage.show();
			stage.toFront();
		}
	}*/

}