package br.com.lhs.jee6angular.component;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;

@FacesComponent(value = "ItemMenuAppComponentType")
public class ItemMenuAppComponent extends UIOutput {

	@Override
	public String getRendererType() {
		return "javax.faces.lhs.ItemMenuAppRenderer";
	}

	@Override
	public String getFamily() {
		return "javax.faces.lhs.MenuAplicacao";
	}

	public String getOutcome() {
		return (String) getStateHelper().eval("outcome");
	}

	public void setOutcome(String outcome) {
		getStateHelper().put("outcome", outcome);
	}

}
