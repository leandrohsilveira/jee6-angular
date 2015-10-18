package br.com.lhs.jee6angular.component.renderer;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import br.com.lhs.jee6angular.component.MenuAplicacaoComponent;

@FacesRenderer(rendererType = "javax.faces.lhs.MenuAplicacaoRenderer", componentFamily = "javax.faces.lhs.MenuAplicacao")
public class MenuAplicacaoRenderer extends Renderer {

	private static final Logger LOGGER = Logger.getLogger(MenuAplicacaoRenderer.class);

	private String applicationName = "Company Manager";
	private String contextName;
	private String urlSearchAnything;
	private MenuAplicacaoComponent menuAplicacaoComponent;

	private String clientId;

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter responseWriter = context.getResponseWriter();

		menuAplicacaoComponent = (MenuAplicacaoComponent) component;
		contextName = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		urlSearchAnything = menuAplicacaoComponent.getUrlSearchAnything();
		applicationName = menuAplicacaoComponent.getApplicationName();

		clientId = component.getClientId();

		responseWriter.append("<nav class='navbar navbar-inverse navbar-fixed-top'>");
		responseWriter.append("<div class='container-fluid'>");
		responseWriter.append("<div class='navbar-header'>");
		String button = String
				.format("<button class='navbar-toggle collapsed' data-toggle='collapse' data-target='#%s_collapse'  aria-expanded='false' aria-controls='navbar'>",
						clientId);
		responseWriter.append(button);
		responseWriter.append("<i class='fa fa-bars fa-lg text-white'></i>");
		responseWriter.endElement("button");
		responseWriter.append(String.format("<span class='navbar-brand'>%s</span>", applicationName));
		responseWriter.endElement("div");
		responseWriter.append(String.format("<div id='%s_collapse' class='navbar-collapse collapse'>", clientId));
		responseWriter.append("<ul class='nav navbar-nav navbar-right'>");
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {

	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter responseWriter = context.getResponseWriter();
		responseWriter.endElement("ul");
		if (StringUtils.isNotBlank(urlSearchAnything)) {
			responseWriter
					.append(String.format("<form class='navbar-form navbar-right' method='get' action='%s%s'>", contextName, urlSearchAnything));
			responseWriter.append("<input type='text' name='search' class='form-control' placeholder='Search anything...' />");
			responseWriter.endElement("form");
		}
		responseWriter.endElement("div");
		responseWriter.endElement("div");
		responseWriter.endElement("nav");
	}

}
