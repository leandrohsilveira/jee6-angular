package br.com.lhs.jee6angular.controllers;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import br.com.lhs.jee6angular.model.Company;
import br.com.lhs.jee6angular.model.Member;
import br.com.lhs.jee6angular.model.Model;

@Named
@ApplicationScoped
public class ModelsController implements Serializable {

    private static final long serialVersionUID = 4943916935331799627L;

    @Produces
    @Named
    public Class<Member> getMemberClass() {
	return Member.class;
    }

    @Produces
    @Named
    public Class<Company> getCompanyClass() {
	return Company.class;
    }

    public boolean isInstanceOfMember(Model model) {
	return model instanceof Member;
    }

    public boolean isInstanceOfCompany(Model model) {
	return model instanceof Company;
    }

    public Member getAsMember(Model model) {
	if (isInstanceOfMember(model))
	    return (Member) model;
	return null;
    }

    public Company getAsCompany(Model model) {
	if (isInstanceOfCompany(model))
	    return (Company) model;
	return null;
    }

}
