package br.com.lhs.jee6angular.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.lhs.jee6angular.dao.DAO;
import br.com.lhs.jee6angular.dao.MemberDAO;
import br.com.lhs.jee6angular.model.Member;
import br.com.lhs.jee6angular.service.MemberService;

@Stateless
public class DefaultMemberService extends AbstractPersistenceService<Member>
		implements
			MemberService {

	private static final long serialVersionUID = 1L;

	@Inject
	private MemberDAO memberDAO;

	@Override
	public DAO<Member> getModelDAO() {
		return memberDAO;
	}

}
