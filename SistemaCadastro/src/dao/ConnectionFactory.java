package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static final String URL = "jdbc:sqlite:resources/produto.db";
	//private static Connection connection;

	public static Connection conectar() throws Exception {
		Class.forName("org.sqlite.JDBC");
		
		/*if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(URL);
		}*/
		return DriverManager.getConnection(URL);
		
	}

}



