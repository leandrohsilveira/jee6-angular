package br.com.lhs.jee6angular.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.lhs.jee6angular.dao.SearchDAO;
import br.com.lhs.jee6angular.dao.util.query.QueryExpression;
import br.com.lhs.jee6angular.model.Searchable;

@Stateless
public class DefaultSearchDAO implements SearchDAO {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public List<Searchable> find(QueryExpression queryExpression, Integer firstResult, Integer maxResults) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Searchable> cq = cb.createQuery(Searchable.class);
		Root<Searchable> root = cq.from(Searchable.class);
		cq.select(root);
		if (queryExpression != null) {
			cq.where(queryExpression.toPredicate(cb, root));
		}
		TypedQuery<Searchable> query = entityManager.createQuery(cq);
		if (firstResult != null) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != null) {
			query.setMaxResults(maxResults);
		}
		query.setHint("org.hibernate.cacheable", Boolean.TRUE);
		return query.getResultList();
	}

	@Override
	public Long count(QueryExpression queryExpression) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<Searchable> root = cq.from(Searchable.class);
		cq.select(cb.count(root));
		if (queryExpression != null) {
			cq.where(queryExpression.toPredicate(cb, root));
		}

		TypedQuery<Long> query = entityManager.createQuery(cq);
		query.setHint("org.hibernate.cacheable", Boolean.TRUE);
		return query.getSingleResult();
	}

}
