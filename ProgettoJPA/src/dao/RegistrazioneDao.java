package dao;


import javax.persistence.EntityManager;

import exception.UtentePresenteException;
import model.Utente;
import patternConnessione.EMSingleton;

public class RegistrazioneDao {

	public static void registra(Utente ut) throws UtentePresenteException {
		EntityManager em = EMSingleton.getIstance();
		if(em.find(Utente.class, ut.getUser_id())!=null)
		{
			throw new UtentePresenteException();
		}
		try {
			em.getTransaction().begin();
			
			em.merge(ut);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			//EMSingleton.close();
		}
	}

}
