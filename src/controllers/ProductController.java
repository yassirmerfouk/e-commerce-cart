package controllers;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import IDao.IProductDao;
import beans.Product;

@WebServlet(name = "ProductController", urlPatterns = {"/products"})
public class ProductController extends HttpServlet{

	IProductDao productDao;
	@Override
	public void init() throws ServletException {
		productDao = ((ApplicationContext) getServletContext().getAttribute("applicationContext")).getBean(IProductDao.class);
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("auth_admin") != null) {
			response.sendRedirect("admin/products");
			return;
		}
		super.service(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> products = productDao.getAll();
//		Collections.reverse(products);
		products = products.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		request.setAttribute("products", products);
		request.setAttribute("title", "products");
		request.getRequestDispatcher("/WEB-INF/views/products.view.jsp").forward(request, response);
	}
	
}
