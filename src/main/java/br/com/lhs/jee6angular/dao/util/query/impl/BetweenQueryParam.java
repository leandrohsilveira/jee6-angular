package br.com.lhs.jee6angular.dao.util.query.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import br.com.lhs.jee6angular.dao.util.query.QueryExpression;

public class BetweenQueryParam extends AbstractQueryParam implements QueryExpression {

	@SuppressWarnings("rawtypes")
	private Comparable valorInicial, valorFinal;

	@SuppressWarnings("rawtypes")
	public BetweenQueryParam(String atributo, Comparable valorInicial, Comparable valorFinal) {
		super(atributo, null);
		this.valorInicial = valorInicial;
		this.valorFinal = valorFinal;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Predicate toPredicate(CriteriaBuilder cb, From<?, ?> root) {
		Path<?> path = root.get(getAtributo());
		if ((path != null) && Comparable.class.isAssignableFrom(path.getJavaType())) {
			Path<Comparable> exp = (Path<Comparable>) path;
			return cb.between(exp, valorInicial, valorFinal);
		}
		return null;
	}

	public Object getValorFinal() {
		return valorFinal;
	}

}
