package app;

import java.sql.SQLException;

import controller.Controller;
import db_config.DBConnection;

public class Starter {

	public static void main(String[] args) {
		DBConnection dbConnection = null;
		try {
			
			dbConnection = DBConnection.getInstance();
			Controller c = new Controller(dbConnection);
			c.setDaos("postgresql");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
	}

}
