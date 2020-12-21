package application;
	
import java.sql.SQLException;

import db.OracleConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/gui/SplashView.fxml"));
			Scene scene = new Scene(parent);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		try {
			if(OracleConnection.getConnection() != null && OracleConnection.getConnection().isClosed() != false)OracleConnection.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
