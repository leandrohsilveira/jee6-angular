package br.com.lhs.jee6angular.dao.util.query.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.lhs.jee6angular.dao.util.query.QueryExpression;

public class LikeQueryParam extends AbstractQueryParam implements QueryExpression {

	public static enum LikeType {

		EXATO("", ""), INICIA("%", ""), TERMINA("", "%"), CONTEM("%", "%");

		private LikeType(String inicio, String fim) {
			this.inicio = inicio;
			this.fim = fim;
		}

		private String inicio;
		private String fim;

		public String get(String valor) {
			return inicio.concat(valor).concat(fim);
		}

	}

	private LikeType tipo = LikeType.CONTEM;

	public LikeQueryParam(String atributo, Object valor, LikeType tipo) {
		super(atributo, valor);
		this.tipo = tipo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Predicate toPredicate(CriteriaBuilder cb, From<?, ?> root) {
		Root<String> r = (Root<String>) root;
		Path<String> path = r.get(getAtributo());
		return cb.like(path, tipo.get(getValor().toString()));
	}

}
