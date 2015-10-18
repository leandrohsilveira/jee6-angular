package br.com.lhs.jee6angular.component.renderer;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import br.com.lhs.jee6angular.component.ItemMenuAppComponent;

@FacesRenderer(rendererType = "javax.faces.lhs.ItemMenuAppRenderer", componentFamily = "javax.faces.lhs.MenuAplicacao")
public class ItemMenuAppRenderer extends Renderer {

	private static final Logger LOGGER = Logger.getLogger(ItemMenuAppRenderer.class);

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter responseWriter = context.getResponseWriter();

		ItemMenuAppComponent itemMenuAppComponent = (ItemMenuAppComponent) component;
		String outcome = itemMenuAppComponent.getOutcome();

		responseWriter.startElement("li", component);
		responseWriter.startElement("a", component);

		if (StringUtils.startsWith(outcome, "/"))
			responseWriter.writeAttribute("href",
					String.format("%s%s", FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath(), outcome), null);
		else if (StringUtils.isNotBlank(outcome))
			responseWriter.writeAttribute("href", outcome, null);
		else
			responseWriter.writeAttribute("href", "#", null);

		responseWriter.writeText(itemMenuAppComponent.getValue(), null);
		responseWriter.endElement("a");
		responseWriter.endElement("li");
	}
}
