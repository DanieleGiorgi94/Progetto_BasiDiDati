package controller;
import java.sql.SQLException;

import dao.LoginDAo;
import exception.DatiErratiException;
import model.Utente;


public class LoginController {

		private static LoginController instance;
		
		private LoginController(){
			//costruttore privato per singletone
		}
		
		public synchronized static LoginController getInstance() {
	        if (instance == null)
	            instance = new LoginController();
	        return instance;
		}
		
		public Utente login(String email, String password) throws DatiErratiException, SQLException {
			
			Utente b = LoginDAo.findByUserNameAndPassword(email, password);
			

	        return b;
			
		}	

	}

