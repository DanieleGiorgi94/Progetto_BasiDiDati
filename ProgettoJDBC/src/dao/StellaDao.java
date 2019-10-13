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
import java.util.Vector;

import model.Filamento;
import model.Stella;
import model.TipiStella;
import patternConnessione.DMsingleton;
import model.Banda;
import model.Contorno;
import dao.*;

public class StellaDao{
	
	
	public static void insertHerschel(String[] elements, Connection conn) {
	
	    PreparedStatement stmt = null;

		try {
			
			if (conn.isClosed() || conn == null)
				conn = DMsingleton.getConnection();

			stmt=conn.prepareStatement("INSERT INTO Stella(stellaid, flusso, latitudine, longitudine, nome, typestars_name) "+
					"VALUES (?,?,?,?,?,?); ");
			stmt.setInt(1, Integer.parseInt(elements[0]));
			stmt.setDouble(2, Double.parseDouble(elements[4]));
			stmt.setFloat(3, Float.parseFloat(elements[3]));
			stmt.setFloat(4, Float.parseFloat(elements[2]));
			stmt.setString(5, elements[1]);
			stmt.setString(6, elements[5]);

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
	
	
	public static void updateStar(String elements[], Connection conn){

		PreparedStatement stmt = null;

		try {

			if (conn.isClosed() || conn == null)
				conn = DMsingleton.getConnection();

			stmt=conn.prepareStatement("UPDATE Stella "+
					"SET stellaid = ?, flusso = ?, latitudine = ?, longitudine = ?, nome = ?, typestars_name = ? "+
					"WHERE stellaid = ? ");


			stmt.setInt(1, Integer.parseInt(elements[0]));
			stmt.setDouble(2, Double.parseDouble(elements[4]));
			stmt.setFloat(3, Float.parseFloat(elements[3]));
			stmt.setFloat(4, Float.parseFloat(elements[2]));
			stmt.setString(5, elements[1]);
			stmt.setString(6, elements[5]);
			stmt.setInt(7, Integer.parseInt(elements[0]));
		

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

	
	/*REQUISITO 9 */

	public static ArrayList<Stella> getAllStars(Integer filamento_id) throws Exception {
		
		PreparedStatement stmt=null;
	    Connection conn=null;
	    
	    
	    ArrayList<Stella> stars = new ArrayList<Stella>();
	    Stella s = null;
	
	    TipiStella ts = null;
		
		List<Contorno> Contorni = ContornoDao.getContorniperId(filamento_id); // mi faccio restituire una lista di contorni relativa al filamento considerato;
		
		if (Contorni.size() == 0)
			throw new NullPointerException();
		
		conn = DMsingleton.getConnection();
		stmt=conn.prepareStatement("SELECT stellaid, flusso, latitudine, longitudine, nome, typestars_name "+
				"FROM Stella " );

		ResultSet rs=stmt.executeQuery();
		
		while(rs.next()){

			int ID = rs.getInt("stellaid");
			float FlussoVal = rs.getFloat("flusso");
			float LatitudineStella = rs.getFloat("latitudine");
			float LongitudineStella = rs.getFloat("longitudine");
			String Nome = rs.getString("nome");
			String Tipologia = rs.getString("typestars_name");
			
			ts = new TipiStella(Tipologia);

			s = new Stella(ID, ts, Nome, LatitudineStella, LongitudineStella, FlussoVal) ;
			stars.add(s);

		}
		if(stars.size() == 0)
			return null;

		
ArrayList<Stella> stellafilamento = new ArrayList<Stella>();
		
		double sum = 0; // mi serve per calcolare la sommatoria di tutte le arctang
		
		
		int j;
		int i;
		Float C_Li  = (Contorni.get(0).getPuntocontorno().getLongitudine());// longitudine primo punto contorno
		System.out.println(Contorni.get(0).getPuntocontorno().getLongitudine());
		Float C_Bi = (Contorni.get(0).getPuntocontorno().getLatitudine()); // latitudine primo punto contorno considerato
		for (j = 0; j<stars.size();j++){
			Float ST_B = (stars.get(j).getLatitudine()); //Latitudine stella i 
			Float ST_L = (stars.get(j).getLongitudine()); // longitudine stella 
			
			for 	(i = 1; i<Contorni.size()-1; i++) {
			
				Float C_Lii  = (Contorni.get(i).getPuntocontorno().getLongitudine());
				Float C_Bii = (Contorni.get(i).getPuntocontorno().getLatitudine());
				
				
				Float value = (((C_Li-ST_L)*(C_Bii-ST_B)-(C_Bi-ST_B)*(C_Lii-ST_L))/((C_Li-ST_L)*(C_Lii-ST_L)+(C_Bi-ST_B)*(C_Bii-ST_B)));
				double theta = Math.atan(value);
	
				
				sum += Math.abs(theta); // devo considerare il valore assoluto della somma 
				
				C_Li = (Contorni.get(i).getPuntocontorno().getLongitudine());// aggiorno i valori della latitudine del contorno
				C_Bi = (Contorni.get(i).getPuntocontorno().getLatitudine()); // aggiorno valori del contorno della longitudine
				
			}
			
			if (sum >= 0.01)  // se tutta la somma � maggiore di 0.01 allora aggiungo la stella all'interno delle stelle interne al filamento con id passato in funzione
				stellafilamento.add(stars.get(j));
			
			sum = 0;
				
		}
		
		if (stellafilamento.size() == 0)
			return null;
		
		//System.out.println(stellafilamento.size());
		return stellafilamento; 
		
		
	}

	public static Vector<Stella> getObjRectangle(float lat, float longit, float latoA, float latoB) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		Vector<Stella> stars = new Vector<Stella>();
		
		Stella s = null;
		TipiStella ts = null;
		
		conn = DMsingleton.getConnection();
		stmt=conn.prepareStatement("SELECT stellaid, flusso, latitudine, longitudine, nome, typestars_name "+
		"FROM Stella "+
		"WHERE (abs(latitudine - ?)) <= ? and (abs(longitudine - ?)) <= ? ");
		

		stmt.setFloat(1, lat);
		stmt.setFloat(2, latoA/2);
		stmt.setFloat(3, longit);
		stmt.setFloat(4, latoB/2);

		ResultSet rs=stmt.executeQuery();
		
		while(rs.next()){
			
			int ID = rs.getInt("stellaid");
			float FlussoVal = rs.getFloat("flusso");
			float LatitudineStella = rs.getFloat("latitudine");
			float LongitudineStella = rs.getFloat("longitudine");
			String Nome = rs.getString("nome");
			String Tipologia = rs.getString("typestars_name");
			
			ts = new TipiStella(Tipologia);

			s = new Stella(ID, ts, Nome, LatitudineStella, LongitudineStella, FlussoVal) ;
			stars.add(s);

		}		

		if(stars.size()==0)
			return null;
		
		
		return stars;
	}

public static ArrayList<Stella> getAllStarsRectangle(float latitudine, float longitudine, float latoA, float latoB) throws Exception {
		
	List<Stella> Stars ;// stelle all'interno della regione 
	List<Integer> ContorniRectangle ; // Contorni all'interno della regione 
	
	Stars = StellaDao.getObjRectangle(latitudine, longitudine, latoA, latoB);
	System.out.println("Le stelle all'interno del rettangolo sono " + Stars.size());
	ContorniRectangle = ContornoDao.getObjRectangle(latitudine, longitudine, latoA, latoB);
	System.out.println("I filamenti all'interno del rettangolo sono " + ContorniRectangle.size());
	ArrayList<Stella> stellafilamento = new ArrayList<Stella>(); // stelle all'interno del filameto dentro il rettangolo 
	
	int h;
	
	Integer filamento_id = ContorniRectangle.get(0);
	for (h = 1; h < ContorniRectangle.size(); h++) {
			List<Contorno> Contorni = ContornoDao.getContorniperId(filamento_id);
				
			double sum = 0; 
			
			
			int j;
			int i;
			Float C_Li  = (Contorni.get(0).getPuntocontorno().getLongitudine()); 
			Float C_Bi = (Contorni.get(0).getPuntocontorno().getLatitudine()); 
			for (j = 0; j<Stars.size();j++){
				Float ST_B = (Stars.get(j).getLatitudine()); 
				Float ST_L = (Stars.get(j).getLongitudine()); 
				
				for 	(i = 1; i<Contorni.size()-1; i++) {
				
					Float C_Lii  = (Contorni.get(i).getPuntocontorno().getLongitudine());
					Float C_Bii = (Contorni.get(i).getPuntocontorno().getLatitudine());
					
					
					Float value = (((C_Li-ST_L)*(C_Bii-ST_B)-(C_Bi-ST_B)*(C_Lii-ST_L))/((C_Li-ST_L)*(C_Lii-ST_L)+(C_Bi-ST_B)*(C_Bii-ST_B)));
					double theta = Math.atan(value);
		
					
					sum += Math.abs(theta); 
					
					C_Li = (Contorni.get(i).getPuntocontorno().getLongitudine());
					C_Bi = (Contorni.get(i).getPuntocontorno().getLatitudine()); 
					
				}
				
				if (Math.abs(sum) >= 0.01) {
						/*System.out.println(Stars.get(j).getStellaId());
						Thread.sleep(100);
						System.out.println(filamento_id);*/
						stellafilamento.add(0, Stars.get(j));
						Stars.remove(j);
					}
				sum = 0;
			}
			if (Stars.isEmpty())
			{
				return stellafilamento;	// se ho verificato che tutte le stelle sono all'interno del filamento esco dalla funzione e mi faccio ritornare le stelle
				// Non ho bisogno di riscandirmi nuovamente tutti gli id dei filamenti trovati 
			}
			System.out.println("Con il filamento" + filamento_id+"ho verificato che al suo interno ci sono finora" + stellafilamento.size()+ "delle stelle totali");	
			filamento_id = ContorniRectangle.get(h);
		
	}
	System.out.println("Le stelle finali all'interno di un filamento sono " + stellafilamento.size());
	if (stellafilamento.size() == 0)
		return null;
	
	
	return stellafilamento; 

	}
}
		
	