package br.com.lhs.jee6angular.service.impl;

import java.util.List;

import br.com.lhs.jee6angular.dao.util.query.impl.LikeQueryParam;
import br.com.lhs.jee6angular.dao.util.query.impl.LikeQueryParam.LikeType;
import br.com.lhs.jee6angular.model.Model;
import br.com.lhs.jee6angular.service.PersistenceService;
import br.com.lhs.jee6angular.service.util.ResultContent;

public abstract class AbstractPersistenceService<T extends Model> implements PersistenceService<T> {

	private static final long serialVersionUID = 1L;

	@Override
	public void save(T model) {
		if (model != null)
			if (model.getId() == null)
				getModelDAO().persist(model);
			else
				getModelDAO().merge(model);
	}

	@Override
	public T findById(Long id) {
		return getModelDAO().findById(id);
	}

	@Override
	public List<T> findAll() {
		return getModelDAO().findAll();
	}

	@Override
	public List<T> find(Integer firstResult, Integer maxResults) {
		return getModelDAO().find(firstResult, maxResults);
	}

	@Override
	public Long count() {
		return getModelDAO().count();
	}

	@Override
	public List<T> find(String search, Integer firstResult, Integer maxResults) {
		return getModelDAO().find(new LikeQueryParam("query", search, LikeType.CONTEM), firstResult, maxResults);
	}

	@Override
	public Long count(String search) {
		return getModelDAO().count(new LikeQueryParam("query", search, LikeType.CONTEM));
	}

	@Override
	public ResultContent<T> search(String search, Integer firstResult, Integer maxResults) {
		Long countResult = count(search);
		List<T> findResult = find(search, firstResult, maxResults);
		return new ResultContent<>(findResult, countResult);
	}

}
