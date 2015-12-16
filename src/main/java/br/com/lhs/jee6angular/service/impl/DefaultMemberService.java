package br.com.lhs.jee6angular.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.lhs.jee6angular.dao.DAO;
import br.com.lhs.jee6angular.dao.MemberDAO;
import br.com.lhs.jee6angular.dao.util.query.impl.EqualQueryParam;
import br.com.lhs.jee6angular.dao.util.query.impl.JoinGroup;
import br.com.lhs.jee6angular.dao.util.query.impl.LikeQueryParam;
import br.com.lhs.jee6angular.dao.util.query.impl.LikeQueryParam.LikeType;
import br.com.lhs.jee6angular.model.Company;
import br.com.lhs.jee6angular.model.Member;
import br.com.lhs.jee6angular.service.MemberService;

@Stateless
public class DefaultMemberService extends AbstractPersistenceService<Member> implements MemberService {

	private static final long serialVersionUID = 1L;

	@Inject
	private MemberDAO memberDAO;

	@Override
	public DAO<Member> getModelDAO() {
		return memberDAO;
	}

	@Override
	public List<Member> findByCompany(Company company) {
		return memberDAO.find(new JoinGroup("company", new EqualQueryParam("id", company.getId())), null, null);
	}

	@Override
	public List<Member> completeByName(String name, Integer maxResults) {
		return memberDAO.find(new LikeQueryParam("name", name, LikeType.CONTEM), 0, maxResults != null ? maxResults : 10);
	}

}
