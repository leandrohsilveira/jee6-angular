package br.com.lhs.jee6angular.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.jboss.logging.Logger;

@WebFilter(servletNames = "FacesServlet")
public class ApplicationFilter implements Filter {

	@Inject
	private Logger logger;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.info("Adicionando context params para o FacesServlet.");
		config.getServletContext().setInitParameter("javax.faces.FACELETS_LIBRARIES", "/WEB-INF/lhs.taglib.xml");
		config.getServletContext().setInitParameter("javax.faces.PROJECT_STAGE", "development");
		config.getServletContext().setInitParameter("primefaces.THEME", "bootstrap");
		config.getServletContext().setInitParameter("primefaces.FONT_AWESOME", "true");
	}

}
