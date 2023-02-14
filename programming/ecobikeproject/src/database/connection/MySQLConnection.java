package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection implements DatabaseType {
	 public Connection getConnection() throws SQLException, ClassNotFoundException {
		    String hostName = "localhost";
		    String dbName = "ecobikeproject";
		    String userName = "root";
		    String password = "12345678";

		    return getConnection(hostName, dbName, userName, password);
	 }

	 public Connection getConnection(String hostName, String dbName, String userName, String password) throws SQLException, ClassNotFoundException {

		    String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

		    Connection connection = DriverManager.getConnection(connectionURL, userName, password);
		    return connection;
     }
}