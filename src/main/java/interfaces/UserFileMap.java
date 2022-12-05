package interfaces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public interface UserFileMap {
	
	// somehow fix to relative path
	final String USER_FILE = "D:\\Program Files 2\\eclipse\\eclipse_workspace\\register-login\\src\\main\\webapp\\WEB-INF\\data\\users.txt";
	//final String USER_FILE = "WEB-INF\\data\\users.txt";
	
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
	}
	
	default void writeUser(String path, String username, String password) {
		
		HashMap<String, String> userBase = readUsers(USER_FILE);
		
		
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
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	}
}
