package view;

import java.sql.SQLException;

import controller.LoginController;
import exception.DatiErratiException;
import model.Utente;

public class LoginBean {

	private String email;
	private String password;
	private String userid;// Min 6 caratteri
	private static boolean isAdmin;
	private LoginController controller;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public static void setAdmin(boolean isAdmin) {
		LoginBean.isAdmin = isAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginController getController() {
		return controller;
	}

	public void setController(LoginController controller) {
		this.controller = controller;
	}

	public boolean validate() throws DatiErratiException, SQLException{
		if (this.userid.equals("") || this.password.equals("")) {
			return false;
		}
		
		LoginController controller = LoginController.getInstance(); 
		Utente found = controller.login(this.userid, this.password);
	
		LoginBean.setAdmin(found.isAdmin());


		if (found != null) {
			
			return true;
		}
		return false;
		
	}
		
}
