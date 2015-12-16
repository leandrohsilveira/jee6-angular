package br.com.lhs.jee6angular.service;

import java.io.Serializable;
import java.util.List;

import br.com.lhs.jee6angular.dao.DAO;
import br.com.lhs.jee6angular.model.Model;

public interface PersistenceService<T extends Model> extends Serializable {

	DAO<T> getModelDAO();

	void save(T model);

	T findById(Long id);

	List<T> findAll();

	List<T> find(Integer firstResult, Integer maxResults);

	Long count();

}
