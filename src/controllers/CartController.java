package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import IDao.IProductDao;
import beans.Cart;
import beans.CartItem;
import beans.Product;

@WebServlet(name = "CartController", urlPatterns = { "/cart", "/addcartproduct", "/updatecartproduct", "/deletecartproduct", "/clearcart" })
public class CartController extends HttpServlet {

	IProductDao productDao;

	@Override
	public void init() throws ServletException {
		productDao = ((ApplicationContext) getServletContext().getAttribute("applicationContext")).getBean(IProductDao.class);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("auth_admin") != null) {
			response.sendRedirect("admin/products");
			return;
		}
		super.service(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath().substring(1).split("/")[0];
		if (action.equals("cart")) {
			request.setAttribute("cart_css", true);
			request.getRequestDispatcher("/WEB-INF/views/cart.view.jsp").forward(request, response);
		} else
			response.sendRedirect("cart");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath().substring(1).split("/")[0];
		if (action.equals("addcartproduct")) {
			try {
				if (request.getParameter("add") != null) {
					int id = Integer.parseInt(request.getParameter("productid"));
					Product p = productDao.find(id);
					if (p != null) {
						Cart cart = (Cart) request.getSession().getAttribute("cart");
						if (!cart.productExist(id)) {
							CartItem cartItem = new CartItem();
							cartItem.setProduct(p);
							cartItem.setQte(1);
							cart.add(cartItem);
							request.getSession().setAttribute("add_success", "The product has been added to your cart");
						} else {
							request.getSession().setAttribute("add_error", "The product selectd is arleady exists");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				response.sendRedirect("products");
			}
		} else if (action.equals("clearcart")) {
			try {
				Cart cart = (Cart) request.getSession().getAttribute("cart");
				cart.clear();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				response.sendRedirect("cart");
			}
		} else if (action.equals("deletecartproduct")) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Cart cart = (Cart) request.getSession().getAttribute("cart");
				if (cart.productExist(id))
					cart.removeItem(id);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				response.sendRedirect("cart");
			}
		} else if (action.equals("updatecartproduct")) {
			System.out.println("update");
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				int qte = Integer.parseInt(request.getParameter("qte"));
				if (qte > 0) {
					Cart cart = (Cart) request.getSession().getAttribute("cart");
					if (cart.productExist(id)) {
						cart.getItem(id).setQte(qte);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				response.sendRedirect("cart");
			}
		}
	}
}
