package model;

import java.sql.*;



public class Main {

	public static void main(String[] argv) throws SQLException {

		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "DanieleG1994";

		try {
			/*Carico driver JDBC
			 * 
			 * L'op. completa da fare sarebbe:
			 * Driver d = new org.postgresql.Driver();
			 * DriverManager.registerDriver(d);
			 * Ma la seguente è l'equivalente
			 */
			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			return;

		}
		
		/* Connessione con il database */
		Connection connection = null; 
		//Oggetto di tipo Connection che costituisce un collegamento attivo fra
		//programma Java e db. 
		try {

			connection = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {

			e.printStackTrace();
			return;

		}
	}
	
}