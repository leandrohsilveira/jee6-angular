package br.com.lhs.jee6angular.dao.util.query.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import br.com.lhs.jee6angular.dao.util.query.QueryExpression;

public abstract class AbstractGroup implements QueryExpression {

	public AbstractGroup(QueryExpression... conditionsOrParams) {
		this.conditionsOrParams = new ArrayList<QueryExpression>();
		if (conditionsOrParams != null)
			for (QueryExpression queryParam : conditionsOrParams)
				this.conditionsOrParams.add(queryParam);
	}

	public AbstractGroup(List<QueryExpression> conditionsOrParams) {
		this.conditionsOrParams = conditionsOrParams;
	}

	protected List<QueryExpression> conditionsOrParams;

	public AbstractGroup addQueryParam(QueryExpression queryParam) {
		if (conditionsOrParams == null)
			conditionsOrParams = Arrays.asList(queryParam);
		else
			conditionsOrParams.add(queryParam);
		return this;
	}

	protected Predicate[] getPredicates(CriteriaBuilder cb, From<?, ?> root) {
		Predicate[] predicates = new Predicate[conditionsOrParams.size()];
		for (int i = 0; i < predicates.length; i++)
			predicates[i] = conditionsOrParams.get(i).toPredicate(cb, root);
		return predicates;
	}

}
