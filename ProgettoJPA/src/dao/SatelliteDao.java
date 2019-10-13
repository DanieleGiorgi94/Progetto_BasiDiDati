package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import exception.DataObbligatoriaException;
import exception.IllegalLastObsException;
import exception.SatelliteEsistenteException;
import model.*;
import patternConnessione.EMSingleton;

public class SatelliteDao {

	public static void insertSatellite(String nomeSatellite, String primaOp, String agenzia) throws SatelliteEsistenteException {

		EntityManager em = EMSingleton.getIstance();
		
		Satellite satellite = new Satellite();
		
		Vector<Agenzia> v = new Vector<Agenzia>(1);
		
		if(em.find(Satellite.class, nomeSatellite) != null){
			throw new SatelliteEsistenteException();
		}
		if(agenzia != null){
			Agenzia agenziaObj = em.find(Agenzia.class, agenzia);
			v.add(0, agenziaObj);
			satellite.setSpacialAgencies(v);
		}

		
		satellite.setName(nomeSatellite);
		satellite.setFirstObservation(primaOp);

		try {
			em.getTransaction().begin();
			em.merge(satellite);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
	}
	
	public static List<Satellite> getSatelliti(){
		
		EntityManager em = EMSingleton.getIstance();
		Query query=em.createNamedQuery("Satelliti");
		List<Satellite> Satelliti = query.getResultList();
		if(Satelliti.size() == 0){
			return null;
		}else{
			return Satelliti;
		}	
	}
	
	public static Satellite getSatelliteDaVisualizzare(String Satellite){
		
		EntityManager em = EMSingleton.getIstance();
		return em.find(Satellite.class, Satellite);
	}
	
	public static void insertLastObs(String idSatellite, String lastObs) throws DataObbligatoriaException, IllegalLastObsException{
		
		EntityManager em = EMSingleton.getIstance();
		Satellite sat = em.find(Satellite.class, idSatellite);
		
		sat.setLastObservation(lastObs);
		
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
			sat.setDuration(durata.toString());
			
			em.getTransaction().begin();
			em.merge(sat);
			em.getTransaction().commit();
			
		} catch (IllegalLastObsException ie) {
			throw ie;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
	}

	public static void insertAgenzia(String idSatellite, String agenzia) {
		EntityManager em = EMSingleton.getIstance();
		Satellite sat = em.find(Satellite.class, idSatellite);
		
		Vector<Agenzia> vAg = new Vector<Agenzia>(1);
		Agenzia ag = em.find(Agenzia.class, agenzia);
		vAg.add(ag);
		
		sat.setSpacialAgencies(vAg);
	}

	public static List<Agenzia> findAgenzie(){
		EntityManager em = EMSingleton.getIstance();
		
		Query query=em.createNamedQuery("Agenzie");
		List<Agenzia> agenzie = query.getResultList();
		if(agenzie.size() == 0){
			return null;
		}else{
			return agenzie;
		}	
	}
}