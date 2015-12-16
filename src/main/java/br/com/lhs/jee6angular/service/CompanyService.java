package br.com.lhs.jee6angular.service;

import java.util.List;

import br.com.lhs.jee6angular.model.Company;

public interface CompanyService extends PersistenceService<Company> {

	List<Company> completeByName(String name, Integer maxResults);

}
