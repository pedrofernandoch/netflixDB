package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import gui.util.Alerts;
import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;

public class OracleConnection {

	private static Connection connection = null;

	public static boolean createConnection(String userName, String password, String hostName, String port, String SID) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			setConnection(DriverManager.getConnection("jdbc:oracle:thin:@"+hostName+":"+port+":"+SID, userName, password));
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			Platform.runLater(new Runnable() {
			    @Override public void run() {
			    	Alerts.showAlert("Exception", "Erro ao tentar conectar", e.getMessage(), AlertType.ERROR);
			}});
			return false;
		}
	}

	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		OracleConnection.connection = connection;
	}
}
