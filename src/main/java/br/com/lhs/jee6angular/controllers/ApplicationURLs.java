package br.com.lhs.jee6angular.controllers;

import java.io.Serializable;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@Singleton
@ApplicationScoped
public class ApplicationURLs implements Serializable {

    private static final long serialVersionUID = 4943916935331799627L;

    @Produces
    @Named
    public String getContextName() {
	return "/jee6-angular";
    }

    @Produces
    @Named
    public String getAssetsPath() {
	return getContextName().concat("/assets");
    }

    @Produces
    @Named
    public String getLibsPath() {
	return getContextName().concat("/libs");
    }

    @Produces
    @Named
    public String getUrlMembersList() {
	return "/members/members-list";
    }

    @Produces
    @Named
    public String getUrlMemberForm() {
	return "/members/member-form";
    }

    @Produces
    @Named
    public String getUrlCompanysList() {
	return "/companys/companys-list";
    }

    @Produces
    @Named
    public String getUrlCompanyForm() {
	return "/companys/company-form";
    }

    @Produces
    @Named
    public String getUrlSearchResults() {
	return "/search/results";
    }

}
