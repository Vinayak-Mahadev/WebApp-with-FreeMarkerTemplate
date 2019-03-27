package properties;

import java.util.Properties;

public class PropertiesForDatabase {

	private static  PropertiesForDatabase propertiesForDatabase;
	private  PropertiesForDatabase() {
		
	}
	public Properties getDBProp() {
		
		Properties prop;
		prop  = new Properties();
		prop.setProperty("DB_DRIVER_CLASS", "org.postgresql.Driver");
		prop.setProperty("DB_URL", "jdbc:postgresql://localhost:5432/testdb");
		prop.setProperty("DB_USERNAME", "postgres");
		prop.setProperty("DB_PASSWORD", "postgres");
		
		return prop;
	}
	public static  PropertiesForDatabase getProperties() {
		if(propertiesForDatabase == null){
			propertiesForDatabase = new PropertiesForDatabase();
			propertiesForDatabase.getDBProp();
		}
		return propertiesForDatabase;
	}
	

}
