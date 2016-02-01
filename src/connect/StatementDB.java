package connect;

import java.sql.SQLException;
//import org.apache.log4j.Logger;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public final class StatementDB {
	
	//static final Logger logger = Logger.getLogger(StatementDB.class);
	
	private static StatementDB instance;
	
	public final Statement statement;
	
	private StatementDB() {
			statement = creatStatement();
		//	logger.info("Creation of statement is successful");
	};
	
	private Statement creatStatement() {
		Connection connection = (Connection) ConnectionDB.getInstance().connect;
			try {
				return (Statement) connection.createStatement();
			} catch (SQLException e) {
			//	logger.error("Error in createStatement",e);
				e.printStackTrace();
			}
			return null;
	}
	
	public synchronized static StatementDB getInstance(){
		if(instance == null)
			instance = new StatementDB();
		return instance;
	}
}
