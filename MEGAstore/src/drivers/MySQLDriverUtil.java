package drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDriverUtil {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	public static final String DATABASE = "MEGAstore";

	public static void registreerDriver() throws ClassNotFoundException {
		Class.forName(DRIVER);
		System.out.println("Driver is geladen"); // didactische reden
	}

	public static Connection getConnection() throws SQLException {
		return getConnection(DATABASE);
	}

	public static Connection getConnection(String databank) throws SQLException {
		return DriverManager.getConnection(getConnectionString(databank), USER,
				PASSWORD);
	}

	public static Connection getConnectionMultipleResults() throws SQLException {
		return DriverManager.getConnection(
				getConnectionString(DATABASE, "allowMultiQueries=true"), USER,
				PASSWORD);
	}

	private static String getConnectionString(String databank, String... opties) {
		String result = URL + "/" + databank;
		if (opties != null && opties.length > 0) {
			result += "?" + opties[0];
			for (int i = 1; i < opties.length; i++) {
				result += "&" + opties[i];
			}
		}
		return result;
	}
}
