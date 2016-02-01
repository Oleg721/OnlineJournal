package dao;
import java.util.List;

import bom.Mark;

public interface MarkDao {

	public List<Mark> getAllMarks();
	
	public List<Mark> getMarksByPupil(Integer pupilID); 
	
	public Integer markOfPupilByLesson(List<Mark> marks, Integer lessonID);
	
	public void insertMarkOnlyID(Integer pupilID, Integer lessonID, Integer mark);
	
	public void updatearkOnlyID(Integer pupilID, Integer lessonID, Integer mark);
	
	public void deleteMarkOnlyID(Integer pupilID, Integer lessonID);
	
}
