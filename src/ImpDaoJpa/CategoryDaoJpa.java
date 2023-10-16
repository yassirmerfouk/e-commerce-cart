package ImpDaoJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import IDao.ICategoryDao;
import beans.Category;

public class CategoryDaoJpa implements ICategoryDao{

	private EntityManagerFactory emf;
	
	public CategoryDaoJpa() {
		emf = Persistence.createEntityManagerFactory("ecommerce");
		System.out.println("connection");
	}

	@Override
	public void add(Category c) {
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(c);
		entityManager.getTransaction().commit();
		entityManager.detach(c);
	}

	@Override
	public void remove(Category c) {
		EntityManager entityManager = emf.createEntityManager();
		c = entityManager.merge(c);
		entityManager.getTransaction().begin();
		entityManager.remove(c);
		entityManager.getTransaction().commit();
	}

	@Override
	public Category find(int id) {
		EntityManager entityManager = emf.createEntityManager();
		return entityManager.find(Category.class, id);
	}

	@Override
	public List<Category> getAll() {
		EntityManager entityManager = emf.createEntityManager();
		TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c",Category.class);
		return query.getResultList();
	}
	
	
	
}
