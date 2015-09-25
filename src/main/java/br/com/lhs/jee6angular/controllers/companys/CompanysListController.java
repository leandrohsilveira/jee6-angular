package br.com.lhs.jee6angular.controllers.companys;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.lhs.jee6angular.model.Company;
import br.com.lhs.jee6angular.service.CompanyService;

@Named
@RequestScoped
public class CompanysListController implements Serializable {

    private static final long serialVersionUID = 3344613678445493200L;

    @Inject
    private CompanyService companyService;

    private List<Company> companys;

    private Integer page = 1;

    private Integer maxResults = 5;

    private Integer pages;

    private String search;

    private void findCompanys() {
	if (page > getPages())
	    page = getPages();
	Integer firstResult = page * maxResults - maxResults;
	if (firstResult <= 0)
	    firstResult = 0;
	if (search != null && !search.trim().equals(""))
	    companys = companyService.find(search.toUpperCase(), firstResult,
		    maxResults);
	else
	    companys = companyService.find(firstResult, maxResults);
    }

    private void countCompanys() {
	Long membersCount;
	if (search != null && !search.trim().equals(""))
	    membersCount = companyService.count(search.toUpperCase());
	else
	    membersCount = companyService.count();
	pages = membersCount.intValue() / maxResults;
	if (membersCount % maxResults > 0)
	    pages++;
    }

    public Integer getPages() {
	if (pages == null)
	    countCompanys();
	return pages;
    }

    public List<Company> getCompanys() {
	if (companys == null)
	    findCompanys();
	return companys;
    }

    public Integer getMaxResults() {
	return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
	this.maxResults = maxResults;
    }

    public String getSearch() {
	return search;
    }

    public void setSearch(String search) {
	this.search = search;
    }

    public Integer getPage() {
	return page;
    }

    public void setPage(Integer page) {
	this.page = page;
    }

}
