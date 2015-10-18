package br.com.lhs.jee6angular.dao.util.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

public interface QueryExpression {

	Predicate toPredicate(CriteriaBuilder cb, From<?, ?> root);

}