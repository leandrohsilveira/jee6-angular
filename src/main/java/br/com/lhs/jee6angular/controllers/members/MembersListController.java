package br.com.lhs.jee6angular.controllers.members;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.lhs.jee6angular.model.Member;
import br.com.lhs.jee6angular.service.MemberService;
import br.com.lhs.jee6angular.utils.LazyDataModelWrapper;

@Named
@ViewScoped
public class MembersListController implements Serializable {

	private static final long serialVersionUID = 3344613678445493200L;

	@Inject
	private MemberService memberService;

	private LazyDataModel<Member> membersData;

	private String name;

	public LazyDataModel<Member> getMembersData() {
		if (membersData == null) {
			membersData = new LazyDataModelWrapper<Member>((first, pageSize, sortField, sortOrder, filters) -> memberService.findByName(getName(), first, pageSize), filters -> memberService.countByName(getName()).intValue());
		}
		return membersData;
	}

	public void setMembersData(LazyDataModel<Member> membersData) {
		this.membersData = membersData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
