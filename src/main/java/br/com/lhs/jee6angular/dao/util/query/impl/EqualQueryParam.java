package br.com.lhs.jee6angular.dao.util.query.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import br.com.lhs.jee6angular.dao.util.query.QueryExpression;

public class EqualQueryParam extends AbstractQueryParam implements QueryExpression {

	public EqualQueryParam(String atributo, Object valor) {
		super(atributo, valor);
	}

	@Override
	public Predicate toPredicate(CriteriaBuilder cb, From<?, ?> root) {
		return cb.equal(root.get(getAtributo()), getValor());
	}

}
