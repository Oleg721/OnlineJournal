package site;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LessonDao;
import dao.impl.LessonDaoImpl;

@WebServlet("/AddLesson")
public class AddLessonServlet extends HttpServlet {
	
	private final LessonDao lessonDao = new LessonDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		req.setCharacterEncoding("UTF-8");
		String formID = req.getParameter("formID").trim();
		try {
			Date date =new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date").trim());
			String theme = req.getParameter("theme").trim();
			if(theme.length() > 0){
				lessonDao.insertLesson(date, theme, Integer.valueOf(formID));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String nextJSP ="/JournalOfClass.jsp?formID="+formID;
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		doGet(request, response);
	}

}
