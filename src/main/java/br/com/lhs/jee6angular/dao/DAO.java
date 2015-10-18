package br.com.lhs.jee6angular.dao;

import java.io.Serializable;
import java.util.List;

import br.com.lhs.jee6angular.dao.util.query.QueryExpression;
import br.com.lhs.jee6angular.model.Model;

public interface DAO<T extends Model> extends Serializable {

	Class<T> getEntityClass();

	List<T> find(Integer firstResult, Integer maxResults);

	List<T> findAll();

	T findById(Long id);

	void remove(T model);

	void merge(T model);

	void persist(T model);

	Long count();

	List<T> find(QueryExpression queryExpression, Integer firstResult, Integer maxResults);

	Long count(QueryExpression queryExpression);

}
