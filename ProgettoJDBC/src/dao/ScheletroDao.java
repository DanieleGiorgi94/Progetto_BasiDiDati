package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import model.*;
import patternConnessione.DMsingleton;





public class ScheletroDao {

	public static void insertHeschel(String[] elements, Connection conn) {
		
		PreparedStatement stmt = null;

		try {
			
			if (conn.isClosed() || conn == null)
				conn = DMsingleton.getConnection();
		
			Satellite satellite = new Satellite();
			satellite.setName("Herschel");
			
			System.out.println("id" + elements[0] + "long"+elements[1]+"lat"+elements[2]+elements[3]+elements[4]);
			
			
			stmt=conn.prepareStatement("INSERT INTO punto_scheletro(longitudine, latitudine ) "+
					"VALUES (?,?); ");
			
		
			stmt.setDouble(1, Double.parseDouble(elements[3]));
			stmt.setDouble(2, Double.parseDouble(elements[4]));
			
			
			stmt.executeUpdate();
			stmt.close();
			
		

			stmt=conn.prepareStatement("INSERT INTO segmento(idsegmento, numeroprogressivo, tipo, puntoscheletro_latitudine, puntoscheletro_longitudine) "+
					"VALUES (?,?,?,?,?); ");
			
			stmt.setInt(1, Integer.parseInt(elements[1]));
			stmt.setInt(2, Integer.parseInt(elements[5]));
			stmt.setString(3, elements[2]);
			stmt.setDouble(4, Double.parseDouble(elements[4]));
			stmt.setDouble(5, Double.parseDouble(elements[3]));
			
			stmt.executeUpdate();
			stmt.close();
			

			stmt=conn.prepareStatement("INSERT INTO scheletro(flusso, puntoscheletro_latitudine, puntoscheletro_longitudine, filamento_id) "+
					"VALUES (?,?,?,?); ");
	
			

			stmt.setDouble(1, Double.parseDouble(elements[6]));
			stmt.setDouble(2, Double.parseDouble(elements[4]));
			stmt.setDouble(3, Double.parseDouble(elements[3]));
			stmt.setInt(4,  Integer.parseInt(elements[0]));
			
			stmt.executeUpdate();
			stmt.close();
			
			

	
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null)
					stmt.close();

			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		}
	
	}

	public static void insertSpitzer(String[] elements, Connection conn) {
		
		PreparedStatement stmt = null;	
		
		
		try {

			if (conn.isClosed() || conn == null)
				conn = DMsingleton.getConnection();
			
			
			Satellite satellite = new Satellite();
			satellite.setName("Spitzer");

			
			stmt=conn.prepareStatement("INSERT INTO punto_scheletro(longitudine, latitudine ) "+
					"VALUES (?,?); ");
			
		
			stmt.setDouble(1, Double.parseDouble(elements[3]));
			stmt.setDouble(2, Double.parseDouble(elements[4]));
			
			
			stmt.executeUpdate();
			stmt.close();
		

		
			stmt=conn.prepareStatement("INSERT INTO scheletro(flusso, puntoscheletro_latitudine, puntoscheletro_longitudine, filamento_id) "+
					"VALUES (?,?,?,?); ");
	
			

			stmt.setDouble(1, Double.parseDouble(elements[6]));
			stmt.setDouble(2, Double.parseDouble(elements[4]));
			stmt.setDouble(3, Double.parseDouble(elements[3]));
			stmt.setInt(4, Integer.parseInt(elements[0]));
			
			stmt.executeUpdate();
			stmt.close();
			
		
		
			stmt=conn.prepareStatement("INSERT INTO segmento(idsegmento, numeroprogressivo, tipo, puntoscheletro_latitudine, puntoscheletro_longitudine) "+
					"VALUES (?,?,?,?,?); ");
			
			stmt.setInt(1, Integer.parseInt(elements[1]));
			stmt.setInt(2, Integer.parseInt(elements[5]));
			stmt.setString(3, elements[2]);
			stmt.setDouble(4, Double.parseDouble(elements[4]));
			stmt.setDouble(5, Double.parseDouble(elements[3]));
			
			stmt.executeUpdate();
			stmt.close();
			
			System.out.println("INSERT done");
				
			
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null)
					stmt.close();

			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		}
	
	}
	
	public static void updateSkeleton(String[] elements, Connection conn) {
		PreparedStatement stmt=null;
		
		
		try {
			
			if (conn.isClosed() || conn == null)
				conn = DMsingleton.getConnection();

		
			stmt=conn.prepareStatement("UPDATE Punto_scheletro "+
			                           "SET longitudine = ?, latitudine = ? "+
					"WHERE longitudine = ? and latitudine = ?" );


			stmt.setDouble(1, Double.parseDouble(elements[3]));
			stmt.setDouble(2, Double.parseDouble(elements[4]));
			stmt.setDouble(3, Double.parseDouble(elements[3]));
			stmt.setDouble(4, Double.parseDouble(elements[4]));
			
			stmt.executeUpdate();
			stmt.close();

			
			stmt=conn.prepareStatement("UPDATE Scheletro "+
                    "SET flusso = ?, puntoscheletro_latitudine = ?, puntoscheletro_longitudine = ?, filamento_id = ? "+
					"WHERE puntoscheletro_latitudine = ? and puntoscheletro_longitudine = ? and filamento_id = ? ");
			
			stmt.setDouble(1, Double.parseDouble(elements[6]));
			stmt.setDouble(2, Double.parseDouble(elements[4]));
			stmt.setDouble(3, Double.parseDouble(elements[3]));
			stmt.setInt(4, Integer.parseInt(elements[0]));
			stmt.setDouble(5, Double.parseDouble(elements[4]));
			stmt.setDouble(6, Double.parseDouble(elements[3]));
			stmt.setInt(7, Integer.parseInt(elements[0]));
			
			stmt.executeUpdate();
			stmt.close();
			
			

			stmt=conn.prepareStatement("UPDATE Segmento "+
					"SET idsegmento = ?, numeroprogressivo = ?, tipo = ?, puntoscheletro_latitudine = ?, puntoscheletro_longitudine = ? "+
					"WHERE idsegmento = ? and numeroprogressivo = ? and puntoscheletro_latitudine = ? and puntoscheletro_longitudine = ? ");
			
			stmt.setInt(1, Integer.parseInt(elements[1]));
			stmt.setInt(2, Integer.parseInt(elements[5]));
			stmt.setString(3, elements[2]);
			stmt.setDouble(4, Double.parseDouble(elements[4]));
			stmt.setDouble(5, Double.parseDouble(elements[3]));
			stmt.setInt(6, Integer.parseInt(elements[1]));
			stmt.setInt(7, Integer.parseInt(elements[5]));
			stmt.setDouble(8, Double.parseDouble(elements[4]));
			stmt.setDouble(9, Double.parseDouble(elements[3]));
			
			stmt.executeUpdate();
			stmt.close();
			
			System.out.println("UPDATE done");
		
			
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
	        try {
	            if (stmt != null)
	                stmt.close();
	        } catch (SQLException se2) {
	        	se2.printStackTrace();
	        }
		}
	}



	public static Integer numeroSegmenti(Integer id) throws SQLException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		conn = DMsingleton.getConnection();
		
		stmt = conn.prepareStatement("SELECT idsegmento, numeroprogressivo, tipo, s.puntoscheletro_latitudine, s.puntoscheletro_longitudine "+
				"FROM Segmento s JOIN punto_scheletro ps ON (s.puntoscheletro_latitudine = ps.latitudine and s.puntoscheletro_longitudine = ps.longitudine) "+
				"JOIN scheletro sc ON (sc.puntoscheletro_latitudine = ps.latitudine and sc.puntoscheletro_longitudine = ps.longitudine) "+
				"WHERE sc.filamento_id = ? order by s.idsegmento ");
		
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();


		List<Segmento> branches = new ArrayList <Segmento>();
		Segmento s = null;
		Punto_scheletro ps = null;

		while (rs.next()) {

			int idSegmento= rs.getInt("idsegmento");
			int numeroprogressivo = rs.getInt("numeroprogressivo");
			String tipo = rs.getString("tipo");
			double lat= rs.getDouble("puntoscheletro_latitudine");
			double longit = rs.getDouble("puntoscheletro_longitudine");

			ps = new Punto_scheletro(lat, longit);
			s = new Segmento(ps, idSegmento, numeroprogressivo, tipo);

			branches.add(s);

		}

		int count = 1;
		int idbranch = branches.get(0).getIdSegmento();

		for(int i = 1; i < branches.size(); i++ ) {
			if (branches.get(i).getIdSegmento() == idbranch ) {
				continue;
			}
			//non è uguale, significa che esiste un altro segmento ancora: incrementa count
			idbranch = branches.get(i).getIdSegmento();
			count++;
		}

		return count;
	}


	public static List<Integer> filamentipersegmenti(Integer Index1, Integer Index2) {

		Connection conn = null;
		PreparedStatement stmt = null;

		//Filamento f = null;
		//List<Filamento> filaments = new ArrayList <Filamento>();
		List<Integer> idfilamento = new ArrayList<Integer>();

		try {

			conn = DMsingleton.getConnection();
			
/* 
			stmt = conn.prepareStatement("SELECT * "+
					"FROM Filamento f JOIN scheletro sc ON f.id = sc.filamento_id "+
					"JOIN punto_scheletro ps ON sc.puntoscheletro_latitudine = ps.latitudine and sc.puntoscheletro_longitudine = ps.longitudine "+
					"JOIN segmento s ON ps.longitudine = s.puntoscheletro_longitudine and ps.latitudine = s.puntoscheletro_latitudine "+
					"GROUP BY f.id having count(distinct s.idsegmento) between ? and ? ");

			stmt.setInt(1, Index1);
			stmt.setInt(2, Index2);

			ResultSet rs = stmt.executeQuery();


			while (rs.next()) {

				int idFil = rs.getInt("id");
				float contrasto = rs.getFloat("contrasto");
				float densita = rs.getFloat("densitamedia");
				double flussotot = rs.getDouble("flussototale");
				float tempmedia = rs.getFloat("temperaturamedia");
				float ellitticita = rs.getFloat("ellitticita");
				String nomeFil = rs.getString("nome");


				f = new Filamento(idFil,nomeFil, densita, tempmedia, flussotot, ellitticita, contrasto);	
				filaments.add(f);
			}
			rs.close();*/
			
			stmt = conn.prepareStatement("SELECT sc.filamento_id from Scheletro sc "+
					"JOIN punto_scheletro ps ON sc.puntoscheletro_latitudine = ps.latitudine and sc.puntoscheletro_longitudine = ps.longitudine "+
					"JOIN Segmento s ON ps.latitudine = s.puntoscheletro_latitudine and ps.longitudine = s.puntoscheletro_longitudine "+
					"GROUP BY sc.filamento_id having count(distinct s.idsegmento) between ? and ? order by sc.filamento_id ");
			
			stmt.setInt(1, Index1);
			stmt.setInt(2, Index2);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Integer idFil = rs.getInt("filamento_id");
				idfilamento.add(idFil);
			}
			
			rs.close();
			stmt.close();
			conn.close();

		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if (idfilamento.size() == 0) 
			return null;
	
		return idfilamento;
	}

	public static ArrayList<Stella> DistanzaSpina(Integer filamento_id, ArrayList<Stella> StelleFilamento) {

		Connection conn = null;
		PreparedStatement stmt = null;


		ArrayList<Double> dist = new ArrayList<Double>();

		Punto_scheletro ps = new Punto_scheletro();
		Segmento s = null;

		List<Segmento> backbone = new ArrayList<Segmento>();

		try {
			
			conn = DMsingleton.getConnection();


			stmt = conn.prepareStatement("SELECT idsegmento, numeroprogressivo, tipo, s.puntoscheletro_latitudine, s.puntoscheletro_longitudine "+
					"FROM Segmento s JOIN punto_scheletro ps ON (s.puntoscheletro_latitudine = ps.latitudine and s.puntoscheletro_longitudine = ps.longitudine) "+
					"JOIN scheletro sc ON (sc.puntoscheletro_latitudine = ps.latitudine and sc.puntoscheletro_longitudine = ps.longitudine) "+
					"WHERE sc.filamento_id = ? and s.tipo = 'S' ");

			stmt.setInt(1, filamento_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()){

				int idSegmento= rs.getInt("idsegmento");
				int numeroprogressivo = rs.getInt("numeroprogressivo");
				String tipo = rs.getString("tipo");
				double lat= rs.getDouble("puntoscheletro_latitudine");
				double longit = rs.getDouble("puntoscheletro_longitudine");

				ps = new Punto_scheletro(lat, longit);
				s = new Segmento(ps, idSegmento, numeroprogressivo, tipo);

				backbone.add(s);

			}

			stmt.close();
			rs.close();
			conn.close();

			if (backbone.size() == 0) 
				return null;
			
			int j;
			int i;
			
			for(j=0; j<StelleFilamento.size();j++) {
				double distanceMin = ps.getDistance(Double.valueOf(StelleFilamento.get(j).getLatitudine()), Double.valueOf(StelleFilamento.get(j).getLongitudine()),Double.valueOf(backbone.get(0).getPuntoscheletro().getLatitudine()),Double.valueOf(backbone.get(0).getPuntoscheletro().getLongitudine()));
				for (i=1; i<backbone.size();i++) {
					if (distanceMin < (ps.getDistance(Double.valueOf(StelleFilamento.get(j).getLatitudine()), Double.valueOf(StelleFilamento.get(j).getLongitudine()),Double.valueOf(backbone.get(i).getPuntoscheletro().getLatitudine()),Double.valueOf(backbone.get(i).getPuntoscheletro().getLongitudine())))) {
						continue;
					}
					else {
						distanceMin = ps.getDistance(Double.valueOf(StelleFilamento.get(j).getLatitudine()), Double.valueOf(StelleFilamento.get(j).getLongitudine()),Double.valueOf(backbone.get(i).getPuntoscheletro().getLatitudine()),Double.valueOf(backbone.get(i).getPuntoscheletro().getLongitudine()));
					}
				}
				StelleFilamento.get(j).setDistanza(distanceMin);
				
			}
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}

			return StelleFilamento;
	}

	public static double getMinDistance(double lat, double longit,Integer filamento_id) throws Exception {
		// TODO Auto-generated method stub

		List <Contorno> outline = ContornoDao.getContorniperId(filamento_id);
		
		System.out.println(lat+longit+filamento_id);
		
		System.out.println("valueof"+Double.valueOf(lat));

		Punto_contorno pc = new Punto_contorno();
		double distanceMin = pc.getDistance(Double.valueOf(outline.get(0).getPuntocontorno().getLatitudine()),Double.valueOf(outline.get(0).getPuntocontorno().getLongitudine()),Double.valueOf(lat),Double.valueOf(longit));
		for(int j=1; j< outline.size();j++) {
			if (distanceMin < pc.getDistance(Double.valueOf(outline.get(j).getPuntocontorno().getLatitudine()),Double.valueOf(outline.get(j).getPuntocontorno().getLongitudine()),Double.valueOf(lat),Double.valueOf(longit))) {
				continue;
			}
			else {
				distanceMin = pc.getDistance(Double.valueOf(outline.get(j).getPuntocontorno().getLatitudine()),Double.valueOf(outline.get(j).getPuntocontorno().getLongitudine()),Double.valueOf(lat),Double.valueOf(longit));
			}

		}

		return distanceMin;

	}

	public static Integer IdFilamentoVerticiSegmento(double lat, double longit) { // mi resistuisce l'id del filamento relativo ai vertici del segmento di cui poi, con getDistance, mi calcoler� la distanza

		PreparedStatement stmt  = null;
		Connection conn = null;
				
		List<Scheletro> scheletri = new ArrayList<Scheletro>();
		Scheletro s = null;
		Filamento f = null;
		
		Integer filamento_id = null;
	
		try {
			
			conn = DMsingleton.getConnection();

			stmt = conn.prepareStatement("SELECT filamento_id "+
					"FROM Scheletro "+
					"WHERE puntoscheletro_latitudine = ? and puntoscheletro_longitudine = ? order by filamento_id ");
			
			stmt.setDouble(1, (double)Math.round(lat* 100000d) / 100000d);
			stmt.setDouble(2, (double)Math.round(longit * 100000d) / 100000d);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()){
	
			Integer idFil = rs.getInt("filamento_id");
			
			f = new Filamento(idFil);
			s = new Scheletro(f);
			
			scheletri.add(s);
			filamento_id = idFil;
			
			}

		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(scheletri.size()==0)
			return (Integer) null;
		
		System.out.println(filamento_id);
		
		return filamento_id;

	}

}

