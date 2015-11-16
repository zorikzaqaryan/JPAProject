package am.gitc.jpa.manager.util;

import javax.persistence.*;
import java.util.*;

public class EntityManagerUtil {
	
	private static EntityManagerFactory entityManagerFactory= null;

	public static synchronized EntityManager getEntityManager() {
		try{
			if (entityManagerFactory == null) {
				createEntityManagerFactory();
			}
			return entityManagerFactory.createEntityManager();			
		}catch (Exception e) {
			throw new RuntimeException("Failed to create EntityManager",e);
		}		
	}
	
	public static void createEntityManagerFactory() {

		HashMap<String,String> config = new HashMap<String, String>();
		config.put("hibernate.connection.username", "root");
		config.put("hibernate.connection.password", "iroot");

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("JPAConfig", config);
		} catch (Exception e) {
			throw new RuntimeException("Failed to create EntityManagerFactory",e);
		}
	}
}
