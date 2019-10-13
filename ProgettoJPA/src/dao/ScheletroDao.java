package dao;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Collection;

import javax.persistence.*;

import patternConnessione.EMSingleton;
import view.ScheletroBean;
import model.*;
import dao.*;

public class ScheletroDao {
	
	//REQUISITO 4: IMPORTAZIONE DI UN FILE SCHELETRO -> FILE DI TIPO HERSCHEL
	public static void insertHeschel(String[] elements) {
		
		EntityManager em = EMSingleton.getIstance();
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		
		try {
			Satellite satellite = new Satellite();
			Strumento strumento = new Strumento();
			satellite.setName("Herschel");
			strumento.setSatellite(satellite);
			Filamento filamento = new Filamento();
			filamento.setStrumento(strumento);
			Punto_scheletro puntoscheletro = new Punto_scheletro();
			// System.out.println("id" + elements[0] + "long"+elements[1]+"lat"+elements[2]);
			
			filamento.setId(Integer.parseInt(elements[0]));
			puntoscheletro.setLatitudine(Double.parseDouble(elements[3]));
			puntoscheletro.setLongitudine(Double.parseDouble(elements[4]));
	
	
			
			em.getTransaction().begin();
			
			em.merge(puntoscheletro);
		
			em.getTransaction().commit();
	
			Scheletro scheletro = new Scheletro();
			scheletro.setFlusso(nf.parse(elements[6]).doubleValue());
			scheletro.setFilamento(filamento);
			scheletro.setPuntoscheletro(puntoscheletro);
			
			em.getTransaction().begin();
			
			em.merge(scheletro);
		
			em.getTransaction().commit();
			
			Segmento segmento = new Segmento();
			
			segmento.setIdSegmento(Integer.parseInt(elements[1]));
			segmento.setTipo(elements[2]);
			segmento.setNumeroProgressivo(Integer.parseInt(elements[5]));
			segmento.setPuntoscheletro(puntoscheletro);
			
			em.getTransaction().begin();
			
			em.merge(segmento);
		
			em.getTransaction().commit();
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException();
		
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			// EMSingleton.close();
		}
	

	}

	//REQUISITO 4: IMPORTAZIONE DI UN FILE SCHELETRO -> FILE DI TIPO SPITZER
	public static void insertSpitzer(String[] elements) {
		EntityManager em = EMSingleton.getIstance();
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		
		try {
			Satellite satellite = new Satellite();
			Strumento strumento = new Strumento();
			satellite.setName("Spitzer");
			strumento.setSatellite(satellite);
			Filamento filamento = new Filamento();
			filamento.setStrumento(strumento);
			Punto_scheletro puntoscheletro = new Punto_scheletro();
			// System.out.println("id" + elements[0] + "long"+elements[1]+"lat"+elements[2]);
			
			filamento.setId(Integer.parseInt(elements[0]));
			puntoscheletro.setLatitudine(Double.parseDouble(elements[3]));
			puntoscheletro.setLongitudine(Double.parseDouble(elements[4]));
	
	
			
			em.getTransaction().begin();
			
			em.merge(puntoscheletro);
		
			em.getTransaction().commit();

			Scheletro scheletro = new Scheletro();
			scheletro.setFlusso(nf.parse(elements[6]).doubleValue());
			scheletro.setFilamento(filamento);
			scheletro.setPuntoscheletro(puntoscheletro);
			
			em.getTransaction().begin();
			
			em.merge(scheletro);
		
			em.getTransaction().commit();
			
			Segmento segmento = new Segmento();
		
			segmento.setIdSegmento(Integer.parseInt(elements[1]));
			segmento.setTipo(elements[2]);
			segmento.setNumeroProgressivo(Integer.parseInt(elements[5]));
			segmento.setPuntoscheletro(puntoscheletro);
			
			em.getTransaction().begin();
			
			em.merge(segmento);
		
			em.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException();
		
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			// EMSingleton.close();
		}
	

	}

