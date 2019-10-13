package controller;

import dao.RegistrazioneDao;
import exception.UtentePresenteException;
import model.Utente;

public class UtenteController {

	public void registra(String name,String surname,String email,String password,String userid) throws UtentePresenteException {
		
		Utente ut=new Utente();
		ut.setName(name);
		ut.setSurname(surname);
		ut.setEmail(email);
		ut.setPassword(password);
		ut.setUser_id(userid);
		RegistrazioneDao.registra(ut);
		
	}
	
	

}

