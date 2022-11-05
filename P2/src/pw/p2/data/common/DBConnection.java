package pw.p2.data.common;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * MySQL connection
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public class DBConnection {

	protected Connection connection = null;
	protected String url = "jdbc:mysql://oraclepr.uco.es:3306/i02mocaf";
	protected String user = "i02mocaf";
	protected String password = "pw2223";
	
	public DBConnection() {
		Properties prop = new Properties();
		try {		
			BufferedReader reader_configproperties = new BufferedReader(new FileReader(new File("config.properties.txt")));
			prop.load(reader_configproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		this.url = prop.getProperty("url");
		this.user = prop.getProperty("user");
		this.password = prop.getProperty("password");
	}
	public Connection getConnection(){

		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = (Connection) DriverManager.getConnection(url, user, password);
			//System.out.println("Database connection successfully opened!");
		} 
		catch (SQLException e) {
			System.err.println("Connection to MySQL has failed!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found.");
			e.printStackTrace();
		}
		return this.connection;
	}

	public void closeConnection() {
		try {
			if(this.connection != null && !this.connection.isClosed()) {
				this.connection.close();
				//System.out.println("Database connection successfully closed!");
			}
		} catch (SQLException e) {
			System.err.println("Error while trying to close the connection.");
			e.printStackTrace();
		}
	}
}
