package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionFactory {

	private static final String URL = "jdbc:sqlite:resources/produto.db?busy_timeout=5000";
	// private static Connection connection;

	public static Connection conectar() throws Exception {

		Class.forName("org.sqlite.JDBC");

		Connection con = DriverManager.getConnection(URL);

		try (Statement stmt = con.createStatement()) {
			stmt.execute("PRAGMA journal_mode=WAL;");
			stmt.execute("PRAGMA busy_timeout=5000;");

			return con;
		}

	}

}
