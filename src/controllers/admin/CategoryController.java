package controllers.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import IDao.ICategoryDao;
import beans.Category;

@WebServlet(name = "AdminCategoryController", urlPatterns = { "/admin/categories", "/admin/addcategory", "/admin/deletecategory" })
public class CategoryController extends HttpServlet {

	private ICategoryDao categoryDao;

	@Override
	public void init() throws ServletException {
		categoryDao = ((ApplicationContext) getServletContext().getAttribute("applicationContext")).getBean(ICategoryDao.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		String action = path.substring(1).split("/")[1];
		if (action.equals("categories")) {
			request.setAttribute("categories", categoryDao.getAll());
			request.getRequestDispatcher("/WEB-INF/views/admin/category/categories.view.jsp").forward(request, response);
		} else
			response.sendRedirect("../admin/categories");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		String action = path.substring(1).split("/")[1];
		if (action.equals("addcategory")) {
			if (request.getParameter("add") != null) {
				try {
					String name = request.getParameter("name");
					if (name.isEmpty()) {
						request.getSession().setAttribute("error_name", "Category name is required");
					} else {
						Category c = new Category();
						c.setName(name);
						categoryDao.add(c);
						request.getSession().setAttribute("add_success", "The new category was added with success");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					response.sendRedirect("../admin/categories");
				}
			}
		} else if (action.equals("deletecategory")) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Category c = categoryDao.find(id);
				if (c != null) {
					categoryDao.remove(c);
					request.getSession().setAttribute("delete_success", "Category was deleted with success");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				response.sendRedirect("../admin/categories");
			}
		}
	}
}
