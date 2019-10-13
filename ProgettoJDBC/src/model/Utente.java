package model;

	
	public class Utente {

		private String user_id;
		private String name;
		private String surname;
		private String password;
		private String email;
		
		private boolean isAdmin=false;
		
		public Utente(String user_id, String pass, Boolean isAdmin) {
			this.user_id = user_id;
			this.password = pass;
			this.isAdmin = isAdmin;
		}


		public Utente() {
			// TODO Auto-generated constructor stub
		}


		public boolean isAdmin() {
			return isAdmin;
		}

		public void setAdmin(boolean isAdmin) {
			this.isAdmin = isAdmin;
		}

		public String getUser_id() {
			return user_id;
		}
		
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
		
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
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
	}

