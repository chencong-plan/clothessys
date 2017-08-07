package cc.ccoder.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	private static final String Driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/clothessys";
	private static final String user = "root";
	private static final String password = "123456";

	private static Connection connection = null;

	private DBUtils() {
		try {
			Class.forName(Driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (connection == null) {
			new DBUtils();
			return connection;
		}
		return connection;
	}
}