	//REQUISITO 5: RECUPERO INFORMAZIONI DERIVATE DI UN FILAMENTO -> NUMERO DI SEGMENTI RELATIVI AL FILAMENTO INSERITO
	public static Integer numeroSegmenti(Integer id) {
		EntityManager em = EMSingleton.getIstance();
		
		TypedQuery<Segmento> SegmentoQuery= em.createQuery("SELECT s FROM Segmento s JOIN s.puntoscheletro ps JOIN ps.scheletro sc WHERE s.puntoscheletro.Latitudine = ps.Latitudine and s.puntoscheletro.Longitudine = ps.Longitudine and sc.puntoscheletro.Latitudine = ps.Latitudine and sc.puntoscheletro.Longitudine = ps.Longitudine and sc.filamento.id = ?1 order by s.idSegmento ", Segmento.class);
		
		SegmentoQuery.setParameter(1, id);
		List<Segmento> branches = SegmentoQuery.getResultList();
		System.out.println(branches.size());
		   
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
	
// REQUISITO 7: RICERCA STRUTTURA PER NUMERO SEGMENTI
public static List<Integer> filamentipersegmenti(Integer Index1, Integer Index2) {
		
		EntityManager em = EMSingleton.getIstance();
		
		//TypedQuery<Filamento> filamentoQuery = em.createQuery("Select f From Filamento f JOIN f.scheletro sc JOIN sc.puntoscheletro ps JOIN ps.segmenti s WHERE f.id = sc.filamento.id and sc.puntoscheletro.Latitudine = ps.Latitudine and sc.puntoscheletro.Longitudine = ps.Longitudine and ps.Latitudine = s.puntoscheletro.Latitudine and ps.Longitudine = s.puntoscheletro.Longitudine group by f.id having count(distinct s.idSegmento) between ?1 and ?2 order by f.id",Filamento.class);
		
		
		TypedQuery<Integer> ScheletroQuery = (TypedQuery<Integer>) em.createQuery("Select sc.filamento.id from Scheletro sc join sc.puntoscheletro ps join ps.segmenti s where sc.puntoscheletro.Latitudine = ps.Latitudine and sc.puntoscheletro.Longitudine = ps.Longitudine and ps.Latitudine = s.puntoscheletro.Latitudine and ps.Longitudine = s.puntoscheletro.Longitudine Group by sc.filamento.id having count(distinct s.idSegmento) between ?1 and ?2 order by sc.filamento.id");
		ScheletroQuery.setParameter(1,Long.valueOf(Index1));
		ScheletroQuery.setParameter(2,Long.valueOf(Index2));
		
		
		List<Integer> idfilamento = ScheletroQuery.getResultList();
		if (idfilamento.size() == 0) 
			return null;
		
		
		return  idfilamento;
	}

//REQUISITO 12: POSIZIONE STELLA RISPETTO SPINA DORSALE
public static ArrayList<Stella> DistanzaSpina(Integer filamento_id, ArrayList<Stella> StelleFilamento) {
	ArrayList<ArrayList<Double>> dist = new ArrayList<ArrayList<Double>>();
	EntityManager em = EMSingleton.getIstance();
	
	Punto_scheletro ps = new Punto_scheletro();
	
	TypedQuery<Segmento> SegmentoQuery = em.createQuery("SELECT s FROM Segmento s JOIN s.puntoscheletro ps JOIN ps.scheletro sc WHERE s.puntoscheletro.Latitudine = ps.Latitudine and s.puntoscheletro.Longitudine = ps.Longitudine and sc.puntoscheletro.Latitudine = ps.Latitudine and sc.puntoscheletro.Longitudine = ps.Longitudine and sc.filamento.id = ?1 and s.tipo = 'S' order by sc.filamento.id",Segmento.class);
	
	SegmentoQuery.setParameter(1, filamento_id);
	List<Segmento> backbone = SegmentoQuery.getResultList();
	
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

	return StelleFilamento;
}

// CALCOLO DISTANZA MINIMA DI UN PUNTO DA TUTTI I PUNTI DEL CONTORNO RELATIVI AL FILAMENTO_ID PASSATO DA PARAMETRO 
public static double getMinDistance(double lat, double longit,Integer filamento_id) {
	// TODO Auto-generated method stub
	
	List <Contorno> outline = ContornoDao.getContorniperId(filamento_id);
	
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

//REQUISITO 11: DISTANZA DI UN VERTICI DI UN SEGMENTO DAL CONTORNO -> MI RESTITUISCE L'ID DEL FILAMENTO RELATIVO AI VERTICI DEL SEGMENTO DI CUI POI, CON GETDISTANCE, MI CALCOLERO LA DISTANZA
public static Integer IdFilamentoVerticiSegmento(double lat, double longit) { 
	
	EntityManager em = EMSingleton.getIstance();
	
	TypedQuery<Scheletro> scheletroQuery = em.createQuery("SELECT s from Scheletro s where s.puntoscheletro.Latitudine = ?1 and s.puntoscheletro.Longitudine = ?2 order by s.filamento.id", Scheletro.class);
	scheletroQuery.setParameter(1, lat);
	scheletroQuery.setParameter(2, longit);
	
	List<Scheletro> scheletri = scheletroQuery.getResultList();
	
	if(scheletri.size()==0)
		return (Integer) null;
	
	
	Integer filamento_id = scheletri.get(0).getFilamento().getId();
	return filamento_id;
	
}
	
	
}

