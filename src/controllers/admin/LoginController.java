package controllers.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import IDao.IAdminDao;
import beans.Admin;

@WebServlet(name = "AdminLoginController",urlPatterns = {"/admin/login"})
public class LoginController extends HttpServlet{
	
	private IAdminDao adminDao;
	
	@Override
	public void init() throws ServletException {
		adminDao = ((ApplicationContext)getServletContext().getAttribute("applicationContext")).getBean(IAdminDao.class);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("auth_admin") != null) {
			response.sendRedirect("../admin/products");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/views/login.view.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
		if(requset.getParameter("login") != null && requset.getParameter("password") != null) {
			String login = requset.getParameter("login");
			String password = requset.getParameter("password");
			if(login.isEmpty() || password.isEmpty()) {
				if(login.isEmpty())
					requset.getSession().setAttribute("error_login", "login is required");
				if(password.isEmpty())
					requset.getSession().setAttribute("error_password", "password is required");
				response.sendRedirect("../admin/login");
			}else{
				Admin admin = adminDao.findByLoginAndPassword(login, password);
				if(admin != null) {
					requset.getSession().setAttribute("auth_admin", admin);
					response.sendRedirect("../admin/products");
				}else {
					requset.getSession().setAttribute("error_login", "error in login or password");
					requset.getSession().setAttribute("error_password", "error in login or password");
					response.sendRedirect("../admin/login");
				}
			}
		}else {
			response.sendRedirect("../admin/login");
		}
	}
}
