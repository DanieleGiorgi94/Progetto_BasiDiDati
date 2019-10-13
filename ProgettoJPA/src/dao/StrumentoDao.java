package dao;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Banda;
import model.Satellite;
import model.Strumento;
import patternConnessione.EMSingleton;

public class StrumentoDao{

	public static List<Banda> findBande() {

		EntityManager em = EMSingleton.getIstance();

		Query query = em.createNamedQuery("Bande");
		List<Banda> bande = query.getResultList();

		if (bande.size() == 0)
			return null;
		return bande;
	}

	public static List<Satellite> findSatelliti() {

		EntityManager em = EMSingleton.getIstance();

		Query query = em.createNamedQuery("Satelliti");
		List<Satellite> satelliti = query.getResultList();

		if (satelliti.size() == 0)
			return null;
		return satelliti;

	}

	public static void insertStrumento(Vector<Float> bande, String satellite,String nomeStrumento) {

		Vector<Banda> vBanda = new Vector<Banda>(1);

		EntityManager em = EMSingleton.getIstance();

		Strumento strumento = new Strumento(); // Istanzio lo strumento
		Satellite sat = em.find(Satellite.class, satellite);

		strumento.setToolId(nomeStrumento); // Imposto l'id dello strumento
		strumento.setSatellite(sat); // Imposto il nome del satellite

		if (bande != null) {
			for (int i = 0; i < bande.size(); i++)
				vBanda.add(i, em.find(Banda.class, bande.elementAt(i)));
		}
		
		strumento.setBanda(vBanda);
		
		try {
			em.getTransaction().begin();
			em.merge(strumento);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
	}

}