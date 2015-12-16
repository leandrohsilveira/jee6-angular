package br.com.lhs.jee6angular.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.lhs.jee6angular.dao.CompanyDAO;
import br.com.lhs.jee6angular.dao.DAO;
import br.com.lhs.jee6angular.dao.util.query.impl.LikeQueryParam;
import br.com.lhs.jee6angular.dao.util.query.impl.LikeQueryParam.LikeType;
import br.com.lhs.jee6angular.model.Company;
import br.com.lhs.jee6angular.service.CompanyService;

@Stateless
public class DefaultCompanyService extends AbstractPersistenceService<Company> implements CompanyService {

	private static final long serialVersionUID = -4312742534233656561L;

	@Inject
	private CompanyDAO companyDAO;

	@Override
	public DAO<Company> getModelDAO() {
		return companyDAO;
	}

	@Override
	public List<Company> completeByName(String name, Integer maxResults) {
		return companyDAO.find(new LikeQueryParam("name", name, LikeType.CONTEM), 0, maxResults != null ? maxResults : 10);
	}

}
