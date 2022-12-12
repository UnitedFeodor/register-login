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

@WebServlet("/controller")
public class Controller extends HttpServlet implements UserFileMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4447390135204731042L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String requestDispatcherPath = null;
		String command = req.getParameter("command");
		ServletContext context = req.getServletContext();
		String path = context.getRealPath("/WEB-INF/classes/" + USER_FILE);
		switch(command) {
			case "register_command" -> {
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				
				HashMap<String, String> userBase = readUsers(path);
				if (userBase == null) {
					requestDispatcherPath = IO_ERROR;
					//RequestDispatcher requestDispatcher = req.getRequestDispatcher("IOError.jsp");
					//requestDispatcher.forward(req, resp);
					
				}
				
				if (password.contains("#")) {
					requestDispatcherPath = REGISTER_ERROR;
					
					//RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/RegisterError.jsp");
					
					//requestDispatcher.forward(req, resp);
					//return;
				} else if (username.contains("#")) {
					requestDispatcherPath = REGISTER_ERROR;
					//RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/RegisterError.jsp");
					
					//requestDispatcher.forward(req, resp);
					
				} else if (userBase.containsKey(username)) {
					// already existing username
					requestDispatcherPath = REGISTER_ERROR;
					//RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/RegisterError.jsp");
					
					//requestDispatcher.forward(req, resp);
					//return;
				} else {
					boolean isIOdone = writeUser(path,username, password);
					if (!isIOdone) {
						requestDispatcherPath = IO_ERROR;
						//RequestDispatcher requestDispatcher = req.getRequestDispatcher("IOError.jsp");
						//requestDispatcher.forward(req, resp);
					} else {
						requestDispatcherPath = REGISTERED;
					}
					//RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/Registered.jsp");
					//requestDispatcher.forward(req, resp);
				}	
			}
			case "login_command" -> {
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				
				
				HashMap<String, String> userBase = readUsers(path);
				
				
				if (userBase == null) {
					requestDispatcherPath = IO_ERROR;
					
					//RequestDispatcher requestDispatcher = req.getRequestDispatcher("IOError.jsp");
					//requestDispatcher.forward(req, resp);
					
				} else {
				
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
						requestDispatcherPath = LOGGED_IN;
				 		//RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/LoggedIn.jsp");
						//requestDispatcher.forward(req, resp);
					} else {
						requestDispatcherPath = LOGIN_ERROR;
						//RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/LoginError.jsp");
						//requestDispatcher.forward(req, resp);
					}
				}
				
			}
			default -> {
		        //System.err.println("Unexpected value ");
		        requestDispatcherPath = IO_ERROR;
		    }
			
	
		}
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(requestDispatcherPath);
		requestDispatcher.forward(req, resp);
		
	}

	

}
