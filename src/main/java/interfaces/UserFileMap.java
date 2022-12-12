package interfaces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;


public interface UserFileMap {
	
	// somehow fix to relative path
	//final String USER_FILE = "D:\\Program Files 2\\eclipse\\eclipse_workspace\\register-login\\src\\main\\webapp\\WEB-INF\\data\\users.txt";
	final String USER_FILE = "users.txt";
	
	final String LOGGED_IN = "WEB-INF/jsp/LoggedIn.jsp";
	final String REGISTERED = "WEB-INF/jsp/Registered.jsp";
	final String LOGIN_ERROR = "LoginError.jsp";
	final String REGISTER_ERROR = "RegisterError.jsp";
	final String IO_ERROR = "IOError.jsp";
	
	
	default HashMap<String, String> readUsers(String path) {
		BufferedReader in;
		
		
		try {
			in = new BufferedReader(new FileReader(path));
			HashMap<String, String> map = new HashMap<>();
			while (in.ready()) {
				String[] userPassword = in.readLine().split("#");
				map.put(userPassword[0], userPassword[1]);
				
			}
			in.close();
			return map;
		} catch (IOException e1) {
			// 
			e1.printStackTrace();
			return null;
		}
		
	}
	
	default boolean writeUser(String path, String username, String password) {
		
		HashMap<String, String> userBase = readUsers(path);
		
		
	    BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(path));
			for (var usernamePassword : userBase.entrySet()) {
				out.write(usernamePassword.getKey() + "#" + usernamePassword.getValue());
				out.newLine();
			}
			
			out.write(username + "#" + password);
			out.newLine();
			out.close();
			return true;
		} catch (IOException e1) {
			// 
			e1.printStackTrace();
			return false;
		}
	    
	}
}
