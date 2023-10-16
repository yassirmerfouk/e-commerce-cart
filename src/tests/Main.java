package tests;

import java.io.File;
import java.io.FileReader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import IDao.IAdminDao;
import IDao.ICategoryDao;
import IDao.IProductDao;
import ImpDao.AdminDao;
import ImpDao.CategoryDao;
import ImpDao.ProductDao;
import beans.Category;
import beans.Product;
import db.IDataBase;
import db.DataBase;

public class Main {

	public static void main(String[] args) throws Exception{

//		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
//		IDataBase db = ctx.getBean(IDataBase.class);
//		IProductDao productDao = ctx.getBean(IProductDao.class);
//		System.out.println(productDao.getAll());
//		Categorie c = daoc.find(4);
//		Product p =new Product();
////		p.setId(3);
//		p.setName("noki 1");
//		p.setDescription("description nokia 1");
//		p.setImage("image nokia 1");
//		p.setRegularPrice(2000);
//		p.setDiscount(0.30);
//		p.setCategorie(c);
//		System.out.println(p.getCategorie().getId());
//		daop.add(p);
//		System.out.println(p.getSalePrice());
//		
//		Product p = daop.find(3);
//		System.out.println(daop.getAll().get(1).getCategorie());
//		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommerce");
//		EntityManager em = emf.createEntityManager();
	}
}
