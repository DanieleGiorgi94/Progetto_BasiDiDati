package patternConnessione;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// PATTERN SINGLETON-> UTILIZZATO PER GESTIRE AL MEGLIO LE CONNESSIONI 
public class EMSingleton {
	
	private static EntityManager entityManager;
	private static EntityManagerFactory factory;
	
	private EMSingleton()
	{
		factory=Persistence.createEntityManagerFactory("ProgettoBasi17/18Unit");
		entityManager=factory.createEntityManager();
	}
	
	public static  EntityManager getIstance()
	{
		
		
		if(entityManager==null)
		{
			
			new EMSingleton();
		}
		return entityManager;
	}
	
	/*public static void close(){
		entityManager.close();
		factory.close();
	}
	*/
}