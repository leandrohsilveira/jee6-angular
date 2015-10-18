package br.com.lhs.jee6angular.dao.util.query.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import br.com.lhs.jee6angular.dao.util.query.QueryExpression;

public class JoinGroup extends AbstractGroup implements QueryExpression {

	public JoinGroup(String joinPath, QueryExpression... conditionsOrParams) {
		this(joinPath, false, JoinType.INNER, conditionsOrParams);
	}

	public JoinGroup(String joinPath, List<QueryExpression> conditionsOrParams) {
		this(joinPath, false, JoinType.INNER, conditionsOrParams);
	}

	public JoinGroup(String joinPath, boolean fetch, QueryExpression... conditionsOrParams) {
		this(joinPath, fetch, JoinType.INNER, conditionsOrParams);
	}

	public JoinGroup(String joinPath, boolean fetch, List<QueryExpression> conditionsOrParams) {
		this(joinPath, fetch, JoinType.INNER, conditionsOrParams);
	}

	public JoinGroup(String joinPath, boolean fetch, JoinType joinType, QueryExpression... conditionsOrParams) {
		super(conditionsOrParams);
		this.joinPath = joinPath;
		this.fetch = fetch;
		this.joinType = joinType;
	}

	public JoinGroup(String joinPath, boolean fetch, JoinType joinType, List<QueryExpression> conditionsOrParams) {
		super(conditionsOrParams);
		this.joinPath = joinPath;
		this.fetch = fetch;
		this.joinType = joinType;
	}

	private boolean fetch;

	private JoinType joinType;

	private String joinPath;

	@Override
	public Predicate toPredicate(CriteriaBuilder cb, From<?, ?> root) {
		if (fetch)
			root.fetch(joinPath, joinType);

		Join<Object, Object> join = root.join(joinPath, joinType);

		return cb.and(getPredicates(cb, join));
	}
}
