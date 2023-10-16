package helpers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper {

	private String persistenceUnitName;
	private EntityManagerFactory entityManagerFactory;

	public EntityManagerHelper() {
		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		System.out.println("Connection to db...");
	}

	public String getPersistenceUnitName() {
		return persistenceUnitName;
	}

	public void setPersistenceUnitName(String persistenceUnitName) {
		this.persistenceUnitName = persistenceUnitName;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	
}
