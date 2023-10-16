package listners;

import java.sql.SQLException;

import javax.servlet.ServletContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import IDao.IAdminDao;
import IDao.ICategoryDao;
import IDao.IProductDao;
import ImpDao.AdminDao;
import ImpDao.CategoryDao;
import ImpDao.ProductDao;
import db.DataBase;
import db.IDataBase;

@WebListener
public class ContextListner implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("app run");
		try {
			ServletContext sc = sce.getServletContext();
			ApplicationContext cpx = new ClassPathXmlApplicationContext("spring-config.xml");
			sc.setAttribute("applicationContext", cpx);
		}catch(Exception e) {
			System.out.println("database connection error");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("destroyed");
		ApplicationContext cpx = (ApplicationContext)sce.getServletContext().getAttribute("applicationContext");
		IDataBase database = cpx.getBean(IDataBase.class);
		try {
			database.getConnection().close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		((AbstractApplicationContext) cpx).close();
	}
	
	
}
