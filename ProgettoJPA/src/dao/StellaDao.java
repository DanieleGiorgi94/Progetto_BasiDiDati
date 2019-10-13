package dao;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import javax.transaction.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.*;

import model.Stella;
import model.Filamento;
import model.TipiStella;
import patternConnessione.EMSingleton;
import model.Banda;
import model.Contorno;
import dao.*;

public class StellaDao {
	//REQUISITO 4: IMPORTAZIONE DI UN FILE STELLA DI TIPO HERSCHEL
	public static void insertHerschel(String[] elements) {
		// TODO Auto-generated method stub
		
		EntityManager em = EMSingleton.getIstance();


		try {
			
			Stella stella = new Stella();
			
			Filamento filamento = new Filamento();
			Banda banda = new Banda();
			
			//System.out.println("id:"+elements[0]+"nome:"+elements[1]+"long:"+elements[2]+"lat:"+elements[3]+"flusso:"+elements[4]+ "tipo:"+ elements[5]);
			stella.setStellaId(Integer.parseInt(elements[0]));
			stella.setNome(elements[1]);
			stella.setLongitudine(Float.parseFloat(elements[2]));
			stella.setLatitudine(Float.parseFloat(elements[3]));
			stella.setFlusso(Float.parseFloat(elements[4]));
			
			TipiStella ts = new TipiStella();			
			
			if (elements[5].equals("UNBOUND")){
				ts.setName("UNBOUND");
				stella.setTypeStars(ts);
			}
			
			if (elements[5].equals("PRESTELLAR")) {
				ts.setName("PRESTELLAR");
				stella.setTypeStars(ts);
			}
			
			if ((elements[5]).equals("PROTOSTELLAR")){
				ts.setName("PROTOSTELLAR");
				stella.setTypeStars(ts);
			}
			
		
			em.getTransaction().begin();
			em.merge(stella);
			em.getTransaction().commit();
			
			
			
			
			


		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException();
		
			
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			// EMSingleton.close();
		}

	}

	//REQUISITO 9: RICERCA DI UN OGGETTO DI TUTTE LE STELLE ALL'INTERNO DI UNA STRUTTURA ESTESA
	public static ArrayList<Stella> getAllStars(Integer filamento_id) {
		
		EntityManager em = EMSingleton.getIstance();
		
		List<Contorno> Contorni = ContornoDao.getContorniperId(filamento_id); // mi faccio restituire una lista di contorni relativa al filamento considerato;
		TypedQuery<Stella> stl = em.createQuery("SELECT s from Stella s", Stella.class);
		List<Stella> Stars = stl.getResultList();
		
		if(Stars.size() == 0)
			return null;
		
		ArrayList<Stella> stellafilamento = new ArrayList<Stella>();
		
		double sum = 0; // mi serve per calcolare la sommatoria di tutte le arctang
		
		
		int j;
		int i;
		Float C_Li  = (Contorni.get(0).getPuntocontorno().getLongitudine());// longitudine primo punto contorno
		Float C_Bi = (Contorni.get(0).getPuntocontorno().getLatitudine()); // latitudine primo punto contorno considerato
		for (j = 0; j<Stars.size();j++){
			Float ST_B = (Stars.get(j).getLatitudine()); //Latitudine stella i 
			Float ST_L = (Stars.get(j).getLongitudine()); // longitudine stella 
			
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
				stellafilamento.add(Stars.get(j));
			
			sum = 0;
				
		}
		
		if (stellafilamento.size() == 0)
			return null;
		
		//System.out.println(stellafilamento.size());
		return stellafilamento; 
		
	}
	
	// REQUISITO 10; FRAZIONE DI STELLE IN FORMAZIONE NEI FILAMENTI ALL'INTERNO DI UNA REGIONE -> STELLE ALL'INTERNO DELLA REGIONE RETTANGOLO 
	public static List<Stella> getObjRectangle(float lat, float longit, float latoA, float latoB) {
		// TODO Auto-generated method stub
		
		EntityManager em = EMSingleton.getIstance();
		
		TypedQuery<Stella> stars = em.createQuery("Select s from Stella s where (abs(s.latitudine - ?1)) <= ?2 and (abs(s.longitudine - ?3)) <= ?4",Stella.class);
		stars.setParameter(1, lat);
		stars.setParameter(2, latoA/2);
		stars.setParameter(3, longit);
		stars.setParameter(4, latoB/2);
		
		
		List<Stella> ListStelle= stars.getResultList();
		//System.out.println(Filamenti.get(0).getNome());
		if(ListStelle.size()==0)
			return null;
		
		return ListStelle;
	}
	
	//REQUISITO 10: FRAZIONE DI STELLE IN FORMAZIONE NEI FILAMENTI ALL'INTERNO DI UNA REGIONE -> STELLE ALL'INTERNO DELLA REGIONE RETTANGOLO E FILAMENTI
	public static ArrayList<Stella> getAllStarsRectangle(float latitudine, float longitudine, float latoA, float latoB) throws InterruptedException {
		EntityManager em = EMSingleton.getIstance();
		
		List<Stella> Stars ;; // stelle all'interno della regione 
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

/*public static Integer getAllStars2() throws InterruptedException {
		EntityManager em = EMSingleton.getIstance();
		
		Integer updateCount = 0;

		TypedQuery<Integer> ContornoQuery = (TypedQuery<Integer>) em.createQuery("Select c.filamento.id from Contorno c group by c.filamento.id having count(distinct c.filamento.id) >= 1 order by c.filamento.id");
		List<Integer> idfilamento = ContornoQuery.getResultList();
		if (idfilamento.size() == 0) 
			return null;
		
		
		TypedQuery<Stella> stl = em.createQuery("SELECT"
				+ ""
				+ ""
				+ " s from Stella s", Stella.class);
		List<Stella> Stars = stl.getResultList();
		
		if(Stars.size() == 0)
			return null;
		
		int h;
		
		Integer filamento_id = idfilamento.get(0);
		for (h = 1; h < idfilamento.size(); h++) {
				List<Contorno> Contorni = ContornoDao.getContorniperId(filamento_id);
				
				if(Stars.size() == 0)
					return null;
					
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
					   em.getTransaction().begin();
						String queryUpdate =
							      "UPDATE Stella s SET s.filamento.id = ?1 WHERE s.stellaId = ?2";
						int executeUpdate = em.createQuery(queryUpdate).setParameter(1, filamento_id).setParameter(2,Stars.get(j).getStellaId()).executeUpdate();
						System.out.println(filamento_id);
						em.getTransaction().commit();
						System.out.println(Stars.get(j).getStellaId());
						Thread.sleep(1000);
						System.out.println(filamento_id);
					}
						
					
					sum = 0;
						
				}
				System.out.println(filamento_id);
				filamento_id = idfilamento.get(h);
		}
		return updateCount;
	}*/
}

		