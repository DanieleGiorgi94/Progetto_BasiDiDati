package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Contorno;
import model.Utente;

public class LoginDAo {

	
	public static Utente findByUserNameAndPassword(String userid, String password) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProgettoBasi17/18Unit");
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<Utente> UtenteQuery = em.createQuery("Select u from Utente u where u.user_id = ?1 and u.password = ?2", Utente.class);
		UtenteQuery.setParameter(1, userid);
		UtenteQuery.setParameter(2, password);
		List<Utente> Cercato = UtenteQuery.getResultList();
		if (Cercato.size() == 0) {
			return null;
		}
		
		return Cercato.get(0);
	}
		
}
