package dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import connect.StatementDB;
import bom.Form;
import bom.Lesson;
import dao.LessonDao;

public class LessonDaoImpl implements LessonDao {
	
		@Override
		public List<Lesson> getAllLessons(){
				String sql = "select * from lesson join class on class.id=class_id";
				return getLessons(sql);
		}
		
		
		@Override
		public List<Lesson> getLessonsByClass(Integer classID) {
			String sql = "select * from lesson join class on class.id=class_id where class_id="+classID;
			return getLessons(sql);
		}
		
		
		private List<Lesson> getLessons(String sql){
			List<Lesson> result = new ArrayList<Lesson>();
			try {
				Statement statement =  StatementDB.getInstance().statement;
				ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
					Lesson lesson = new Lesson();
					lesson.setId(resultSet.getInt("id"));
					lesson.setTheme(resultSet.getString("theme"));
					lesson.setLessonDate(resultSet.getDate("lesson_date"));
					Form form = new Form();
					form.setId(resultSet.getInt("class.id"));
					form.setName(resultSet.getString("class.name"));
					lesson.setForm(form);
					result.add(lesson);
				}
				resultSet.close();
			} catch (SQLException e) {
				System.out.println("Error in getAllLessons");
				e.printStackTrace();
			} 
			return result;
			
		}
		public static void main(String[] args) throws ParseException {
			LessonDaoImpl l = new LessonDaoImpl();
			
			l.insertLesson(new SimpleDateFormat("yyyy-MM-dd").parse("2014-03-22"), "Абараба", 1);
		}

		@Override
		public void insertLesson(Date date, String theme, Integer classID) {
			final String patternSql = "insert into lesson(theme,lesson_date,class_id) values(''{1}'',''{0}'',{2})";
			final String sql = MessageFormat.format(patternSql, new SimpleDateFormat("yyy-MM-dd").format(date),theme,classID);
			System.out.println(sql);
			Statement statement =  StatementDB.getInstance().statement;
			try {
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		
}