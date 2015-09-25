package br.com.lhs.jee6angular.controllers.companys;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.lhs.jee6angular.controllers.ApplicationURLs;
import br.com.lhs.jee6angular.model.Company;
import br.com.lhs.jee6angular.service.CompanyService;

@Named
@RequestScoped
public class CompanyFormController implements Serializable {

    private static final long serialVersionUID = 3344613678445493200L;

    @Inject
    private CompanyService companyService;

    @Inject
    private ApplicationURLs urls;

    private Company company;

    private Long id;

    private void newCompany() {
	company = new Company();
	if (id != null)
	    company = companyService.findById(id);
    }

    public String save() {
	companyService.save(company);
	return urls.getUrlCompanysList().concat("?faces-redirect=true");
    }

    public Company getCompany() {
	if (company == null)
	    newCompany();
	return company;
    }

    public void setCompany(Company company) {
	this.company = company;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

}
