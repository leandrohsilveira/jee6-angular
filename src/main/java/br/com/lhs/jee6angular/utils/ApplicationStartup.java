package br.com.lhs.jee6angular.utils;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import br.com.lhs.jee6angular.model.Member;
import br.com.lhs.jee6angular.model.User;
import br.com.lhs.jee6angular.service.MemberService;
import br.com.lhs.jee6angular.service.UserService;

@Startup
@Singleton
public class ApplicationStartup implements Serializable {

	private static final long serialVersionUID = 508940912972076694L;

	@Inject
	private MemberService memberService;

	@Inject
	private UserService userService;

	@PostConstruct
	private void startUp() {

		User user = userService.getByUsername("admin");
		if (user == null) {
			Member member = new Member();
			member.setName("Administrator");
			member.setPhoneNumber("9999999999");
			memberService.save(member);

			user = new User();
			user.setUsername("admin");
			user.setPassword("ever6050");
			user.setMember(member);
			userService.save(user);
		}

	}
}
