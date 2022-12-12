package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interfaces.UserFileMap;

@WebServlet("/loggedin")
public class LoginServlet extends HttpServlet implements UserFileMap {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		ServletContext context = req.getServletContext();
		String path = context.getRealPath("/WEB-INF/classes/" + USER_FILE);
		
		HashMap<String, String> userBase = readUsers(path);
		if (userBase == null) {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("IOError.jsp");
			//resp.getWriter().append("<h2>ERROR! NO SUCH USER OR WRONG PASSWORD!</h2>");
			requestDispatcher.forward(req, resp);
			
		}
		boolean userFound = false;
		boolean passwordCorrect = false;
		for (var usernamePassword : userBase.entrySet()) {
			if (usernamePassword.getKey().equals(username)) {
				userFound = true;
				
				if (userFound && usernamePassword.getValue().equals(password)) {
					passwordCorrect = true;
					break;
				}
				break;
			}
			
		}
		
		if (userFound && passwordCorrect) {
	 		RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/LoggedIn.jsp");
			requestDispatcher.forward(req, resp);
		} else {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/LoginError.jsp");
			//resp.getWriter().append("<h2>ERROR! NO SUCH USER OR WRONG PASSWORD!</h2>");
			requestDispatcher.forward(req, resp);
		}
	}
	
	
	
	
	
}
