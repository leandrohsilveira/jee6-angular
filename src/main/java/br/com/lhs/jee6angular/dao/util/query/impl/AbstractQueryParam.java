package br.com.lhs.jee6angular.dao.util.query.impl;

import br.com.lhs.jee6angular.dao.util.query.QueryExpression;

public abstract class AbstractQueryParam implements QueryExpression {

	private String atributo;
	private Object valor;

	public AbstractQueryParam(String atributo, Object valor) {
		super();
		this.atributo = atributo;
		this.valor = valor;
	}

	public String getAtributo() {
		return atributo;
	}

	public Object getValor() {
		return valor;
	}

}
