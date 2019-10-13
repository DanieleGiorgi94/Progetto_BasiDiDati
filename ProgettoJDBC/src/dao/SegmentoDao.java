package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Punto_scheletro;
import model.Segmento;
import patternConnessione.DMsingleton;



public class SegmentoDao {
	

	public static ArrayList<Double> getMinVertex(Integer idsegmento) {
		
		PreparedStatement stmt  = null;
		Connection conn = null;
		
		Punto_scheletro ps = null;
		Segmento s = null;
		
		List<Segmento> segments = new ArrayList <Segmento>();
		ArrayList<Double> vertex = new ArrayList<Double>();

		try {
			
			conn = DMsingleton.getConnection();

			stmt = conn.prepareStatement("SELECT idsegmento, s.puntoscheletro_latitudine, s.puntoscheletro_longitudine, numeroprogressivo FROM Segmento s "+
					"JOIN punto_scheletro ps ON s.puntoscheletro_latitudine = ps.latitudine and s.puntoscheletro_longitudine = ps.longitudine "+
					"JOIN scheletro sc ON sc.puntoscheletro_latitudine = ps.latitudine and sc.puntoscheletro_longitudine = ps.longitudine "+
					"WHERE s.idsegmento = ?  ORDER BY sc.filamento_id,s.numeroprogressivo ");
			
			stmt.setInt(1, idsegmento);

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				int idSegmento= rs.getInt("idsegmento");
				int nProg = rs.getInt("numeroprogressivo");
				float lat= rs.getFloat("puntoscheletro_latitudine");
				float longit = rs.getFloat("puntoscheletro_longitudine");

				ps = new Punto_scheletro(lat, longit);
				s = new Segmento(ps, idSegmento, nProg);
				
				segments.add(s);
			}
			
			stmt.close();
			conn.close();

		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		
		if (segments.size() == 0) 
			return null;
		
		for (int i=0; i<segments.size();i++){
			if (segments.get(i).getNumeroProgressivo() == 1) {
				double latMinVertex = segments.get(i).getPuntoscheletro().getLatitudine();
				double longMinVertex = segments.get(i).getPuntoscheletro().getLongitudine();
				vertex.add(latMinVertex);
				vertex.add(longMinVertex);
				System.out.println(latMinVertex);
				System.out.println(longMinVertex);
			}
			
		}
		
		if (segments.size() == 0) 
			return null;
		
		return vertex;
		
	}
	
	public static ArrayList<Double> getMaxVertex(Integer idsegmento) {
		
		PreparedStatement stmt  = null;
		Connection conn = null;
		
		Punto_scheletro ps = null;
		Segmento s = null;
		
		List<Segmento> segments = new ArrayList <Segmento>();
		ArrayList<Double> vertex = new ArrayList<Double>();

		try {
			
			conn = DMsingleton.getConnection();

			stmt = conn.prepareStatement("SELECT idsegmento, s.puntoscheletro_latitudine, s.puntoscheletro_longitudine, numeroprogressivo FROM Segmento s "+
					"JOIN punto_scheletro ps ON s.puntoscheletro_latitudine = ps.latitudine and s.puntoscheletro_longitudine = ps.longitudine "+
					"JOIN scheletro sc ON sc.puntoscheletro_latitudine = ps.latitudine and sc.puntoscheletro_longitudine = ps.longitudine "+
					"WHERE s.idsegmento = ?  ORDER BY sc.filamento_id,s.numeroprogressivo ");
			
			stmt.setInt(1, idsegmento);

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				int idSegmento= rs.getInt("idsegmento");
				int numeroprogressivo = rs.getInt("numeroprogressivo");
				float lat= rs.getFloat("puntoscheletro_latitudine");
				float longit = rs.getFloat("puntoscheletro_longitudine");

				ps = new Punto_scheletro(lat, longit);
				s = new Segmento(ps, idSegmento, numeroprogressivo);
				
				segments.add(s);
			}

		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Integer size = segments.size();	
		
		if (size == 0) 
			return null;
		
		for (int i=1; i<size; i++){
			if (segments.get(i).getNumeroProgressivo() == 1) {
				double latMaxVertex = segments.get(i-1).getPuntoscheletro().getLatitudine();
				double longMaxVertex = segments.get(i-1).getPuntoscheletro().getLongitudine();
				vertex.add(latMaxVertex);
				vertex.add(longMaxVertex);
				System.out.println(latMaxVertex);
				System.out.println(longMaxVertex);
			}
		}
		
		vertex.add(segments.get(size-1).getPuntoscheletro().getLatitudine());
		vertex.add(segments.get(size-1).getPuntoscheletro().getLongitudine());
		
		return vertex;
			
	}
	
	

}