package dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import patternConnessione.EMSingleton;
import model.*;

//REQUISITO 4 : IMPORTAZIONE DI UN FILE CONTORNO DI TIPO HERSCHEL
public class ContornoDao {
	
	Punto_contorno puntocontorno = new Punto_contorno();
	Filamento filamento = new Filamento();
	
	public static void insertHerschel(String[] elements) {
		
		EntityManager em = EMSingleton.getIstance();
		
		try {
		
			Satellite satellite = new Satellite();
			Strumento strumento = new Strumento();
			satellite.setName("Herschel");
			Punto_contorno puntocontorno = new Punto_contorno();
			strumento.setSatellite(satellite);
			Filamento filamento = new Filamento();
			filamento.setStrumento(strumento);
		
			// System.out.println("id" + elements[0] + "long"+elements[1]+"lat"+elements[2]);
			
			filamento.setId(Integer.parseInt(elements[0]));
			puntocontorno.setLatitudine(Float.parseFloat(elements[1]));
			puntocontorno.setLongitudine(Float.parseFloat(elements[2]));
	
	
			
			em.getTransaction().begin();
			
			em.merge(puntocontorno);
		
			em.getTransaction().commit();
			
			//ContornoDao Contorno;
			//Contorno = new ContornoDao(puntocontorno,filamento);
	
			Contorno contorno = new Contorno();
			contorno.setFilamento(filamento);
			contorno.setPuntocontorno(puntocontorno);
			em.merge(contorno);
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException();
			
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			// EMSingleton.close();
		}

	}
		
