package br.com.lhs.jee6angular.utils;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

public interface LazyDataModelLoadSimple<T> {

	List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);

}
