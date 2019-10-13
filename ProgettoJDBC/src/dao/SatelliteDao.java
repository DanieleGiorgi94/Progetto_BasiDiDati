package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import exception.DataObbligatoriaException;
import exception.IllegalLastObsException;
import exception.SatelliteEsistenteException;
import model.*;
import patternConnessione.DMsingleton;


public class SatelliteDao {

	public static void insertSatellite(String nomeSatellite, String primaOp, String agenzia) throws SatelliteEsistenteException, SQLException {

		Connection conn = DMsingleton.getConnection();
		PreparedStatement stmt = null;
		
		Satellite satellite = new Satellite();
		Agenzia ag = null;
		
		Vector<Agenzia> v = new Vector<Agenzia>(1);
		

		stmt=conn.prepareStatement("SELECT name "+
				"FROM Satellite "+
				"WHERE name = ? ");

		stmt.setString(1,nomeSatellite);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			throw new SatelliteEsistenteException();
		}
		
		rs.close();

		if(agenzia != null){
			stmt=conn.prepareStatement("SELECT * "+
					"FROM Agenzia "+
					"WHERE name = ? ");

			stmt.setString(1, agenzia);

			ResultSet rs2 = stmt.executeQuery();

			while (rs2.next()){		
				String nome = rs.getString("name");
				
				ag = new Agenzia(nome);
				v.add(ag);
				satellite.setSpacialAgencies(v);
			}
			
			rs2.close();
		}
		
		stmt = conn.prepareStatement("INSERT INTO Satellite (name, firstobservation) "+
				"VALUES(?, ?) ");
		
		stmt.setString(1, nomeSatellite);
		stmt.setString(2, primaOp);
		
		stmt.executeUpdate();
		/*
		stmt = conn.prepareStatement("INSERT INTO satellite_agenzia"+
				"VALUES(?,?) ");
		
		stmt.setString(1, nomeSatellite);
		stmt.setString(2, v.get(0).getName());*/
		
