package br.com.lhs.jee6angular.filter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;

import org.picketlink.authentication.web.AuthenticationFilter;

@WebFilter(filterName = "PicketLink Authentication Filter", urlPatterns = {
		"/members/*",
		"/companys/*",
		"/users/*"
})
public class SecurityFilter extends AuthenticationFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);

		ServletContext servletContext = filterConfig.getServletContext();

		servletContext.setInitParameter("authType", "FORM");
		servletContext.setInitParameter("form-login-page", "/login.xhtml");
		servletContext.setInitParameter("form-error-page", "/login-error.xhtml");
	}

}
