package br.com.lhs.jee6angular.controllers.companys;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import br.com.lhs.jee6angular.model.Company;
import br.com.lhs.jee6angular.service.CompanyService;

@Named
@RequestScoped
public class CompanyProvider {

	@Inject
	private CompanyService companyService;

	private Converter converter;

	public List<Company> autocompleteCompany(String query) {
		List<Company> companies = companyService.completeByName(query, 10);
		System.out.println("Company query " + query + " results in " + companies.size() + " results.");
		return companies;
	}

	@Produces
	@Named("companyConverter")
	public Converter getConverter() {
		if (converter == null)
			converter = new Converter() {

				@Override
				public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
					if (object != null && object instanceof Company)
						if (((Company) object).getId() != null)
							return ((Company) object).getId().toString();
					return null;
				}

				@Override
				public Object getAsObject(FacesContext arg0, UIComponent arg1, String stringValue) {
					if (StringUtils.isNotBlank(stringValue))
						try {
							return companyService.findById(Long.valueOf(stringValue));
						} catch (NumberFormatException e) {
							// Do nothing
						}
					return null;
				}
			};
		return converter;
	}

}