		stmt.close();
		conn.close();
					
	}
	
	public static List<Satellite> getSatelliti() throws SQLException{
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String nomeSat = null;
		Collection<Strumento> tools = new ArrayList<Strumento>();
		
		Strumento tool = null;
		Satellite satellite = null;
		
		//publiString nome;
		
		List<Satellite> Satelliti =  new ArrayList<Satellite>();
		
		conn = DMsingleton.getConnection();
		
		stmt = conn.prepareStatement("SELECT * from Satellite ");
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			String nome = rs.getString("name");
			String durata = rs.getString("duration");
			String firstObs = rs.getString("firstobservation");
			String lastObs = rs.getString("lastobservation");
			
			satellite = new Satellite(nome, durata, firstObs, lastObs);
			Satelliti.add(satellite);
			
			nomeSat = nome;
		}
		
		rs.close();
		
		stmt = conn.prepareStatement("SELECT toolid "+
				"FROM strumento "+
				"WHERE satellite_name = ? ");
		
		stmt.setString(1, nomeSat);
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			String nomeStrumento = rs.getString("toolid");
			
			satellite = new Satellite(nomeSat);
			tool = new Strumento(nomeStrumento, satellite);
			
			tools.add(tool);
			satellite.setTools(tools);
			
		}
		
		stmt.close();
		conn.close();
		
		if(Satelliti.size() == 0){
			return null;
		}else{
			return Satelliti;
		}	
	}
	
	public static Satellite getSatelliteDaVisualizzare(String Satellite) throws SQLException{
		
		Connection conn = DMsingleton.getConnection();
		PreparedStatement stmt = null;
		
		Satellite satellite = null;
		
		stmt = conn.prepareStatement("SELECT * from Satellite "+
				"WHERE name = ? ");
		stmt.setString(1, Satellite);
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			String nome = rs.getString("name");
			String durata = rs.getString("duration");
			String firstObs = rs.getString("firstobservation");
			String lastObs = rs.getString("lastobservation");
			
			satellite = new Satellite(nome, durata, firstObs, lastObs);
		
		}
		
		return satellite;
		
	}
	
	public static void insertLastObs(String idSatellite, String lastObs) throws DataObbligatoriaException, IllegalLastObsException, SQLException{
		
		Connection conn = DMsingleton.getConnection();
		PreparedStatement stmt = null;
		
		Satellite sat = null;
		
		stmt = conn.prepareStatement("UPDATE Satellite "+
				"SET lastobservation = ? "+
				"WHERE name = ? ");
		
		stmt.setString(1, lastObs);
		stmt.setString(2, idSatellite);
		
		stmt.executeUpdate();
		stmt.close();
		
		stmt = conn.prepareStatement("SELECT * from Satellite "+
				"WHERE name = ? ");
		stmt.setString(1, idSatellite);
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			String nome = rs.getString("name");
			String durata = rs.getString("duration");
			String firstObs = rs.getString("firstobservation");
			String lastObservation = rs.getString("lastobservation");
			
			sat = new Satellite(nome, durata, firstObs, lastObservation);
			
		}
		
		stmt.close();
		rs.close();
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			
			/*Trasformo in data la prima operazione*/
			Date first = formatter.parse(sat.getFirstObservation());
			Calendar c = new GregorianCalendar();
			c.setTime(first);
			
			/*Trasformo in data l'ultima operazione*/
			Date last = formatter.parse(lastObs);
			Calendar c1 = new GregorianCalendar();
			c1.setTime(last);
			
			/*Faccio la differenza in millisecondi per ottenere il numero di giorni tra le due date*/
			Long differenza = c1.getTimeInMillis() - c.getTimeInMillis();
			
			Double d = differenza/ 86400000.0;
			if(d<0){
				throw new IllegalLastObsException();
			}
			Integer durata = d.intValue();
			
			stmt = conn.prepareStatement("UPDATE Satellite "+
					"SET duration = ? "+
					"WHERE name = ? ");
			stmt.setString(1, d.toString());
			stmt.setString(2, sat.getName());
			
			stmt.executeUpdate();
			
		} catch (IllegalLastObsException ie) {
			throw ie;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public static void insertAgenzia(String idSatellite, String agenzia)  {

		PreparedStatement stmt = null;
		Connection conn = null;
		
		try {
			
			
		conn= DMsingleton.getConnection();
		
		
		stmt = conn.prepareStatement("SELECT name from Satellite "+
				"WHERE name = ? ");
		stmt.setString(1, idSatellite);
		
		ResultSet rs = stmt.executeQuery();
		
		rs.next();
		String nomeSatellite = rs.getString("name");
		
		
		rs.close();
		stmt.close();
			
		stmt=conn.prepareStatement("SELECT name "+
				"FROM Agenzia "+
				"WHERE name = ? ");
		

		stmt.setString(1, agenzia);
		ResultSet rs2 = stmt.executeQuery();
		
		rs2.next();
		String nomeAgenzia = rs2.getString("name");
		
		rs2.close();
		stmt.close();
		
		stmt = conn.prepareStatement("INSERT INTO satellite_agenzia (satellites_name, spacialagencies_name) "+
				"VALUES (?,?) ");
		stmt.setString(1, nomeSatellite);
		stmt.setString(2, nomeAgenzia);
		
		stmt.executeUpdate();
		
		stmt.close();
		conn.close();
		
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public static List<Agenzia> findAgenzie() throws SQLException{

		PreparedStatement stmt = null;
		Connection conn = DMsingleton.getConnection();
		
		Agenzia a = null;
		List <Agenzia> vAg = new ArrayList<Agenzia>();
		
		stmt=conn.prepareStatement("SELECT * "+
				"FROM Agenzia ");

		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			String nomeAgenzia = rs.getString("name");
			a = new Agenzia(nomeAgenzia);
			vAg.add(a);
			
		}
		
		rs.close();
		stmt.close();
		conn.close();

		if(vAg.size() == 0){
			return null;
		}else{
			return vAg;
		}	
	}
}