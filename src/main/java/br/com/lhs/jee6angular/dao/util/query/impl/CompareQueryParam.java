package br.com.lhs.jee6angular.dao.util.query.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import br.com.lhs.jee6angular.dao.util.query.QueryExpression;

public class CompareQueryParam extends AbstractQueryParam implements QueryExpression {

	@SuppressWarnings("rawtypes")
	private Comparable valor;
	private TipoComparacao tipo;

	public static enum TipoComparacao {

		MAIOR, MAIOR_IGUAL, IGUAL, MENOR_IGUAL, MENOR, DIFERENTE;

	}

	@SuppressWarnings("rawtypes")
	public CompareQueryParam(String atributo, Comparable valor, TipoComparacao tipo) {
		super(atributo, null);
		this.valor = valor;
		this.tipo = tipo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Predicate toPredicate(CriteriaBuilder cb, From<?, ?> root) {
		Path<?> path = root.get(getAtributo());
		if ((path != null) && Comparable.class.isAssignableFrom(path.getJavaType())) {
			Path<Comparable> exp = (Path<Comparable>) path;
			switch (tipo) {
			case MAIOR:
				return cb.greaterThan(exp, valor);
			case MAIOR_IGUAL:
				return cb.greaterThanOrEqualTo(exp, valor);
			case MENOR_IGUAL:
				return cb.lessThanOrEqualTo(exp, valor);
			case MENOR:
				return cb.lessThan(exp, valor);
			case DIFERENTE:
				return cb.notEqual(exp, valor);
			case IGUAL:
			default:
				return cb.equal(exp, valor);
			}

		}
		return null;
	}

	@Override
	public Object getValor() {
		return valor;
	}

}
