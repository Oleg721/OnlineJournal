package dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
//import org.apache.log4j.Logger;
import connect.StatementDB;
import bom.Form;
import bom.Pupil;
import dao.PupilDao;

public class PupilDaoImpl implements PupilDao {
	
	//static final Logger logger = Logger.getLogger(FormDaoImpl.class);

		@Override
		public List<Pupil> getAllPupils() {
				String sql = "select * from pupil join class on class.id=class_id";
				return getPupil(sql);
		}

		@Override
		public List<Pupil> getPupilByForm(Integer id) {
				String sql = "select * from pupil join class on class.id=class_id where class_id="+id+"";
				return getPupil(sql);
		}
		
		private List<Pupil> getPupil(String sql){
			List<Pupil> result = new ArrayList<Pupil>();
			try {
				Statement statement =  StatementDB.getInstance().statement;
				ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
					Pupil pupil = new Pupil();
					pupil.setId(resultSet.getInt("pupil.id"));
					pupil.setName(resultSet.getString("pupil.name"));
					pupil.setSurname(resultSet.getString("surname"));
					Form form = new Form();
					form.setId(resultSet.getInt("class.id"));
					form.setName(resultSet.getString("class.name"));
					pupil.setForm(form);
					result.add(pupil);
				}
				resultSet.close();
			} catch (SQLException e) {
				System.out.println("Error in getAllPupils");
				//logger.error("Error in getAllPupils",e);
				e.printStackTrace();
			} 
			return result;
		}
		
		@Override
		public void insertPupil(Pupil pupil) {
			String sqlOut = "select * from class";
			try {
				Statement statement =  StatementDB.getInstance().statement;
				ResultSet resultSet = statement.executeQuery(sqlOut);
				while(resultSet.next()) {
					if(resultSet.getString("name").equals(pupil.getForm().getName())){
						String sqlIn = "insert into pupil(name,surname,class_id) values "
						+ "('"+pupil.getName()+"','"+pupil.getSurname()+"',"+resultSet.getInt("id")+")";
						statement.executeUpdate(sqlIn);
						//logger.info("add pupil");
						resultSet.close();
						return;
					} 
				}		
				new FormDaoImpl().insertForms(pupil.getForm());
				insertPupil(pupil);
			} catch (SQLException e) {
				//logger.error("Error in insertPupil",e);
				e.printStackTrace();
			} 
		}

		@Override
		public void insertPupil(String name, String sername, Integer classID) {
			final String patternSql = "insert into pupil(name,surname,class_id) values(''{0}'',''{1}'',{2})";
			final String sql = MessageFormat.format(patternSql, name, sername,classID);
			Statement statement =  StatementDB.getInstance().statement;
			try {
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		public static void main(String[] args) {
			PupilDaoImpl p = new PupilDaoImpl();
			p.insertPupil("Дориан", "Грей", 5);
			
		}
}


		
	


