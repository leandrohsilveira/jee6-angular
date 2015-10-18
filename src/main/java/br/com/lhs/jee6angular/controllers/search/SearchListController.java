package br.com.lhs.jee6angular.controllers.search;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.lhs.jee6angular.model.Searchable;
import br.com.lhs.jee6angular.service.SearchService;

@Named
@RequestScoped
public class SearchListController implements Serializable {

	private static final long serialVersionUID = 8448865325443214697L;

	@Inject
	private SearchService searchService;

	private String search;

	private Integer page = 1;

	private Integer maxResults = 5;

	private Integer pages;

	private List<Searchable> results;

	private void search() {
		if (page > getPages())
			page = getPages();
		Integer firstResult = page * maxResults - maxResults;
		if (firstResult <= 0)
			firstResult = 0;
		if (search != null && !search.trim().equals(""))
			results = searchService.search(search.toUpperCase(), firstResult, maxResults);
		else
			results = searchService.all();
	}

	public List<Searchable> getResults() {
		if (results == null)
			search();
		return results;
	}

	private void countResults() {
		Long membersCount;
		if (search != null && !search.trim().equals(""))
			membersCount = searchService.count(search.toUpperCase());
		else
			membersCount = searchService.count();
		pages = membersCount.intValue() / maxResults;
		if (membersCount % maxResults > 0)
			pages++;
	}

	public Integer getPages() {
		if (pages == null)
			countResults();
		return pages;
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

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public void setResults(List<Searchable> results) {
		this.results = results;
	}

}
