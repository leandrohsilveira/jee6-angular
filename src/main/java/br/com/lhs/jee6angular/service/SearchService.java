package br.com.lhs.jee6angular.service;

import java.io.Serializable;
import java.util.List;

import br.com.lhs.jee6angular.model.Searchable;

public interface SearchService extends Serializable {

	List<Searchable> search(String search, Integer firstResult, Integer maxResults);

	List<Searchable> all();

	Long count(String search);

	Long count();

}
