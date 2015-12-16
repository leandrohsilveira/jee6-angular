package br.com.lhs.jee6angular.utils;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;

public interface LazyDataModelLoadAdvanced<T> {

	List<T> load(Integer firstResult, Integer pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters);

}