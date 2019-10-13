package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.UtentePresenteException;
import model.Utente;
import patternConnessione.DMsingleton;

public class RegistrazioneDao {

	public static void registra(Utente ut) throws UtentePresenteException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		conn = DMsingleton.getConnection();
		
		stmt = conn.prepareStatement("SELECT user_id FROM utente ");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()){
			if (rs.getString("user_id") == ut.getUser_id())
		
				throw new UtentePresenteException();
		
		}
		
		stmt = conn.prepareStatement("INSERT INTO Utente (user_id, email, isadmin, name, password, surname) "+
				"VALUES (?,?,?,?,?,?)");
		
		stmt.setString(1, ut.getUser_id());
		stmt.setString(2, ut.getEmail());
		stmt.setBoolean(3, ut.isAdmin());
		stmt.setString(4, ut.getName());
		stmt.setString(5, ut.getPassword());
		stmt.setString(6, ut.getSurname());
		
		
		stmt.executeUpdate();
		
		
		stmt.close();
		conn.close();
			
	}
		
}
