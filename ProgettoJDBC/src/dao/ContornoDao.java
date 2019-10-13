package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;



import patternConnessione.DMsingleton;
import model.*;

public class ContornoDao {

	
	public static void insertHerschel(String[] elements, Connection conn) {
		
		Satellite satellite = new Satellite();
		satellite.setName("Herschel");
		
		PreparedStatement stmt = null;
		//Connection conn2 = null;
		
		System.out.println("INSERT done");

		try {
			
			if (conn == null || conn.isClosed())
				conn = DMsingleton.getConnection();
			
			stmt=conn.prepareStatement("INSERT INTO punto_contorno(longitudine, latitudine) "+
					"VALUES (?,?); ");
			
			stmt.setFloat(1, Float.parseFloat(elements[1]));
			stmt.setFloat(2, Float.parseFloat(elements[2]));
			
			stmt.executeUpdate();
			
			stmt.close();
			
				
			stmt=conn.prepareStatement("INSERT INTO contorno(filamento_id, puntocontorno_longitudine, puntocontorno_latitudine) "+
					"VALUES (?,?,?); ");
			
		
			stmt.setInt(1, Integer.parseInt(elements[0]));
			stmt.setFloat(2, Float.parseFloat(elements[1]));
			stmt.setFloat(3, Float.parseFloat(elements[2]));
			
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
			
			System.out.println("insert done");

			try {
				
				if (conn.isClosed())
						conn = DMsingleton.getConnection();
			
				
				stmt=conn.prepareStatement("INSERT INTO punto_contorno(longitudine, latitudine) "+
						"VALUES (?,?); ");
				
				stmt.setFloat(1, Float.parseFloat(elements[1]));
				stmt.setFloat(2, Float.parseFloat(elements[2]));
				
				stmt.executeUpdate();
				
				stmt.close();
				
					
				stmt=conn.prepareStatement("INSERT INTO contorno(filamento_id, puntocontorno_longitudine, puntocontorno_latitudine) "+
						"VALUES (?,?,?); ");
				
			
				stmt.setInt(1, Integer.parseInt(elements[0]));
				stmt.setFloat(2, Float.parseFloat(elements[1]));
				stmt.setFloat(3, Float.parseFloat(elements[2]));
				
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
	
	public static void updateOutlines(String[] elements, Connection conn) {
		PreparedStatement stmt=null;
		
		System.out.println("update done");
		
		try {
			
			if (conn.isClosed() || conn == null)
				conn = DMsingleton.getConnection();

		
			stmt=conn.prepareStatement("UPDATE Punto_contorno "+
			                           "SET longitudine = ?, latitudine = ? "+
					"WHERE longitudine = ? and latitudine = ?" );


			stmt.setFloat(1, Float.parseFloat(elements[1]));
			stmt.setFloat(2, Float.parseFloat(elements[2]));
			stmt.setFloat(3, Float.parseFloat(elements[1]));
			stmt.setFloat(4, Float.parseFloat(elements[2]));


			stmt.executeUpdate();

			stmt.close();


			stmt=conn.prepareStatement("UPDATE Contorno "+
					"SET filamento_id = ?, puntocontorno_longitudine = ?, puntocontorno_latitudine = ? "+
					"WHERE filamento_id = ? and puntocontorno_longitudine = ? and puntocontorno_latitudine = ?" );

			
			stmt.setInt(1, Integer.parseInt(elements[0]));
			stmt.setFloat(2, Float.parseFloat(elements[1]));
			stmt.setFloat(3, Float.parseFloat(elements[2]));
			stmt.setInt(4, Integer.parseInt(elements[0]));
			stmt.setFloat(5, Float.parseFloat(elements[1]));
			stmt.setFloat(6, Float.parseFloat(elements[2]));


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
	


	public static double[] getEstensione(Integer id){

		PreparedStatement stmt = null;
		Connection conn = DMsingleton.getConnection();

		ArrayList<float[]> maxLat = new ArrayList<float[]>();
		ArrayList<float[]> minLat = new ArrayList<float[]>();
		ArrayList<float[]> maxLong = new ArrayList<float[]>();
		ArrayList<float[]> minLong = new ArrayList<float[]>();
		
		Punto_contorno pc = new Punto_contorno();


		try {
			/*LONGITUDINE MASSIMA */
			
			/*Selezioniamo tutti i punti del contorno relativi a quel filamento*/
			stmt=conn.prepareStatement("SELECT puntocontorno_latitudine, puntocontorno_longitudine "+
					"FROM contorno "+
					"WHERE puntocontorno_longitudine in "+
					"(SELECT max(puntocontorno_longitudine) "+
					"FROM contorno "+
					"WHERE filamento_id = ?) ");

			stmt.setInt(1, id);

			ResultSet rs1 = stmt.executeQuery();

			while(rs1.next()){

				float latitudine = rs1.getFloat("puntocontorno_latitudine");
				float longitudine = rs1.getFloat("puntocontorno_longitudine");
				
				
				float infoContorno[] = new float[2];
				infoContorno[0]= latitudine;
				infoContorno[1] = longitudine;
				maxLong.add(infoContorno);
				break;
			}
			

			rs1.close();

			/* LATITUDINE MASSIMA */
		
			stmt=conn.prepareStatement("SELECT puntocontorno_latitudine, puntocontorno_longitudine "+
					"FROM contorno "+
					"WHERE puntocontorno_latitudine in "+
					"(SELECT max(puntocontorno_latitudine) " +
					"FROM contorno "+
					"WHERE filamento_id = ?) ");
			
			stmt.setInt(1, id);

			ResultSet rs2=stmt.executeQuery();
			while(rs2.next()){

				float latitudine = rs2.getFloat("puntocontorno_latitudine");
				float longitudine = rs2.getFloat("puntocontorno_longitudine");

				float infoContorno[] = new float[2];
				infoContorno[0]= latitudine;
				infoContorno[1] = longitudine;
				maxLat.add(infoContorno);				
				break;
			}

			rs2.close();


			/* LONGITUDINE MINIMA */
			
			stmt=conn.prepareStatement("SELECT puntocontorno_latitudine, puntocontorno_longitudine "+
					"FROM contorno "+
					"WHERE puntocontorno_longitudine in "+
					"(SELECT min(puntocontorno_longitudine) " +
					"FROM contorno "+
					"WHERE filamento_id = ?) ");
			
			stmt.setInt(1, id);

			ResultSet rs3=stmt.executeQuery();
			while(rs3.next()){

				float latitudine = rs3.getFloat("puntocontorno_latitudine");
				float longitudine = rs3.getFloat("puntocontorno_longitudine");

				float infoContorno[] = new float[2];
				infoContorno[0]= latitudine;
				infoContorno[1] = longitudine;
				minLong.add(infoContorno);				
				break;
			}

			rs3.close();

			/* LATITUDINE MINIMA */
			stmt=conn.prepareStatement("SELECT puntocontorno_latitudine, puntocontorno_longitudine "+
					"FROM contorno "+
					"WHERE puntocontorno_latitudine in "+
					"(SELECT min(puntocontorno_latitudine) " +
					"FROM contorno "+
					"WHERE filamento_id = ?) ");
			
			stmt.setInt(1, id);

			ResultSet rs4=stmt.executeQuery();
			while(rs4.next()){

				float latitudine = rs4.getFloat("puntocontorno_latitudine");
				float longitudine = rs4.getFloat("puntocontorno_longitudine");

				float infoContorno[] = new float[2];
				infoContorno[0]= latitudine;
				infoContorno[1] = longitudine;
				minLat.add(infoContorno);				
				break;
			}

			rs4.close();
			stmt.close();
			conn.close();


		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}

		
		double dist[] = new double[2];
		
		System.out.println(maxLat.get(0)[0]);

		dist[0] = pc.getDistance(maxLat.get(0)[0], maxLat.get(0)[1], minLat.get(0)[0], minLat.get(0)[1]);

		dist[1] = pc.getDistance(maxLong.get(0)[0], maxLong.get(0)[1], minLong.get(0)[0], minLong.get(0)[1]);


		return dist;


	}

		public static List<Contorno> getContorniperId (Integer filamento_id) throws Exception{

			PreparedStatement stmt = null;
			Connection conn = null;
			
			List<Contorno> Contorni = new ArrayList<Contorno>();
			
			Contorno c = null;
			Punto_contorno pc = null;
			Filamento f = new Filamento(filamento_id);
	

			try {
				conn = DMsingleton.getConnection();
				
				/*Selezioniamo tutti i punti del contorno relativi a quel filamento*/
				stmt=conn.prepareStatement("SELECT puntocontorno_longitudine,puntocontorno_latitudine "+
						"FROM contorno "+
						"WHERE filamento_id = ? ") ;

				stmt.setInt(1,filamento_id);
				

				ResultSet rs1=stmt.executeQuery();

				while(rs1.next()){
					float LongitudinePunto = rs1.getFloat("puntocontorno_longitudine");
					float LatitudinePunto = rs1.getFloat("puntocontorno_latitudine");
					
					pc = new Punto_contorno(LatitudinePunto, LongitudinePunto);
					c = new Contorno(pc, f);

					
					Contorni.add(c);

				}

				rs1.close();
				stmt.close();
				conn.close();

			}catch (SQLException se) {
				se.printStackTrace();
			} finally {

				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			}
			return Contorni;
		}
		
		
		public static Vector<Filamento> getObjCircle(float lat, float longit, float raggio) {
			Connection conn = null;
			PreparedStatement stmt = null;
			
			Vector<Filamento> filaments = new Vector<Filamento>();
			
			Filamento f = null;
			Punto_contorno pc = null;

			
			try {
				conn = DMsingleton.getConnection();
				/*Selezioniamo tutti i punti del contorno relativi a quel filamento*/
				stmt=conn.prepareStatement("SELECT f.id, f.nome FROM Filamento f "+
							"join contorno c ON f.id = c.filamento_id "+
							"WHERE sqrt(power(puntocontorno_latitudine - ?, 2) + power(puntocontorno_longitudine - ?,2)) <= ? group by f.id having count(distinct f.id) >= 1 ") ;

				stmt.setFloat(1, lat);
				stmt.setFloat(2, longit);
				stmt.setFloat(3, raggio);

				ResultSet rs1 = stmt.executeQuery();

				while(rs1.next()){

					int idFil = rs1.getInt("id");
					String nomeFil = rs1.getString("nome");

					f = new Filamento(idFil, nomeFil);
	
					filaments.add(f);
				}

			if(filaments.size()==0)
				return null;

			System.out.println(filaments.size());
	
			
			
			}catch(SQLException se){
				se.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
				return filaments;
		}




		public static Vector<Filamento> getObjSquare(float lat, float longit, float lato) {
			
		
			Connection conn = null;
			PreparedStatement stmt = null;
			
			Vector<Filamento> filaments = new Vector<Filamento>();
			
			Filamento f = null;
			
			try {
				conn = DMsingleton.getConnection();
				/*Selezioniamo tutti i punti del contorno relativi a quel filamento*/
				stmt=conn.prepareStatement("SELECT f.id, f.nome FROM Filamento f "+
							"join contorno c ON f.id = c.filamento_id "+
							"WHERE ((abs(c.puntocontorno_latitudine - ?))<(?*(sqrt(2)/2)) and (abs(c.puntocontorno_longitudine-?))<(?*(sqrt(2)/2))) group by f.id having count(distinct f.id) >= 1 ") ;

				stmt.setFloat(1, lat);
				stmt.setFloat(2, longit);
				stmt.setFloat(3, lato);
				stmt.setFloat(4, lato);

				ResultSet rs1 = stmt.executeQuery();

				while(rs1.next()){

					int idFil = rs1.getInt("id");
					String nomeFil = rs1.getString("nome");

					f = new Filamento(idFil, nomeFil);
	
					filaments.add(f);
				}

			if(filaments.size()==0)
				return null;

			System.out.println(filaments.size());
	
			
			
			}catch(SQLException se){
				se.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
				return filaments;
		}
	
		

		public static List<Contorno> getAllContorni() {
			
			Connection conn = null;
			PreparedStatement stmt = null;
			
			List<Contorno> Contorni = new ArrayList<Contorno>();
			
			Contorno c = null;
			Filamento f = null;
			Punto_contorno pc = null;
			
			
			try {
				conn = DMsingleton.getConnection();
				/*Selezioniamo tutti i punti del contorno relativi a quel filamento*/
				stmt=conn.prepareStatement(
						"SELECT puntocontorno_latitudine, puntocontorno_longitudine, filamento_id "+
								"From Contorno "+
								"order by filamento_id ") ;


				ResultSet rs1 = stmt.executeQuery();

				while(rs1.next()){

					float latitudine = rs1.getFloat("puntocontorno_latitudine");
					float longitudine = rs1.getFloat("puntocontorno_longitudine");
					int idFilamento = rs1.getInt("filamento_id");

					f = new Filamento(idFilamento);
					pc = new Punto_contorno(latitudine, longitudine);
					c = new Contorno (pc, f);
					
					Contorni.add(c);
				}

				if(Contorni.size()==0)
					return null;

			}catch(SQLException se){
				se.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}

			return Contorni;

			
		}
		
			

		public static List<Integer> getObjRectangle(float lat, float longit, float latoA, float latoB) { // mi restituisce i contorni e di conseguenza i filamenti all'interno della regiome 
			
			Connection conn = null;
			PreparedStatement stmt = null;
			
			List<Integer> ListContorno = new ArrayList<Integer>();
	
			
			try {
				
				conn = DMsingleton.getConnection();
				/*Selezioniamo tutti i punti del contorno relativi a quel filamento*/
				stmt=conn.prepareStatement("SELECT filamento_id "+
						"FROM Contorno "+
						"WHERE (abs(puntocontorno_latitudine - ?)) <= ? and (abs(puntocontorno_longitudine - ?)) <= ? group by filamento_id having count(distinct filamento_id) >=1 order by filamento_id ") ;

				stmt.setFloat(1, lat);
				stmt.setFloat(3, longit);
				stmt.setFloat(2, latoA);
				stmt.setFloat(4, latoB);

				ResultSet rs1 = stmt.executeQuery();
			

				while(rs1.next()){


					int idFilamento = rs1.getInt("filamento_id");
					
					ListContorno.add(idFilamento);
				}

			if(ListContorno.size()==0)
				return null;

			
			}catch(SQLException se){
				se.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}

			return ListContorno;
		}

		public static float[] getCentroide(Integer id) throws Exception {


			List<Contorno> puntiContorno = new ArrayList<Contorno>();

			puntiContorno = ContornoDao.getContorniperId(id);
			

			if(puntiContorno.size() == 0)
				return null;


			float sumLat = 0;

			for (int i=0; i<puntiContorno.size(); i++) {
				sumLat = (float) (sumLat+ ((puntiContorno.get(i).getPuntocontorno().getLatitudine()))); 
			}


			float avgLat = sumLat/ puntiContorno.size();



			float sumLong = 0;

			for (int i=0; i<puntiContorno.size(); i++) {
				sumLong = (float) (sumLong + ((puntiContorno.get(i).getPuntocontorno().getLongitudine()))); 
			}

			float avgLong = sumLong / puntiContorno.size();

			float centroide[] = new float[2];

			centroide[0] = avgLat;
			centroide[1] = avgLong;

			return centroide;


		}


	}
