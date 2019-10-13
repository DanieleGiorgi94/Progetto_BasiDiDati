package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


import model.Punto_scheletro;
import model.Segmento;
import patternConnessione.EMSingleton;

public class SegmentoDao {
	
	//REQUISITO 11: DISTANZA DI UN VERTICE DI UN SEGMENTO DAL CONTORNO -> VERTICI SEGMENTO CON NUMERO PROGRESSIVO MINIMO 
	public static ArrayList<Double> getMinVertex(Integer idsegmento) {
		
		EntityManager em = EMSingleton.getIstance();
		
		TypedQuery<Segmento> segmentoQuery = em.createQuery("SELECT s FROM Segmento s JOIN s.puntoscheletro ps JOIN ps.scheletro sc WHERE s.puntoscheletro.Latitudine = ps.Latitudine and s.puntoscheletro.Longitudine = ps.Longitudine and sc.puntoscheletro.Latitudine = ps.Latitudine and sc.puntoscheletro.Longitudine = ps.Longitudine and s.idSegmento = ?1  ORDER BY sc.filamento.id,s.numeroProgressivo" , Segmento.class);
		
		segmentoQuery.setParameter(1, idsegmento);
		
		List<Segmento> segments = segmentoQuery.getResultList();
		
		if (segments.size() == 0) 
			return null;
		
		ArrayList<Double> vertex = new ArrayList<Double>();
		
		
		for (int i=0; i<segments.size();i++){
			if (segments.get(i).getNumeroProgressivo() == 1) {
				double latMinVertex = segments.get(i).getPuntoscheletro().getLatitudine();
				double longMinVertex = segments.get(i).getPuntoscheletro().getLongitudine();
				vertex.add(latMinVertex);
				vertex.add(longMinVertex);
			}
			
		}
		
		
		if (segments.size() == 0) 
			return null;
		
		
		return vertex;
		
	}
	
	//REQUISITO 11: DISTANZA DI UN VERTICE DI UN SEGMENTO DAL CONTORNO -> VERTICI SEGMENTO CON NUMERO PROGRESSIVO MASSIMO
	public static ArrayList<Double> getMaxVertex(Integer idsegmento) {
		
		EntityManager em = EMSingleton.getIstance();
		
		TypedQuery<Segmento> segmentoQuery = em.createQuery("SELECT s FROM Segmento s JOIN s.puntoscheletro ps JOIN ps.scheletro sc WHERE s.puntoscheletro.Latitudine = ps.Latitudine and s.puntoscheletro.Longitudine = ps.Longitudine and sc.puntoscheletro.Latitudine = ps.Latitudine and sc.puntoscheletro.Longitudine = ps.Longitudine and s.idSegmento = ?1 ORDER BY sc.filamento.id,s.numeroProgressivo" , Segmento.class);
		
		segmentoQuery.setParameter(1, idsegmento);
		
		List<Segmento> segments = segmentoQuery.getResultList();
		
		Integer size = segments.size();		
		
		if (segments.size() == 0) 
			return null;
		
		ArrayList<Double> vertex = new ArrayList<Double>();
		
		for (int i=1; i<segments.size();i++){
			if (segments.get(i).getNumeroProgressivo() == 1) {
				double latMaxVertex = segments.get(i-1).getPuntoscheletro().getLatitudine();
				double longMaxVertex = segments.get(i-1).getPuntoscheletro().getLongitudine();
				vertex.add(latMaxVertex);
				vertex.add(longMaxVertex);
			}
		}
		
		vertex.add(segments.get(size-1).getPuntoscheletro().getLatitudine());
		vertex.add(segments.get(size-1).getPuntoscheletro().getLongitudine());
		
		return vertex;
			
	}
	
	

}