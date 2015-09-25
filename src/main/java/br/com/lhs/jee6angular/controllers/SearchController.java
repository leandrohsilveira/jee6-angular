package br.com.lhs.jee6angular.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.lhs.jee6angular.model.Model;
import br.com.lhs.jee6angular.service.SearchService;

@Named
@RequestScoped
public class SearchController implements Serializable {

    private static final long serialVersionUID = 8448865325443214697L;

    @Inject
    private SearchService searchService;

    private String search;

    private List<Model> results;

    private void search() {
	if (search != null && !search.trim().equals(""))
	    results = searchService.search(search.toUpperCase());
	else
	    results = searchService.all();
    }

    public List<Model> getResults() {
	if (results == null)
	    search();
	return results;
    }

    public String getSearch() {
	return search;
    }

    public void setSearch(String search) {
	this.search = search;
    }

}
