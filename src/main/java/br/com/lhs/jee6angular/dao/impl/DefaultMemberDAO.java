package br.com.lhs.jee6angular.dao.impl;

import javax.ejb.Stateless;

import br.com.lhs.jee6angular.dao.MemberDAO;
import br.com.lhs.jee6angular.model.Member;

@Stateless
public class DefaultMemberDAO extends AbstractDAO<Member> implements MemberDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Member> getEntityClass() {
		return Member.class;
	}

}
