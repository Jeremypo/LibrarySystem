package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String URL = "jdbc:postgresql://localhost:5432/university_db";

	private static final String USER = "postgres";

	private static final String PWD = "123";

	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(ConnectionFactory.URL, ConnectionFactory.USER, ConnectionFactory.PWD);
		
	}	

}
