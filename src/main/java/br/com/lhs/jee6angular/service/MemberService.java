package br.com.lhs.jee6angular.service;

import java.util.List;

import br.com.lhs.jee6angular.model.Company;
import br.com.lhs.jee6angular.model.Member;

public interface MemberService extends PersistenceService<Member> {

	List<Member> findByCompany(Company company);

	List<Member> completeByName(String name, Integer maxResults);

}
