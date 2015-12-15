package br.com.lhs.jee6angular.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.lhs.jee6angular.dao.DAO;
import br.com.lhs.jee6angular.dao.util.query.QueryExpression;
import br.com.lhs.jee6angular.model.Member;
import br.com.lhs.jee6angular.model.Model;

public abstract class AbstractDAO<T extends Model> implements DAO<T> {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public void persist(T model) {
		entityManager.persist(model);
	}

	@Override
	public void merge(T model) {
		entityManager.merge(model);
	}

	@Override
	public void remove(T model) {
		entityManager.remove(model);
	}

	@Override
	public T findById(Long id) {
		try {
			return entityManager.find(getEntityClass(), id);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<T> findAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		Root<T> root = cq.from(getEntityClass());
		cq.select(root);
		TypedQuery<T> query = entityManager.createQuery(cq);
		query.setHint("org.hibernate.cacheable", Boolean.TRUE);
		return query.getResultList();
	}

	@Override
	public List<T> find(Integer firstResult, Integer maxResults) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		Root<T> root = cq.from(getEntityClass());
		cq.select(root);
		TypedQuery<T> query = entityManager.createQuery(cq);
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
	public Long count() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<T> root = cq.from(getEntityClass());
		cq.select(cb.count(root));
		TypedQuery<Long> query = entityManager.createQuery(cq);
		query.setHint("org.hibernate.cacheable", Boolean.TRUE);
		return query.getSingleResult();
	}

	@Override
	public List<T> find(QueryExpression queryExpression, Integer firstResult, Integer maxResults) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		Root<T> root = cq.from(getEntityClass());
		cq.select(root);
		if (queryExpression != null) {
			cq.where(queryExpression.toPredicate(cb, root));
		}
		TypedQuery<T> query = entityManager.createQuery(cq);
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
		Root<Member> root = cq.from(Member.class);
		cq.select(cb.count(root));
		if (queryExpression != null) {
			cq.where(queryExpression.toPredicate(cb, root));
		}
		TypedQuery<Long> query = entityManager.createQuery(cq);
		query.setHint("org.hibernate.cacheable", Boolean.TRUE);
		return query.getSingleResult();
	}

}
