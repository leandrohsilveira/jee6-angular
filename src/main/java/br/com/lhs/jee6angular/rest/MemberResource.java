package br.com.lhs.jee6angular.rest;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

import br.com.lhs.jee6angular.model.Member;
import br.com.lhs.jee6angular.service.MemberService;
import br.com.lhs.jee6angular.service.PersistenceService;

@Path("/members")
@RequestScoped
public class MemberResource extends AbstractServiceResource<Member>
implements
Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MemberService memberService;

	@Override
	protected PersistenceService<Member> getService() {
		return memberService;
	}

}
