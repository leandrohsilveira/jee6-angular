package br.com.lhs.jee6angular.controllers.companys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.lhs.jee6angular.controllers.ApplicationURLs;
import br.com.lhs.jee6angular.model.Company;
import br.com.lhs.jee6angular.model.Member;
import br.com.lhs.jee6angular.service.CompanyService;
import br.com.lhs.jee6angular.service.MemberService;

@Named
@RequestScoped
public class CompanyFormController implements Serializable {

	private static final long serialVersionUID = 3344613678445493200L;

	@Inject
	private CompanyService companyService;

	@Inject
	private MemberService memberService;

	@Inject
	private ApplicationURLs urls;

	// @Inject
	// private CompanyMembersController companyMembersController;

	private Company company;

	private Long id;

	private void newCompany() {
		if (id != null) {
			company = companyService.findById(id);
			company.setMembers(memberService.findByCompany(company));
		} else {
			company = new Company();
			company.setMembers(new ArrayList<>());
		}

	}

	public String save() {
		// company.setMembers(getCompanyMembersController().getMembers());
		if (company.getId() != null) {
			List<Member> actualMembers = memberService.findByCompany(company);

			actualMembers.stream().forEach(member -> {
				if (!company.getMembers().contains(member)) {
					member.setCompany(null);
					memberService.save(member);
				}
			});
			company.getMembers().stream().forEach(member -> member.setCompany(company));
			companyService.save(company);
		} else {
			List<Member> members = company.getMembers();
			company.setMembers(null);
			companyService.save(company);
			members.stream().forEach(member -> member.setCompany(company));
			company.setMembers(members);
			companyService.save(company);
		}

		return urls.getUrlCompanysList().concat("?faces-redirect=true");
	}

	public Company getCompany() {
		if (company == null) {
			newCompany();
		}
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// public CompanyMembersController getCompanyMembersController() {
	// return companyMembersController;
	// }

}
