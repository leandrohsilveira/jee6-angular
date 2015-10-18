package br.com.lhs.jee6angular.dao;

import java.io.Serializable;
import java.util.List;

import br.com.lhs.jee6angular.dao.util.query.QueryExpression;
import br.com.lhs.jee6angular.model.Searchable;

public interface SearchDAO extends Serializable {

	List<Searchable> find(QueryExpression queryExpression, Integer firstResult, Integer maxResults);

	Long count(QueryExpression queryExpression);

}
