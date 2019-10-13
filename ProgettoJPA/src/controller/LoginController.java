package controller;
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
		
		public Utente login(String email, String password) throws DatiErratiException {
			
			Utente u = LoginDAo.findByUserNameAndPassword(email, password);
			
			if (u ==null){
				throw new DatiErratiException();
			}
	        return u;
			
		}	

	}

