package site;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bom.Mark;
import dao.MarkDao;
import dao.impl.MarkDaoImpl;

@WebServlet ("/SaveMark")
public class SaveMarkServlet extends HttpServlet {
	
	private MarkDao markDao = new MarkDaoImpl();
	
	private List<Integer> markValue = new ArrayList<>();
	
	private List<Integer> oldMark = new ArrayList<>();
	
	private List<Integer> lessonID = new ArrayList<>();
	
	private List<Integer> pupilID = new ArrayList<>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
		if(req.getParameterValues("markValue") != null){
		markValue = replaceMarksIntoNumbers(req.getParameterValues("markValue"));
		oldMark = replaceMarksIntoNumbers(trim(req.getParameterValues("oldMark")));
		lessonID = replaceIDIntoNumbers(trim( req.getParameterValues("lessonID")));
		pupilID = replaceIDIntoNumbers(trim(req.getParameterValues("pupilID")));
		saveMarks();
		}
		String formID = req.getParameter("formID").trim();	
		String nextJSP ="/JournalOfClass.jsp?formID="+formID;
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		doGet(request, response);
	}
	

	private void saveMarks(){
		Iterator<Integer> markValueIterator = markValue.iterator();
		Iterator<Integer> oldMarkIterator = oldMark.iterator();
		Iterator<Integer> lessonIDIterator = lessonID.iterator();
		Iterator<Integer> pupilIDIterator = pupilID.iterator();
		while(markValueIterator.hasNext()){
			Integer markValue = markValueIterator.next();
			Integer oldMark = oldMarkIterator.next();
			Integer lessonID = lessonIDIterator.next();
			Integer pupilID = pupilIDIterator.next();
			if (markValue != oldMark){
				if(markValue == -1){
					markDao.deleteMarkOnlyID(pupilID, lessonID);
					continue;
				}
				if(oldMark == -1) {
					markDao.insertMarkOnlyID(pupilID, lessonID, markValue);
					continue;
				} else {
					markDao.updatearkOnlyID(pupilID, lessonID, markValue);
				}
			}
		}
	}
	
	private List<Integer> replaceMarksIntoNumbers(String[] values){
		List<Integer> result = new ArrayList<>();
		for(String value : values){
			if(value.length() < 1 || value.equals(" ") ){
			result.add(-1);
			continue;
			} 
			int mark = 0;
			if(Character.isDigit(value.charAt(0))){
				mark = Integer.valueOf(value);
			} 
			if(mark <= 5 && mark >= 1){
				result.add(mark);
			}else{
				result.add(0);
			}
		} 
		return result;
	}
	
	
	private List<Integer> replaceIDIntoNumbers(String[] values){
		List<Integer> result = new ArrayList<>();
		for(String value : values){
			result.add(Integer.valueOf(value));
		}
		return result;
	}
	
	private String[] trim(String[] values){
		String[] strArr = new String[values.length];
		for(int i = 0; i < values.length; i++){
			strArr[i] = values[i].trim();
		}
		return strArr;
	}
	
	
}
