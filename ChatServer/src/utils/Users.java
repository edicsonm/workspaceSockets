package utils;

import java.util.ResourceBundle;


public class Users {
	
	private static Users instance;
	public static ResourceBundle resourceBundle = ResourceBundle.getBundle("Users");
	
	public static Users getInstance() {
		if (instance == null) {
			instance = new Users();
		}
		return instance;
	}
	
	private Users() {}

	public String getKey(String key) {
		try {
			return resourceBundle.getString(key);
		}catch (java.util.MissingResourceException ex){
			throw ex;
		}
	}
	
//	public static void main(String[] args) {
//		try {
//			System.out.println(getKey("user.edicsona"));
//		}catch (java.util.MissingResourceException ex){
//			System.out.println("Error: " + ex.getMessage());
//		}
//	}

}
