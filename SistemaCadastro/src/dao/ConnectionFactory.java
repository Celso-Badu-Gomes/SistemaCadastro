package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static final String URL = "jdbc:sqlite:resources/produto.db";

	public static Connection conectar() throws Exception {
		Class.forName("org.sqlite.JDBC");
		
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(URL);
		}
		return connection;
	}

	private static Connection connection = null;

	public static void main(String[] args) {
		try {
			System.out.println("Conectado com sucesso!!");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}