	public  ContornoDao(Punto_contorno puntocontorno,Filamento filamento){
		Contorno contorno = new Contorno();
		this.puntocontorno = puntocontorno;
		this.filamento = filamento;
		
		contorno.setFilamento(filamento);
		contorno.setPuntocontorno(puntocontorno);
		
		
		
	}

//REQUISITO 4 : IMPORTAZIONE DI UN FILE CONTORNO DI TIPO SPITZER
public static void insertSpitzer(String[] elements) {
		
		EntityManager em = EMSingleton.getIstance();
		
		try {
			
			Satellite satellite = new Satellite();
			Strumento strumento = new Strumento();
			satellite.setName("Spitzer");
			Punto_contorno puntocontorno = new Punto_contorno();
			strumento.setSatellite(satellite);
			Filamento filamento = new Filamento();
			filamento.setStrumento(strumento);
		
			// System.out.println("id" + elements[0] + "long"+elements[1]+"lat"+elements[2]);
			
			filamento.setId(Integer.parseInt(elements[0]));
			puntocontorno.setLatitudine(Float.parseFloat(elements[1]));
			puntocontorno.setLongitudine(Float.parseFloat(elements[2]));
	
	
			
			em.getTransaction().begin();
			
			em.merge(puntocontorno);
		
			em.getTransaction().commit();
			
	
			Contorno contorno = new Contorno();
			contorno.setFilamento(filamento);
			contorno.setPuntocontorno(puntocontorno);
			em.merge(contorno);
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException();
			
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			// EMSingleton.close();
		}

	}
	
//REQUISITO 5: RECUPERO INFORMAZIONI DERIVATE DI UN FILAMENTO -> CALCOLO ESTENSIONE 
public static double[] getEstensione(Integer id) {
	
	EntityManager em = EMSingleton.getIstance();
	
	TypedQuery<Contorno> contornoQuery = em.createQuery("SELECT c FROM Contorno c WHERE c.filamento.id = ?1", Contorno.class);
	contornoQuery.setParameter(1, id);
	List<Contorno> outline = contornoQuery.getResultList();
	
	Punto_contorno pc = new Punto_contorno();
	
	
	
	if (outline.size() == 0) {
		return null;
	}
	
	//CALCOLO PUNTO CON LATITUDINE MASSIMA
	
	float maxLat = -1000;
	float longit1 = 0;
	
	for (int i = 0; i <outline.size(); i++) {
		if ((outline.get(i).getPuntocontorno().getLatitudine()) > maxLat) {
			maxLat = (outline.get(i).getPuntocontorno().getLatitudine());
			longit1 =(outline.get(i).getPuntocontorno().getLongitudine());
			
		}
			
	}
	
	
	// CALCOLO LONGITUDINE MASSIMA
	float maxLong = -1000;
	float lat1 = 0;
	
	for (int i = 0; i <outline.size(); i++) {
		if ((outline.get(i).getPuntocontorno().getLongitudine()) > maxLong) {
			maxLong = (outline.get(i).getPuntocontorno().getLongitudine());
			lat1 = (outline.get(i).getPuntocontorno().getLatitudine());
		}
			
	}
	
	
	//CALCOLO LATITUDINE MINIMA 
	float minLat = maxLat;
	float longit2 = 0;
	
	for (int i = 0; i <outline.size(); i++) {
		if ((outline.get(i).getPuntocontorno().getLatitudine()) < minLat) {
			minLat = (outline.get(i).getPuntocontorno().getLatitudine());
			longit2 = (outline.get(i).getPuntocontorno().getLongitudine());
			
		}
			
	}
	
	
	//CALCOLO LONGITUDINE MINIMA
	float minLong = maxLong;
	float lat2 = 0;
	
	for (int i = 0; i <outline.size(); i++) {
		if ( (outline.get(i).getPuntocontorno().getLongitudine()) < minLong) {
			minLong = (outline.get(i).getPuntocontorno().getLongitudine());
			lat2 = (outline.get(i).getPuntocontorno().getLatitudine());
		}
			
	}
	
	double dist[] = new double[2];
	
	dist[0] = pc.getDistance(maxLat, longit1, minLat, longit2);
	
	dist[1] = pc.getDistance(lat1, maxLong, lat2, minLong);
	
	
	return dist;
	

}

// OTTENGO I PUNTI DEL CONTORNO (QUINDI IL CONTORNO) RELATIVI AL FILAMENTO PASSATO DA PARAMETRO
public static List<Contorno> getContorniperId (Integer filamento_id){
	
	EntityManager em = EMSingleton.getIstance();
	
	TypedQuery<Contorno> cnt = em.createQuery("SELECT c from Contorno c where c.filamento.id = ?1 order by c.filamento.id", Contorno.class);
	cnt.setParameter(1,filamento_id);
	
	List<Contorno> Contorni = cnt.getResultList();
	
	if(Contorni.size()==0)
		return null;
	
	return Contorni;
}

//REQUISITO 8: RICERCA DI UN OGGETTO ALL'INTERNO DI UNA REGIONE ->  OTTENGO TUTTI I FILAMENTI INTERNI ALLA REGIONE QUADRATO DI LATO L, LATITUDINE E LONGITUDINE TUTTI PASSATI COME PARAMETRI ALLA FUNZIONE 
public static List<Filamento> getObjSquare(float lat, float longit, float lato) {
	EntityManager em = EMSingleton.getIstance();
	
	TypedQuery<Filamento> filaments = em.createQuery("SELECT f from Filamento f join f.contorno c where f.id = c.filamento.id and ((abs(c.puntocontorno.Latitudine - ?1))<(?3*(sqrt(2)/2)) and (abs(c.puntocontorno.Longitudine-?2))<(?3*(sqrt(2)/2))) group by f.id having count(distinct f.id) >= 1", Filamento.class);
	filaments.setParameter(1, lat); 
	filaments.setParameter(2, longit);
	filaments.setParameter(3, Double.valueOf(lato));
	
	List<Filamento> Filamenti = filaments.getResultList();
	
	if(Filamenti.size()==0)
		return null;
	
	return Filamenti;
}

//REQUISITO 8: RICERCA DI UN OGGETTO ALL'INTERNO DI UNA REGIONE -> PER CALCOLARE TUTTE LE STRUTTURE ESTESE ALL'INTERNO DI UNA REGIONE CIRCOLARE DI RAGGIO R CON CENTROIDE (LAT,LONGIT)
public static List<Filamento> getObjCircle(float lat, float longit, float raggio) {
	EntityManager em = EMSingleton.getIstance();
	
	
	TypedQuery<Filamento> filaments = em.createQuery("SELECT f from Filamento f join f.contorno c where f.id = c.filamento.id and sqrt(power(c.puntocontorno.Latitudine - ?1, 2) + power(c.puntocontorno.Longitudine -?2,2)) <= ?3 group by f.id having count(distinct f.id) >= 1", Filamento.class);
	filaments.setParameter(1,lat);
	filaments.setParameter(2,longit);
	filaments.setParameter(3, Double.valueOf(raggio));
	
	List<Filamento> FilamentiCircle = filaments.getResultList();
	if(FilamentiCircle.size()==0)
		return null;	
	
	return FilamentiCircle;
}

//CI RESTITUISCE UNA LISTA DI TUTTI I CONTORNI(FILAMENTO_ID,PUNTOCONTORNO_LATITUDINE,PUNTOCONTORNO_LONGITUDINE) ORDINATI IN BASE ALL'ID DEL FILAMENTO
public static List<Contorno> getAllContorni() {
	// TODO Auto-generated method stub
	EntityManager em = EMSingleton.getIstance();
	
	TypedQuery<Contorno> cnt = em.createQuery("SELECT c from Contorno c order by c.filamento.id ", Contorno.class);
	
	List<Contorno> totContorni = cnt.getResultList();
	if(totContorni.size()==0)
		return null;
	
	return totContorni;
}

//REQUISITO 10: FRAZIONE DI STELLE IN FORMAZIONE ALL'INTERNO DI UNA REGIONE -> MI RESTITUISCE I CONTORNI E DI CONSEGUENZA I FILAMENTI ALL'INTERNI DELLA REGIONE  
public static List<Integer> getObjRectangle(float lat, float longit, float latoA, float latoB) { 
		// TODO Auto-generated method stub
		
		EntityManager em = EMSingleton.getIstance();
		
		TypedQuery<Integer> ContorniRectangle = (TypedQuery<Integer>) em.createQuery("Select c.filamento.id from Contorno c where (abs(c.puntocontorno.Latitudine - ?1)) <= ?2 and (abs(c.puntocontorno.Longitudine - ?3)) <= ?4 group by c.filamento.id having count(distinct c.filamento.id) >=1 order by c.filamento.id");
		ContorniRectangle.setParameter(1, lat);
		ContorniRectangle.setParameter(2, latoA/2);
		ContorniRectangle.setParameter(3, longit);
		ContorniRectangle.setParameter(4, latoB/2);
		
		
		List<Integer> ListContorno= ContorniRectangle.getResultList();
		
		if(ListContorno.size()==0)
			return null;
		
		return ListContorno;
	}

//REQUISITO 5 : RECUPERO INFORMAZIONI DERIVATE DI UN FILAMENTO -> CALCOLO CENTROIDE
public static float[] getCentroide(Integer id) {
	// TODO Auto-generated method stub
	EntityManager em = EMSingleton.getIstance();

	TypedQuery<Contorno> contornoQuery = em.createQuery("SELECT c FROM Contorno c WHERE c.filamento.id = ?1", Contorno.class);
	contornoQuery.setParameter(1, id);
	List<Contorno> outline = contornoQuery.getResultList();
	
	if(outline.size() == 0)
		return null;
	
	
	float sumLat = 0;
	
	for (int i=0; i<outline.size(); i++) {
		 sumLat = sumLat+ ((outline.get(i).getPuntocontorno().getLatitudine())); 
	}
	
	
	float avgLat = sumLat/ outline.size();
	
	
	
float sumLong = 0;
	
	for (int i=0; i<outline.size(); i++) {
		 sumLong = sumLong + ((outline.get(i).getPuntocontorno().getLongitudine())); 
	}
	
	float avgLong = sumLong / outline.size();
	
	float centroide[] = new float[2];
	
	centroide[0] = avgLat;
	centroide[1] = avgLong;
	
	return centroide;

	
}


}
