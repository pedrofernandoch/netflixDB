package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection {

	private static Connection connection = null;

	public static boolean createConnection(String userName, String password, String hostName, String port, String SID) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			setConnection(DriverManager.getConnection("jdbc:oracle:thin:@"+hostName+":"+port+":"+SID, userName, password));
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
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
