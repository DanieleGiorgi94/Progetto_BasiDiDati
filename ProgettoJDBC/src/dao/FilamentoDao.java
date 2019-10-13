package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.*;

import model.Satellite;
import model.Filamento;
import patternConnessione.DMsingleton;


public class FilamentoDao {

	public static void insertHerschel(String[] elements, Connection conn){
		
		Satellite satellite = new Satellite();
		satellite.setName("Herschel");
		
		PreparedStatement stmt = null;
		
		System.out.println(elements[0]+elements[1]+elements[2]+elements[3]+elements[4]+elements[5]+elements[6]+elements[7]+elements[8]);
		try {
			
			if (conn.isClosed() || conn == null) 
				conn = DMsingleton.getConnection();
			

			stmt=conn.prepareStatement("INSERT INTO filamento(id, nome, flussototale, densitamedia, temperaturamedia, ellitticita, contrasto, strumento_satellite_name, strumento_toolid) "+
					"VALUES (?,?,?,?,?,?,?,?,?); ");

		
			stmt.setInt(1, Integer.parseInt(elements[0]));
			stmt.setString(2, elements[1]);
			stmt.setDouble(3, Double.parseDouble(elements[2]));
			stmt.setDouble(4, Double.parseDouble(elements[3]));
			stmt.setFloat(5, Float.parseFloat(elements[4]));
			stmt.setFloat(6, Float.parseFloat(elements[5]));
			stmt.setFloat(7, Float.parseFloat(elements[6]));
			stmt.setString(8, elements[7]);
			stmt.setString(9, elements[8]);
			
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
		
	Satellite satellite = new Satellite();
	satellite.setName("Spitzer");
	
	PreparedStatement stmt = null;
	
	System.out.println(elements[0]+elements[1]+elements[2]+elements[3]+elements[4]+elements[5]+elements[6]+elements[7]+elements[8]);

	try {
		

		stmt=conn.prepareStatement("INSERT INTO filamento(id, nome, flussototale, densitamedia, temperaturamedia, ellitticita, contrasto, strumento_satellite_name, strumento_toolid) "+
				"VALUES (?,?,?,?,?,?,?,?,?); ");

	
		stmt.setInt(1, Integer.parseInt(elements[0]));
		stmt.setString(2, elements[1]);
		stmt.setDouble(3, Double.parseDouble(elements[2]));
		stmt.setDouble(4, Double.parseDouble(elements[3]));
		stmt.setFloat(5, Float.parseFloat(elements[4]));
		stmt.setFloat(6, Float.parseFloat(elements[5]));
		stmt.setFloat(7, Float.parseFloat(elements[6]));
		stmt.setString(8, elements[7]);
		stmt.setString(9, elements[8]);
		
		
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

public static void updateFilaments(String[] elements, Connection conn) {
	PreparedStatement stmt=null;
	
	
	try {
		
		if (conn.isClosed())
			conn = DriverManager.getConnection("jdbc:postgresql:ProgettoBasi17/18","postgres","DanieleG1994");
		
		stmt=conn.prepareStatement("UPDATE Filamento "+
		                           "SET id = ?, nome = ?, flussototale = ?, densitamedia = ?, temperaturamedia = ?, ellitticita = ?, contrasto = ?, strumento_satellite_name = ?, strumento_toolid = ? "+
								   "WHERE id = ? ");
								   
		
		stmt.setInt(1, Integer.parseInt(elements[0]));
		stmt.setString(2, elements[1]);
		stmt.setDouble(3, Double.parseDouble(elements[2]));
		stmt.setDouble(4, Double.parseDouble(elements[3]));
		stmt.setFloat(5, Float.parseFloat(elements[4]));
		stmt.setFloat(6, Float.parseFloat(elements[5]));
		stmt.setFloat(7, Float.parseFloat(elements[6]));
		stmt.setString(8, elements[7]);
		stmt.setString(9, elements[8]);
		stmt.setInt(10, Integer.parseInt(elements[0]));
		
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

public static  Filamento getFilamenti(Integer id, String nome) {
	
	Connection conn = DMsingleton.getConnection();
	PreparedStatement stmt = null;
	
	Filamento f = null;
	
	try {
		stmt=conn.prepareStatement("SELECT id, nome, flussototale, densitamedia, temperaturamedia, ellitticita, contrasto "+
	"FROM filamento "+
	"WHERE id = ? and nome= ? ");
		
	stmt.setInt(1,id);
	stmt.setString(2,nome);
	
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
	}


	rs.close();
	conn.close();



	}catch(SQLException se){
		se.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
	}


	return f;
}

public static Filamento getFilamentiPerNome(String nome) {
	Connection conn = null;
	PreparedStatement stmt = null;
	
	Filamento f = null;
	
	try {
		
		conn = DMsingleton.getConnection();
		
		stmt=conn.prepareStatement("SELECT id, nome, flussototale, densitamedia, temperaturamedia, ellitticita, contrasto "+
	"FROM filamento "+
	"WHERE nome = ? ");
		
	stmt.setString(1,nome);

	
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
	}


	rs.close();
	conn.close();



	}catch(SQLException se){
		se.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	return f;
}

public static Filamento getFilamentiPerId(Integer id) {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	
	Filamento f = null;

		
	try {
		conn = DMsingleton.getConnection();
		
		stmt=conn.prepareStatement("SELECT id, nome, flussototale, densitamedia, temperaturamedia, ellitticita, contrasto "+
				"FROM public.filamento "+
				"WHERE id = ? ");
				
		stmt.setInt(1, id);

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
		
		
	}

	rs.close();
	conn.close();
	


	}catch(SQLException se){
		se.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	
	return f;
	
}

public static List <Filamento> getAllFilamenti() {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	
	Filamento f = null;
	List <Filamento> Filamenti = new ArrayList<Filamento>();
	
	try {
		
		conn = DMsingleton.getConnection();
		
		stmt=conn.prepareStatement("SELECT id, contrasto, densitamedia, flussototale, temperaturamedia, ellitticita, nome "+
	"FROM Filamento");
		

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
		Filamenti.add(f);
	}


	rs.close();
	conn.close();



	}catch(SQLException se){
		se.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	return Filamenti;
}

public static List <Filamento> ricercaperellitticitaebrillanza(float index1,float index2, float brillanza) {

	Connection conn = null;
	PreparedStatement stmt = null;

	Filamento f = null;
	List <Filamento> Filamenti = new ArrayList <Filamento>();

	try {
		
		conn = DMsingleton.getConnection();

		stmt=conn.prepareStatement("SELECT id, nome, flussototale, densitamedia, temperaturamedia, ellitticita, contrasto "+
				"FROM Filamento "+
				"WHERE ellitticita between ? and ? and contrasto > (1.0 + ?) order by id ");
		
		stmt.setFloat(1, index1);
		stmt.setFloat(2, index2);
		stmt.setFloat(3, brillanza/100);


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
			Filamenti.add(f);
		}


		rs.close();
		conn.close();
		
		if (Filamenti.size() == 0) 
			return null;
		

	}catch(SQLException se){
		se.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	return Filamenti;

}

//NECESSARIO PER IL REQUISITO 6 -> MI VELOCIZZA IL CALCOLO DEL CATALOGO DEI FILAMENTI
public static Long getCountAllFilamenti() throws SQLException {
	// TODO Auto-generated method stub
	
	Connection conn = DMsingleton.getConnection();
	PreparedStatement stmt = null;
	
	Long qFilamenti = (long) 0;
	
	stmt = conn.prepareStatement("SELECT count(*) FROM filamento ");
	ResultSet rs = stmt.executeQuery();
	
	if (rs.next())
		qFilamenti = rs.getLong("count");
	
	if (qFilamenti != 0)
		return qFilamenti;
	
	return null;
	
}

}

