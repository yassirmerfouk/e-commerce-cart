package ImpDaoJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import IDao.IAdminDao;
import beans.Admin;
import beans.Product;

public class AdminDaoJpa implements IAdminDao{

	private EntityManagerFactory emf;
	
	public AdminDaoJpa() {
		emf = Persistence.createEntityManagerFactory("ecommerce");
		System.out.println("connection");
	}
	
	@Override
	public Admin findByLoginAndPassword(String login, String password) {
		EntityManager entityManager = emf.createEntityManager();
		TypedQuery<Admin> query = entityManager.createQuery("SELECT a FROM Admin a where a.login = :login and a.password= :password",Admin.class);
		query.setParameter("login", login);
		query.setParameter("password", password);
		List<Admin> list = query.getResultList();
		return !list.isEmpty() ? list.get(0) : null;
	}

}
