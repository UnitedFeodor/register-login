package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

		String nextPage = null;
		String errorMessage = null;
		String command = req.getParameter("command");
		ServletContext context = req.getServletContext();
		String path = context.getRealPath("/WEB-INF/classes/" + USER_FILE);
		switch(command) {
			case "register_command" -> {
				nextPage = REGISTER_FORM;
				
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				
				HashMap<String, String> userBase = readUsers(path);
				if (userBase == null) {
					errorMessage = "IO error: unable to read data";
					
				}
				
				if (password.contains("#")) {
					errorMessage = "Password error: can't contain '#' symbol";
					
				
				} else if (username.contains("#")) {
					errorMessage = "Username error: can't contain '#' symbol";
					
					
				} else if (userBase.containsKey(username)) {
					// already existing username
					errorMessage = "Username error: user already exists in the data base";
					
				} else {
					boolean isIOdone = writeUser(path,username, password);
					if (!isIOdone) {
						errorMessage = "IO error: unable to write data";
					} else {
						nextPage = REGISTERED;
					}
					
				}	
			}
			case "login_command" -> {
				nextPage = LOGIN_FORM;
				
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				
				
				HashMap<String, String> userBase = readUsers(path);
				
				
				if (userBase == null) {
					errorMessage = "IO error: unable to read data";
					
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
					
					
					if (userFound && !passwordCorrect){
						errorMessage = "Password error: incorrect password for this username";

					} else if (!userFound) {
						errorMessage = "Username error: no such user found";
					} else if (userFound && passwordCorrect) {
						nextPage = LOGGED_IN;
						
					} 
				
				}
			}
			default -> {
		        
				errorMessage = "Command error: unexpected value";
				nextPage = LOGIN_FORM;
		    }
			
	
		}
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(nextPage);
		if (errorMessage != null) {
			
			PrintWriter out = resp.getWriter();  
			resp.setContentType("text/html");  
			out.print(errorMessage + "\n");
			
			requestDispatcher.include(req, resp);
		} else {
			requestDispatcher.forward(req, resp);
		}
		
	}

	

}
