package site;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.FormDao;
import dao.impl.FormDaoImpl;

@WebServlet ("/AddForm")
public class AddFormServlet extends HttpServlet{
	
	FormDao formDao = new FormDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		req.setCharacterEncoding("UTF-8");
		String addingform = req.getParameter("addingform");
		String formID = "";
		Integer addingformID = -1;
		formID = req.getParameter("formID");
		String nextJSP;
		addingformID = addClass(addingform);
		if (addingformID != -1){
			nextJSP ="/JournalOfClass.jsp?formID="+addingformID;
		} else {
			if(formID.equals("")){
			nextJSP ="/";
			} else {
			nextJSP ="/JournalOfClass.jsp?formID="+formID.trim();
			}
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		doGet(request, response);
	}
	
	private Integer addClass(String className){
		if (className.equals("")){
		return -1;
		} 
		if (className.length() < 3){
			return -1;
			} 
		return formDao.insertForms(className.toUpperCase());
	}
}
