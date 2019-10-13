package model;

import javax.persistence.Query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String args[]){
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProgettoBasi17/18Unit");
		EntityManager em = emf.createEntityManager();
		Utente ut=em.find(Utente.class, "DanieleG1994");
		
		Query query=em.createNamedQuery("Utenti");
		List Utenti=query.getResultList();
		for(Object o:Utenti)
		{
			printResult((Utente)o);
		}
		
	}

	private static void printResult(Utente result) {
		if (result == null) {
		      System.out.print("NULL");
		    } else {
		    	System.out.println(result.getName());
		    }
		
	}
}