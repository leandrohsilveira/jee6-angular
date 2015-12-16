package br.com.lhs.jee6angular.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

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
		return findByName(name, 0, maxResults != null ? maxResults : 10);
	}

	@Override
	public List<Member> findByName(String name, Integer first, Integer maxResults) {
		if (StringUtils.isBlank(name)) {
			name = "";
		}
		return memberDAO.find(new LikeQueryParam("name", name, LikeType.CONTEM), first, maxResults);
	}

	@Override
	public Long countByName(String name) {
		if (StringUtils.isBlank(name)) {
			name = "";
		}
		return memberDAO.count(new LikeQueryParam("name", name, LikeType.CONTEM));
	}
}
