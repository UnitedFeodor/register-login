package servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interfaces.UserFileMap;

@WebServlet("/registered")
public class RegisterServlet extends HttpServlet implements UserFileMap{
 
	
	//private HashMap<String, String> userBase = new HashMap<>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HashMap<String, String> userBase = readUsers(USER_FILE);
		
		if (password.contains("#")) {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/RegisterError.jsp");
			
			requestDispatcher.forward(req, resp);
			return;
		}
		if (username.contains("#")) {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/RegisterError.jsp");
			
			requestDispatcher.forward(req, resp);
			
			return;
		}
		if (userBase.containsKey(username)) {
			// already existing username
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/RegisterError.jsp");
			
			requestDispatcher.forward(req, resp);
			return;
		}
		writeUser(USER_FILE,username, password);
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/Registered.jsp");
		requestDispatcher.forward(req, resp);
		
	}
	

	
}
