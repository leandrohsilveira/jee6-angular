package br.com.lhs.jee6angular.component;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;

import org.jboss.logging.Logger;

@FacesComponent(value = "MenuAplicacaoComponentType")
public class MenuAplicacaoComponent extends UIOutput {

	private static final Logger LOGGER = Logger.getLogger(MenuAplicacaoComponent.class);

	@Override
	public String getRendererType() {
		return "javax.faces.lhs.MenuAplicacaoRenderer";
	}

	@Override
	public String getFamily() {
		return "javax.faces.lhs.MenuAplicacao";
	}

	public String getUrlSearchAnything() {
		return (String) getStateHelper().eval("urlSearchAnything");
	}

	public void setUrlSearchAnything(String urlSearchAnything) {
		getStateHelper().put("urlSearchAnything", urlSearchAnything);
	}

	public String getApplicationName() {
		return (String) getStateHelper().eval("applicationName");
	}

	public void setApplicationName(String applicationName) {
		getStateHelper().put("applicationName", applicationName);
	}
}
