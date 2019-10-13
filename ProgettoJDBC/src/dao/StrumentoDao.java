package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import model.Banda;
import model.Satellite;
import model.Strumento;
import patternConnessione.DMsingleton;

public class StrumentoDao{

	public static List<Banda> findBande() throws SQLException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		conn = DMsingleton.getConnection();

		List<Banda> bande = new ArrayList<Banda>();
		Banda b = null;
		
		stmt = conn.prepareStatement("SELECT * "+
				"FROM Banda ");
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			float risoluzione = rs.getFloat("risoluzione");
			b = new Banda(risoluzione);
		
			bande.add(b);
		}

		if (bande.size() == 0)
			return null;
		
		return bande;
	}

	public static List<Satellite> findSatelliti() throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		
	
			conn = DMsingleton.getConnection();
		
		Satellite satellite = null;
		List<Satellite> Satelliti =  new ArrayList<Satellite>();
		
		stmt = conn.prepareStatement("SELECT * from Satellite ");
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			String nome = rs.getString("name");
			String durata = rs.getString("duration");
			String firstObs = rs.getString("firstobservation");
			String lastObs = rs.getString("lastobservation");
			
			satellite = new Satellite(nome, durata, firstObs, lastObs);
			Satelliti.add(satellite);
		}
		
		rs.close();
		stmt.close();
		
		conn.close();
		
		if(Satelliti.size() == 0){
			return null;
		}else{
			return Satelliti;
		}	

	}

	public static void insertStrumento(Vector<Float> bande, String satellite, String nomeStrumento) {

		Vector<Banda> vBanda = new Vector<Banda>(1);

		Connection conn = null;
		PreparedStatement stmt = null;
		
		Strumento tool = null;

		try {

			conn = DMsingleton.getConnection();

			stmt = conn.prepareStatement("INSERT INTO Strumento(toolid, satellite_name) "+
					"VALUES (?,?) ");

			stmt.setString(1, nomeStrumento); // Imposto l'id dello strumento
			stmt.setString(2, satellite); // Imposto il nome del satellite

			stmt.executeUpdate();

			stmt.close();

			if (bande != null) {
				for (int i = 0; i < bande.size(); i++)
					vBanda.add(i, StrumentoDao.findBande().get(i));
			}

			tool.setBanda(vBanda);

			for (int i = 0; i < bande.size(); i++) {
			
			stmt = conn.prepareStatement("INSERT INTO strumento_banda(tools_satellite_name, tools_toolid, banda_risoluzione) "+
					"VALUES (?,?,?) ");
			
			stmt.setString(1, satellite);
			stmt.setString(2, nomeStrumento);
			stmt.setFloat(3, tool.getBanda().get(i).getRisoluzione());
			
			stmt.executeUpdate();
			stmt.close();
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}