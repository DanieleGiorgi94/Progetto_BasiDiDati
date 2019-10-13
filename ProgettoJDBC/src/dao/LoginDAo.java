package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Utente;
import patternConnessione.DMsingleton;

public class LoginDAo {
	

	
	public static Utente findByUserNameAndPassword(String userid, String password) throws SQLException {

		Connection conn = null;
		
		Utente u = null;
		List<Utente> Cercato = new ArrayList<Utente>();
		
		try {
			
			conn = DMsingleton.getConnection();
			
			PreparedStatement prepStmt = conn.prepareStatement("select * from utente where user_id=? and password=? ");
			prepStmt.setString(1, userid);
			prepStmt.setString(2, password);
			
			ResultSet rs = prepStmt.executeQuery();
			
			if (rs.next()) {
				String user_id = rs.getString("user_id");
				String pass = rs.getString("password");
				Boolean isAdmin = rs.getBoolean("isadmin");
				
				u = new Utente(user_id, pass, isAdmin);
				Cercato.add(u);
			}
			
			rs.close();
			prepStmt.close();
			
			if (Cercato.size() == 0) {
				return null;
			}
			
			return Cercato.get(0);
			
			
		} catch(Exception e) {
			System.out.println("validateLogon: Error while validating password: "+e.getMessage());
			throw e;
		} finally {
			conn.close();
		}	
		
	}

}
