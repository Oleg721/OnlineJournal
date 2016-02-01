package dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;

import connect.StatementDB;
import bom.Form;
import bom.Pupil;
import dao.FormDao;

public class FormDaoImpl implements FormDao{

	//static final Logger logger = Logger.getLogger(FormDaoImpl.class);
	
		@Override
		public List<Form> getAllForms() {
			
			String sql = "select * from class";
			List<Form> result = new ArrayList<Form>();
			try {
				Statement statement = StatementDB.getInstance().statement;
				ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
					Form form = new Form();
					form.setId(resultSet.getInt("class.id"));
					form.setName(resultSet.getString("class.name"));
					result.add(form);
				}
				resultSet.close();
			} catch (SQLException e) {
				//logger.error("Error in getAllForms",e);
				e.printStackTrace();
			} 
			return result;
		}

		
		@Override
		public Integer insertForms(String form) {

			String sqlIn = "insert into class(name)  SELECT * FROM (select '"+form+"' ) AS tmp WHERE NOT EXISTS ("
				  +  "SELECT name FROM class WHERE name = '"+form+"' );" ;
			String sqlOut = "select id from class Where name = '"+form+"'";
			Integer result = -1;
			try {
				Statement statement =  StatementDB.getInstance().statement;
				statement.executeUpdate(sqlIn);			
				ResultSet resultSet = statement.executeQuery(sqlOut);
				resultSet.next();
				result = resultSet.getInt("id");
			} catch (SQLException e) {
				//logger.error("Error in insertForms",e);
			}
			return result; 
		}
		

		@Override
		public void insertForms(Form form) {
			String sqlIn = "insert into class(name)  SELECT * FROM (select '"+form.getName()+"' ) AS tmp WHERE NOT EXISTS ("
					  +  "SELECT name FROM class WHERE name = '"+form.getName()+"' );" ;
			try {
				Statement statement =  StatementDB.getInstance().statement;
				statement.executeUpdate(sqlIn);
			} catch (SQLException e) {
				//logger.error("Error in insertForms",e);
				e.printStackTrace();
			} 
		}	
}
