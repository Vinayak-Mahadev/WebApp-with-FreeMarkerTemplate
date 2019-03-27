package model.org;

import java.sql.Connection;

import dom.pojo.org.User;
import model.dbConnection.DBConnection;
import msg.Messages;

public class LoginModel {
	private Connection  con;
	public Messages validate(User user) {
		Messages msg = null;
		DBConnection db = null; 
		try {
			db = DBConnection.GetDB_Class();
			try {
				db.properties.setProperty("DB_USERNAME", user.getUsername());
				db.properties.setProperty("DB_PASSWORD", user.getPassword());
				con = db.getDBConnection();
				if (con != null) {
					msg = new Messages();
					msg.setReson("Authentication");
					msg.setSuccessmsg("Success..");
					db.close();
					return msg;
				}

				msg = new Messages();
				msg.setReson("Authentication");
				msg.setFailmsg("Failed..");
				return msg;

			} catch (Exception e) {
				e.printStackTrace();

				msg = new Messages();
				msg.setReson("Authentication");
				msg.setFailmsg("Failed..");
				return msg;

			}
		} 
		finally {
			db.close();
		}
	}
}
