package br.com.lhs.jee6angular.service;

import br.com.lhs.jee6angular.model.User;

public interface UserService extends PersistenceService<User> {

	User getByUsername(String username);

}
