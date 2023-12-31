package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/admin/*" })
public class AdminFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
			String path = req.getServletPath().substring(1);
				String[] tab = path.split("/");
				if(tab[1] != null && !tab[1].equals("login")) {
					if(req.getSession().getAttribute("auth_admin") == null) {
						((HttpServletResponse) response).sendRedirect("../admin/login");
						return;
					}
				}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
}
