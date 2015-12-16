package br.com.lhs.jee6angular.controllers.members;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.lhs.jee6angular.model.Member;
import br.com.lhs.jee6angular.service.MemberService;

@Named
@RequestScoped
public class MembersListController implements Serializable {

	private static final long serialVersionUID = 3344613678445493200L;

	@Inject
	private MemberService memberService;

	private List<Member> members;

	private Integer page = 1;

	private Integer maxResults = 5;

	private Integer pages;

	private String search;

	private void findMembers() {
		if (page > getPages())
			page = getPages();
		Integer firstResult = page * maxResults - maxResults;
		if (firstResult <= 0)
			firstResult = 0;
		if (search != null && !search.trim().equals(""))
			members = memberService.find(search.toUpperCase(), firstResult, maxResults);
		else
			members = memberService.find(firstResult, maxResults);
	}

	private void countMembers() {
		Long membersCount;
		if (search != null && !search.trim().equals(""))
			membersCount = memberService.count(search.toUpperCase());
		else
			membersCount = memberService.count();
		pages = membersCount.intValue() / maxResults;
		if (membersCount % maxResults > 0)
			pages++;
	}

	public Integer getPages() {
		if (pages == null)
			countMembers();
		return pages;
	}

	public List<Member> getMembers() {
		if (members == null)
			findMembers();
		return members;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
