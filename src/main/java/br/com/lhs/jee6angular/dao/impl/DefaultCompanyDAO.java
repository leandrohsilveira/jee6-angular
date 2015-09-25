package br.com.lhs.jee6angular.dao.impl;

import javax.ejb.Stateless;

import br.com.lhs.jee6angular.dao.CompanyDAO;
import br.com.lhs.jee6angular.model.Company;

@Stateless
public class DefaultCompanyDAO extends AbstractDAO<Company> implements
	CompanyDAO {

    private static final long serialVersionUID = -2317829138536529150L;

    @Override
    public Class<Company> getEntityClass() {
	return Company.class;
    }

}
