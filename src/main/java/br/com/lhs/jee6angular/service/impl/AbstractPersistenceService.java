package br.com.lhs.jee6angular.service.impl;

import java.util.List;

import br.com.lhs.jee6angular.model.Model;
import br.com.lhs.jee6angular.service.PersistenceService;

public abstract class AbstractPersistenceService<T extends Model> implements PersistenceService<T> {

	private static final long serialVersionUID = 1L;

	@Override
	public void save(T model) {
		if (model != null) {
			if (model.getId() == null) {
				getModelDAO().persist(model);
			} else {
				getModelDAO().merge(model);
			}
		}
	}

	@Override
	public T findById(Long id) {
		return getModelDAO().getById(id);
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

}
