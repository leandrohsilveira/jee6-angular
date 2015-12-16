package br.com.lhs.jee6angular.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.lhs.jee6angular.dao.DAO;
import br.com.lhs.jee6angular.dao.UserDAO;
import br.com.lhs.jee6angular.dao.util.query.impl.EqualQueryParam;
import br.com.lhs.jee6angular.model.User;
import br.com.lhs.jee6angular.service.UserService;

@Stateless
public class DefaultUserService extends AbstractPersistenceService<User> implements UserService {

	private static final long serialVersionUID = -9015042877433311725L;

	@Inject
	private UserDAO userDAO;

	@Override
	public DAO<User> getModelDAO() {
		return userDAO;
	}

	@Override
	public User getByUsername(String username) {
		return userDAO.get(new EqualQueryParam("username", username));
	}

}
