package br.com.lhs.jee6angular.controllers.members;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.lhs.jee6angular.model.Member;
import br.com.lhs.jee6angular.service.MemberService;

@Named
@RequestScoped
public class MemberFormController implements Serializable {

    private static final long serialVersionUID = 3344613678445493200L;

    @Inject
    private MemberService memberService;

    private Member member;

    private Long id;

    private void newMember() {
	member = new Member();
	if (id != null)
	    member = memberService.findById(id);
    }

    public String save() {
	memberService.save(member);
	return "members-list.jsf?faces-redirect=true";
    }

    public Member getMember() {
	if (member == null)
	    newMember();
	return member;
    }

    public void setMember(Member member) {
	this.member = member;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

}
