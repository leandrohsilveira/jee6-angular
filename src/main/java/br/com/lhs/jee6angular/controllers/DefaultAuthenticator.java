package br.com.lhs.jee6angular.controllers;

import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.annotations.PicketLink;
import org.picketlink.authentication.web.FormAuthenticationScheme;
import org.picketlink.credential.DefaultLoginCredentials;

import br.com.lhs.jee6angular.service.UserService;

@Named
@PicketLink
public class DefaultAuthenticator extends FormAuthenticationScheme {

	@Inject
	private UserService userService;

	@Inject
	private DefaultLoginCredentials credentials;

	// @Override
	// public void authenticate() {
	// String username = credentials.getUserId();
	// String password = credentials.getPassword();
	// User user = userService.getByUsername(username);
	// if ((user != null) && password.equals(user.getPassword())) {
	// setStatus(AuthenticationStatus.SUCCESS);
	// setAccount(new org.picketlink.idm.model.basic.User(username));
	// } else {
	// setStatus(AuthenticationStatus.FAILURE);
	// FacesContext.getCurrentInstance().addMessage(null, new
	// FacesMessage("Authentication Failure - The username or password you provided were invalid."));
	// }
	//
	// }

}
