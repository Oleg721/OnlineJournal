package dao;
import java.util.Date;
import java.util.List;

import bom.Lesson;

public interface LessonDao {
	
	public List<Lesson> getAllLessons();
	
	public List<Lesson> getLessonsByClass(Integer classID);
	
	void insertLesson(Date date, String theme, Integer classID);
	
}
