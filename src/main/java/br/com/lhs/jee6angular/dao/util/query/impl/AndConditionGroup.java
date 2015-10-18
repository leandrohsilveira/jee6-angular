package br.com.lhs.jee6angular.dao.util.query.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import br.com.lhs.jee6angular.dao.util.query.QueryExpression;

public class AndConditionGroup extends AbstractGroup implements QueryExpression {

	@Override
	public Predicate toPredicate(CriteriaBuilder cb, From<?, ?> root) {
		if ((conditionsOrParams != null) && !conditionsOrParams.isEmpty())
			return cb.and(getPredicates(cb, root));
		return cb.equal(cb.literal(1), 1);
	}

}
