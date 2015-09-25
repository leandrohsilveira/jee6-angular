package br.com.lhs.jee6angular.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.lhs.jee6angular.model.Model;
import br.com.lhs.jee6angular.service.CompanyService;
import br.com.lhs.jee6angular.service.MemberService;
import br.com.lhs.jee6angular.service.SearchService;

@Stateless
public class DefaultSearchService implements SearchService {

    private static final long serialVersionUID = -6569217399796192790L;

    @Inject
    private MemberService memberService;

    @Inject
    private CompanyService companyService;

    @Override
    public List<Model> search(String search) {
	List<Model> models = new ArrayList<>();
	memberService.find(search, null, null).forEach(
		member -> models.add(member));
	companyService.find(search, null, null).forEach(
		company -> models.add(company));
	return models;
    }

    public List<Model> all() {
	List<Model> models = new ArrayList<>();
	memberService.findAll().forEach(member -> models.add(member));
	companyService.findAll().forEach(company -> models.add(company));
	return models;
    }

}
