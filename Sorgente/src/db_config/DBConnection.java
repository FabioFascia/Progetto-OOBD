package db_config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static DBConnection instance = null;
	private Connection connection;
	private String DBMS = "postgresql";
	private String USERNAME = "adminprogetto";
	private String PASSWORD = "ProgettoConteFasciaTraccia2";
	private String IP = "localhost";
	private String PORT = "5432";
	private String url = "jdbc:" + DBMS + "://" + IP + ":" + PORT + "/" + "DBProgettoOOBD";
	
	private DBConnection() throws SQLException {
		
		try {
			
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
		}
		catch (SQLException e) {
			
			System.out.println("Database Connection creation failed : " + e.getMessage());
		}
	}
	
	public Connection getConnection() {
		
		return connection;
	}

	public static DBConnection getInstance() throws SQLException {
		
		if (instance == null)
			instance = new DBConnection();
		else
			if (instance.getConnection().isClosed())
				instance = new DBConnection();
		
		return instance;
	}

}
