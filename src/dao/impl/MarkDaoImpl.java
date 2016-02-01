package dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import connect.StatementDB;
import bom.Lesson;
import bom.Mark;
import bom.Pupil;
import dao.MarkDao;

public class MarkDaoImpl implements MarkDao {

			@Override
			public List<Mark> getAllMarks() {
				String sql = "select * from marks join lesson on lesson_id = lesson.id join pupil on pupil_id = pupil.id ";
				return getMarks(sql);
			}
			
			@Override
			public List<Mark> getMarksByPupil(Integer pupilID) {
				String sql = "select * from marks join lesson on lesson_id ="
						+ " lesson.id join pupil on pupil_id = pupil.id where pupil.id="+pupilID ;
				return getMarks(sql);
			}

		
			@Override
			public void insertMarkOnlyID(Integer pupilID, Integer lessonID, Integer mark) {
				String sql = "insert into marks values("+lessonID+","+pupilID+","+mark+");";
				executeUpdateID(sql);
			}
			

			@Override
			public void updatearkOnlyID(Integer pupilID, Integer lessonID, Integer mark) {
				String sql = "update marks set mark = "+mark+"  where lesson_id ="+lessonID+" and  pupil_id="+pupilID+";";
				executeUpdateID(sql);
			}

			@Override
			public void deleteMarkOnlyID(Integer pupilID, Integer lessonID) {
				String sql = "delete from marks where lesson_id="+lessonID+" and pupil_id="+pupilID+";";
				executeUpdateID(sql);
			}
			
			private void executeUpdateID(String sql){
				Statement statement =  StatementDB.getInstance().statement;
				try {
					int resultSet = statement.executeUpdate(sql);
					System.out.println(resultSet);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			private List<Mark> getMarks(String sql) {
				List<Mark> result = new ArrayList<Mark>();
				try {
					Statement statement =  StatementDB.getInstance().statement;
					ResultSet resultSet = statement.executeQuery(sql);
					while(resultSet.next()) {
						Mark mark = new Mark();
						Pupil pupil = new Pupil();
						pupil.setId(resultSet.getInt("pupil.id"));
						pupil.setName(resultSet.getString("pupil.name"));
						pupil.setSurname(resultSet.getString("surname"));
						mark.setPupil(pupil);
						Lesson lesson = new Lesson();
						lesson.setId(resultSet.getInt("id"));
						lesson.setTheme(resultSet.getString("theme"));
						lesson.setLessonDate(resultSet.getDate("lesson_date"));
						mark.setLesson(lesson);
						mark.setMark(resultSet.getInt("Mark"));
						result.add(mark);
					}
					resultSet.close();
				} catch (SQLException e) {
					System.out.println("Error in getAllMark");
					e.printStackTrace();
				} 
				return result;
			}
			
			@Override
			public Integer markOfPupilByLesson(List<Mark> marks, Integer lessonID){
				for(Mark mark :marks){
					if(mark.getLesson().getId() == lessonID)
						return mark.getMark();
				}
				return -1;
				
			}

}
