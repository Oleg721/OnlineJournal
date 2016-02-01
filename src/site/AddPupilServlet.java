package site;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PupilDao;
import dao.impl.PupilDaoImpl;

@WebServlet ("/AddPupil")
public class AddPupilServlet extends HttpServlet {
	
	PupilDao pupilDao = new PupilDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name").trim();
		String sername = req.getParameter("sername").trim();
		String formID = req.getParameter("formID").trim();
		if(name.length() > 0 && sername.length() > 0){
			name = formatName(name);
			sername = formatName(sername);
			pupilDao.insertPupil(name, sername, Integer.valueOf(formID));
		}
		String nextJSP ="/JournalOfClass.jsp?formID="+formID;
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		doGet(request, response);
	}
	
	private String formatName(String value){
		char firstLetter = value.charAt(0);
		StringBuilder resalt = new StringBuilder(value.toLowerCase());
		resalt.setCharAt(0, Character.toUpperCase(firstLetter));
		return resalt.toString();
	}
	
}
