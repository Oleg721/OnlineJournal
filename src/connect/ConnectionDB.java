package connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import org.apache.log4j.Logger;

public final class ConnectionDB {
		
		//static final Logger logger = Logger.getLogger(ConnectionDB.class);
	
		private static ConnectionDB instance;
		
		public final Connection connect;
				
		private ConnectionDB(){
			connect = getConnection();
		};
		
		private Connection getConnection() {
			loadJdbcDriver();
			return databaseConnection();	
			}
		
		public static ConnectionDB getInstance(){
			if(instance == null)
				instance = new ConnectionDB(); 
			return instance;
		}
		
		private void loadJdbcDriver(){
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
			//	logger.error("Load error",e);
				e.printStackTrace();
			}
		}
		
		private Connection databaseConnection(){
			Connection connection = null;
			try {
				connection = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/journal","root", "Udr7m3XewZ");
			} catch (SQLException e) {
				//logger.error("Connection Failed! Check output console",e);
				e.printStackTrace();
			}
			checkDatabaseConnection(connection);
			return connection;
		}
		
		private void checkDatabaseConnection(Connection connection){
			if (connection != null) {
				//logger.info("You made it, take !!!control your database now!");
			} else {
			//	logger.info("Failed to make connection!");
			}
		}

}
