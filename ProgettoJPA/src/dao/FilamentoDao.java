package dao;

import java.text.NumberFormat;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import model.Satellite;
import model.Punto_contorno;
import model.Filamento;
import model.Strumento;
import patternConnessione.EMSingleton;

public class FilamentoDao {

	//REQUISITO 4 : IMPORTAZIONE DI UN FILE FILAMENTO DI TIPO HERSCHEL
	public static void insertHerschel(String[] elements) {

		EntityManager em = EMSingleton.getIstance();
		
		try {
		
			Strumento strumento = new Strumento();
			Satellite satellite = new Satellite();
			Filamento filamento = new Filamento();
			
			
				
			NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
	
			
			//System.out.println("id:"+elements[0]+"name:"+elements[1]+"totFlux:"+elements[2]+"meanDens:"+elements[3]+"meanTemp:"+elements[4]+"ellitticity:"+elements[5]+"contrast:"+elements[6]+"satName:"+elements[7]+"toolName:"+elements[8]);
			filamento.setId(Integer.parseInt(elements[0]));
			filamento.setNome(elements[1]);
			filamento.setFlussoTotale(nf.parse(elements[2]).doubleValue());
			filamento.setDensitaMedia(nf.parse(elements[3]).doubleValue());
			filamento.setTemperaturaMedia(Float.parseFloat(elements[4]));
			filamento.setEllitticita(Float.parseFloat(elements[5]));
			filamento.setContrasto(Float.parseFloat(elements[6]));
			satellite.setName(elements[7]);
			strumento.setToolId(elements[8]);
			
			strumento.setSatellite(satellite);
			filamento.setStrumento(strumento);
			
		
			
		
			em.getTransaction().begin();
			em.merge(filamento);
			em.getTransaction().commit();
			


		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException();
		
			
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			 //EMSingleton.close();
		}

	}

//REQUISITO 4 : IMPORTAZIONE DI UN FILE FILAMENTO DI TIPO SPITZER
public static void insertSpitzer(String[] elements) {
		
		EntityManager em = EMSingleton.getIstance();
		
		try {
			
			Strumento strumento = new Strumento();
			Satellite satellite = new Satellite();
			Filamento filamento = new Filamento();
				
			NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
	
			
			//System.out.println("id:"+elements[0]+"name:"+elements[1]+"totFlux:"+elements[2]+"meanDens:"+elements[3]+"meanTemp:"+elements[4]+"ellitticity:"+elements[5]+"contrast:"+elements[6]+"satName:"+elements[7]+"toolName:"+elements[8]);
			filamento.setId(Integer.parseInt(elements[0]));
			filamento.setNome(elements[1]);
			filamento.setFlussoTotale(nf.parse(elements[2]).doubleValue());
			filamento.setDensitaMedia(nf.parse(elements[3]).doubleValue());
			filamento.setTemperaturaMedia(Float.parseFloat(elements[4]));
			filamento.setEllitticita(Float.parseFloat(elements[5]));
			filamento.setContrasto(Float.parseFloat(elements[6]));	
			satellite.setName(elements[7]);
			strumento.setToolId(elements[8]);
			
			strumento.setSatellite(satellite);
			filamento.setStrumento(strumento);
		
			em.getTransaction().begin();
			em.merge(filamento);
			em.getTransaction().commit();
			

		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException();
		
			
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			 //EMSingleton.close();
		}

		
	}

// FILAMENTO OTTENUTO PASSANDO ID E NOME
public static Filamento getFilamenti(Integer id, String nome) {
	EntityManager em = EMSingleton.getIstance();
	
	
	TypedQuery<Filamento> flm = em.createQuery("SELECT f FROM Filamento f WHERE f.id=?1 and f.Nome=?2", Filamento.class);
	flm.setParameter(1,id);
	flm.setParameter(2,nome);
	List<Filamento> Filamenti = flm.getResultList();
	
	
	if(Filamenti.size()==0)
		return null;
	
	return Filamenti.get(0);
}

//REQUISITO 5: RECUPERO INFORMAZIONI DERIVATE DI UN FILAMENTO -> RICHIEDERE IL FILAMENTO PASSANDO COME PARAMETRO IL NOME DEL FILAMENTO 
public static Filamento getFilamentiPerNome(String nome) {
	EntityManager em = EMSingleton.getIstance();
	
	TypedQuery<Filamento> flm = em.createQuery("SELECT f FROM Filamento f WHERE f.nome=?1", Filamento.class);
	flm.setParameter(1,nome);
	List<Filamento> Filamenti = flm.getResultList();
	
	
	if(Filamenti.size()==0)
		return null;
	
	return Filamenti.get(0);
}

//REQUISITO 5: RECUPERO INFORMAZIONI DERIVATE DI UN FILAMENTO -> RICHIEDERE IL FILAMENTO PASSANDO COME PARAMETRO L'ID DEL FILAMENTO 
public static Filamento getFilamentiPerId(Integer id) {
	EntityManager em = EMSingleton.getIstance();
	
	TypedQuery<Filamento> flm = em.createQuery("SELECT f FROM Filamento f WHERE f.id=?1", Filamento.class);
	flm.setParameter(1,id);
	
	List<Filamento> Filamenti = flm.getResultList();
	
	if(Filamenti.size()==0)
		return null;
	
	return Filamenti.get(0);
	
}


// LISTA DI TUTTI I FILAMENTI ALL'INTERNO DEL DATABASE
public static List<Filamento> getAllFilamenti() {
	// TODO Auto-generated method stub
	
	EntityManager em = EMSingleton.getIstance();
	
	TypedQuery<Filamento> qFilamenti = em.createQuery("Select f from Filamento f",Filamento.class);
	List<Filamento> totFilamenti = qFilamenti.getResultList();
	if (totFilamenti.size() != 0)
		return totFilamenti;
	return null;
}

//REQUISITO 6 : RICERCA PER ELLITTICITA E BRILLANZA
public static List<Filamento> ricercaperellitticitaebrillanza(float index1,float index2, float brillanza) {
	EntityManager em = EMSingleton.getIstance();
	
	TypedQuery<Filamento> filamenti = em.createQuery("SELECT f FROM Filamento f WHERE f.ellitticita between ?1 and ?2 and f.Contrasto  > (1.0f + ?3) order by f.id",Filamento.class);
	filamenti.setParameter(1,index1);
	filamenti.setParameter(2,index2);
	filamenti.setParameter(3,brillanza/100);
	
	List<Filamento> Filamenti = filamenti.getResultList();
	
	if(Filamenti.size() != 0) {
		return Filamenti;
	}
	return null;
}

// NECESSARIO PER IL REQUISITO 6 -> MI VELOCIZZA IL CALCOLO DEL CATALOGO DEI FILAMENTI
public static Long getCountAllFilamenti() {
	// TODO Auto-generated method stub
	
	EntityManager em = EMSingleton.getIstance();
	
	TypedQuery<Long> qFilamenti = (TypedQuery<Long>) em.createQuery("Select count(*) from Filamento f");
	Long totFilamenti = qFilamenti.getSingleResult();
	if (totFilamenti != 0)
		return totFilamenti;
	return null;
	
}

}

