package br.com.lhs.jee6angular.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.lhs.jee6angular.dao.SearchDAO;
import br.com.lhs.jee6angular.dao.util.query.impl.LikeQueryParam;
import br.com.lhs.jee6angular.dao.util.query.impl.LikeQueryParam.LikeType;
import br.com.lhs.jee6angular.model.Searchable;
import br.com.lhs.jee6angular.service.SearchService;

@Stateless
public class DefaultSearchService implements SearchService {

	private static final long serialVersionUID = -6569217399796192790L;

	@Inject
	private SearchDAO searchDAO;

	@Override
	public List<Searchable> search(String search, Integer firstResult, Integer maxResults) {
		return searchDAO.find(new LikeQueryParam("query", search, LikeType.CONTEM), firstResult, maxResults);
	}

	@Override
	public List<Searchable> all() {
		return searchDAO.find(null, null, null);
	}

	@Override
	public Long count(String search) {
		return searchDAO.count(new LikeQueryParam("query", search, LikeType.CONTEM));
	}

	@Override
	public Long count() {
		return searchDAO.count(null);
	}

}
