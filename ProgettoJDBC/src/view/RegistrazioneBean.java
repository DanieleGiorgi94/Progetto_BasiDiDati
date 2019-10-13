package view;


import java.sql.SQLException;

import controller.UtenteController;
import exception.DatiErratiException;
import exception.MinLengthException;
import exception.UtentePresenteException;

public class RegistrazioneBean {
	
	private String name;
	private String surname;
	private String email;
	private String userid;//Min 6 caratteri
	private String password;//Min 6 caratteri
	UtenteController controller=new UtenteController();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void registrautente() throws MinLengthException,DatiErratiException, UtentePresenteException, SQLException{
		String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		
		if(this.name==null || this.surname==null || this.email==null || this.password==null|| this.userid==null ||
				!this.email.matches(emailRegex) || this.name.matches(".*\\d+.*") || this.surname.matches(".*\\d+.*"))
		{
			throw new DatiErratiException();
		}
		if(this.userid.length()<6 || this.password.length()<6)
		{
			throw new MinLengthException();
		}
		//Fare controlli
		controller.registra(this.name,this.surname,this.email,this.password,this.userid);
	}

}
