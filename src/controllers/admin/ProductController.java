package controllers.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.context.ApplicationContext;

import IDao.ICategoryDao;
import IDao.IProductDao;
import beans.Category;
import beans.Product;

@WebServlet(name = "AdminProductController", urlPatterns = { "/admin/products", "/admin/addproduct", "/admin/editproduct", "/admin/updateproduct", "/admin/deleteproduct" })
@MultipartConfig
public class ProductController extends HttpServlet {

	IProductDao productDao;
	ICategoryDao categoryDao;

	@Override
	public void init() throws ServletException {
		productDao = ((ApplicationContext) getServletContext().getAttribute("applicationContext")).getBean(IProductDao.class);
		categoryDao = ((ApplicationContext) getServletContext().getAttribute("applicationContext")).getBean(ICategoryDao.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getServletPath().substring(1).split("/")[1];
		if (action.equals("products")) {
			request.setAttribute("products", productDao.getAll());
			request.getRequestDispatcher("/WEB-INF/views/admin/product/products.view.jsp").forward(request, response);
		} else if (action.equals("addproduct")) {
			request.setAttribute("categories", categoryDao.getAll());
			request.getRequestDispatcher("/WEB-INF/views/admin/product/add.view.jsp").forward(request, response);
		} else if (action.equals("editproduct")) {
			boolean error = false;
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Product product = productDao.find(id);
				if (product != null) {
					request.setAttribute("categories", categoryDao.getAll());
					request.setAttribute("product", product);
					request.getRequestDispatcher("/WEB-INF/views/admin/product/edit.view.jsp").forward(request, response);
				} else
					error = true;
			} catch (Exception e) {
				error = true;
			} finally {
				if (error)
					response.sendRedirect("../admin/products");
			}
		} else
			response.sendRedirect("../admin/products");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath().substring(1).split("/")[1];
		if (action.equals("addproduct")) {
			boolean error = false;
			try {
				if (request.getParameter("add") != null) {
					if (request.getParameter("name").isEmpty() || request.getParameter("regular_price").isEmpty() || request.getParameter("category_id") == null) {
						if (request.getParameter("name").isEmpty())
							request.getSession().setAttribute("error_name", "Field required");
						if (request.getParameter("regular_price").isEmpty())
							request.getSession().setAttribute("error_regularPrice", "Field required");
						if (request.getParameter("category_id") == null)
							request.getSession().setAttribute("error_categoryId", "Field required");
						error = true;
					} else {
						String name = request.getParameter("name");
						String description = request.getParameter("description");
						double regularPrice = Double.parseDouble(request.getParameter("regular_price"));
						double discount = request.getParameter("discount").isEmpty() ? 0 : Double.parseDouble(request.getParameter("discount"));
						int categoryId = Integer.parseInt(request.getParameter("category_id"));
						Category category = categoryDao.find(categoryId);
						String path = "C:/Users/Yassir Merfouk/Documents/java/eclipse_package/java_web/j2ee/e-commerce-cart/WebContent/images/products/";
						Part image = request.getPart("image");
						String fileName = image.getContentType();
						String ext = fileName.split("/")[1];
						int randomNumber = (int) (Math.random() * 10000000);
						String imageName = null;
						imageName = image.getSize() > 0 ? randomNumber + "." + ext : "";
						fileName = path + imageName;
						if (category != null) {
							Product product = new Product();
							product.setName(name);
							product.setDescription(description);
							product.setImage("product");
							product.setRegularPrice(regularPrice);
							product.setDiscount(discount / 100);
							product.setCategory(category);
							product.setImage(imageName);
							productDao.add(product);
							if(image.getSize() > 0) image.write(fileName);
							request.getSession().setAttribute("add_success", "Product added with success");
						}
					}
				}
			} catch (Exception e) {
				error = true;
				e.printStackTrace();
			} finally {
				if (error)
					response.sendRedirect("../admin/addproduct");
				else
					response.sendRedirect("../admin/products");
			}
		} else if (action.equals("deleteproduct")) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Product product = productDao.find(id);
				if (product != null) {
					productDao.remove(product);
					request.getSession().setAttribute("delete_success", "Product deleted with success");
				}
			} catch (Exception e) {
			} finally {
				response.sendRedirect("../admin/products");
			}
		} else if (action.equals("updateproduct")) {
			boolean error = false;
			try {
				int productId = Integer.parseInt(request.getParameter("id"));
				Product product = productDao.find(productId);
				if (product != null) {
					if (request.getParameter("name").isEmpty() || request.getParameter("regular_price").isEmpty() || request.getParameter("category_id") == null) {
						if (request.getParameter("name").isEmpty())
							request.getSession().setAttribute("error_name", "Field required");
						if (request.getParameter("regular_price").isEmpty())
							request.getSession().setAttribute("error_regularPrice", "Field required");
						if (request.getParameter("category_id") == null)
							request.getSession().setAttribute("error_categoryId", "Field required");
						error = true;
					} else {
						String name = request.getParameter("name");
						String description = request.getParameter("description");
						double regularPrice = Double.parseDouble(request.getParameter("regular_price"));
						double discount = request.getParameter("discount").isEmpty() ? 0 : Double.parseDouble(request.getParameter("discount"));
						int categoryId = Integer.parseInt(request.getParameter("category_id"));
						Category category = categoryDao.find(categoryId);
						String path = "C:/Users/Yassir Merfouk/Documents/java/eclipse_package/java_web/j2ee/e-commerce-cart/WebContent/images/products/";
						Part image = request.getPart("image");
						String fileName = image.getContentType();
						String ext = fileName.split("/")[1];
						int randomNumber = (int) (Math.random() * 10000000);
						String imageName = null;
						imageName = image.getSize() > 0 ? randomNumber + "." + ext : product.getImage();
						fileName = path + imageName;
						if (category != null) {
							product.setName(name);
							product.setDescription(description);
							product.setImage(imageName);
							product.setRegularPrice(regularPrice);
							product.setDiscount(discount / 100);
							product.setCategory(category);
							productDao.update(product);
							if(image.getSize() > 0) image.write(fileName);
							request.getSession().setAttribute("update_success", "Product updated with success");
						} else
							error = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				error = true;
			} finally {
				if (error)
					response.sendRedirect("../admin/editproduct?id=" + request.getParameter("id"));
				else
					response.sendRedirect("../admin/products");
			}
		}
	}
}
