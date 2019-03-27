  package model.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import properties.PropertiesForDatabase;


public class DBConnection {


	private static final String info = "/*\r\n * THIS CLASS IS SINGLETON CLASS\r\n * \r\n * It means only one thread works for your Database operations\r\n * for entire application\r\n * You can use data base connection where ever in your application\r\n * \r\n * DB properties are taken by two way \r\n * \r\n * 1. 'src/config'  <------ from this location \r\n * \t\t\twhen you call DBConnection.getDB_class();\r\n * \r\n * note : config is file name\r\n * \r\n * 2.calling static method\r\n * DBConnection.setPropertyFileLocation_and_GetDB_Class(String config_file_location)\r\n * \r\n * Here config_file_location is your file path\r\n * \r\n * 1.DBConnection.getDB_class();\r\n * 2.DBConnection.setPropertyFileLocation_and_GetDB_Class(String config_file_location);\r\n * Call any one method to get this class object \r\n * that method will give this class object reference\r\n * \r\n * Add jdbc driver in your reference libraries and set db properties\r\n * \r\n * \r\n * DBConnection.GetDB_Class()\r\n * @author Vinayak Mahadev\r\n * email vinayakmahadev.nm@gmail.com\r\n * @version 1.0\r\n * \r\n * */";
	private static DBConnection dbc;
	public  Properties properties;
	private static Connection con;
	public  String config_file_location = "configuration/database/config.properties";
	private static int db_connection_count = 0;
	private  String username;
	private String password;
	private String url;
	private DBConnection()
	{
		properties = PropertiesForDatabase.getProperties().getDBProp();
	}

	public static synchronized DBConnection GetDB_Class() {
		if (dbc == null) {
			dbc = new DBConnection();
		}
		return dbc;
	}

	public synchronized Connection getDBConnection()
	{
		if (con == null) {
			try {
				System.out.println(info);
				url = properties.getProperty("DB_URL");
				username = properties.getProperty("DB_USERNAME");
				password = properties.getProperty("DB_PASSWORD");
				System.out.println(username+" "+password);
				Class.forName(properties.getProperty("DB_DRIVER_CLASS"));
				con = DriverManager.getConnection(url, username, password);
				System.out.println("DB Connection made.......");
			} catch (ClassNotFoundException e) {
				System.out.println("jdbc Driver not present in our project");
				e.printStackTrace();
				return null;
			} catch (Exception e) {
				System.out.println("Making Connection problem because wrong url,username,password");
				e.printStackTrace();
				return null;
			}
		}

		System.out.println("DB connection is given to you......" + ++db_connection_count + "  time");
		

		return con;
	}

	public String toString() {
		return "/*\r\n * THIS CLASS IS SINGLETON CLASS\r\n * \r\n * It means only one thread works for your Database operations\r\n * for entire application\r\n * You can use data base connection where ever in your application\r\n * \r\n * DB properties are taken by two way \r\n * \r\n * 1. 'src/config'  <------ from this location \r\n * \t\t\twhen you call DBConnection.getDB_class();\r\n * \r\n * note : config is file name\r\n * \r\n * 2.calling static method\r\n * DBConnection.setPropertyFileLocation_and_GetDB_Class(String config_file_location)\r\n * \r\n * Here config_file_location is your file path\r\n * \r\n * 1.DBConnection.getDB_class();\r\n * 2.DBConnection.setPropertyFileLocation_and_GetDB_Class(String config_file_location);\r\n * Call any one method to get this class object \r\n * that method will give this class object reference\r\n * \r\n * Add jdbc driver in your reference libraries and set db properties\r\n * \r\n * \r\n * DBConnection.GetDB_Class()\r\n * @author Vinayak Mahadev\r\n * email vinayakmahadev.nm@gmail.com\r\n * @version 1.0\r\n * \r\n * */";
	}
	public static void info() {
		System.out.println(info);
	}

	public void close() {
		
		DBConnection.con = null;
		properties       = null;
		DBConnection.dbc = null;
		DBConnection.db_connection_count = 0;


	}
}
