package ImpDaoJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import IDao.IProductDao;
import beans.Category;
import beans.Product;

public class ProductDaoJpa implements IProductDao{
	EntityManagerFactory emf;
	public ProductDaoJpa() {
		 emf = Persistence.createEntityManagerFactory("ecommerce");
			System.out.println("connection");
	}
	@Override
	public void add(Product p) {
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(p);
		entityManager.getTransaction().commit();
		entityManager.detach(p);
	}
	@Override
	public void update(Product p) {
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(p);
		entityManager.getTransaction().commit();
		entityManager.detach(p);
		
	}
	@Override
	public void remove(Product p) {
		EntityManager entityManager = emf.createEntityManager();
		p = entityManager.merge(p);
		entityManager.getTransaction().begin();
		entityManager.remove(p);
		entityManager.getTransaction().commit();
	}
	@Override
	public Product find(int id) {
		EntityManager entityManager = emf.createEntityManager();
		Product product = entityManager.find(Product.class, id);
		return product;
	}
	
	@Override
	public List<Product> getAll() {
		EntityManager entityManager = emf.createEntityManager();
		TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p",Product.class);
		List<Product> products = query.getResultList();
		return products;
	}
}
