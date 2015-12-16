package br.com.lhs.jee6angular.dao.impl;

import javax.ejb.Stateless;

import br.com.lhs.jee6angular.dao.UserDAO;
import br.com.lhs.jee6angular.model.User;

@Stateless
public class DefaultUserDAO extends AbstractDAO<User> implements UserDAO {

	private static final long serialVersionUID = -5801387671826963139L;

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

}
