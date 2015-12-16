package br.com.lhs.jee6angular.utils;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

public class LazyDataModelWrapper<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;

	private LazyDataModelLoadAdvanced<T> loadAdvanced;
	private LazyDataModelLoadSimple<T> loadSimple;
	private LazyDataModelCount count;

	public LazyDataModelWrapper(LazyDataModelLoadAdvanced<T> load, LazyDataModelCount count) {
		this.loadAdvanced = load;
		this.count = count;
	}

	public LazyDataModelWrapper(LazyDataModelLoadSimple<T> load, LazyDataModelCount count) {
		this.loadSimple = load;
		this.count = count;
	}

	@Override
	public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
		if (loadAdvanced != null) {
			setRowCount(count.count(filters));
			return loadAdvanced.load(first, pageSize, multiSortMeta, filters);
		}
		return super.load(first, pageSize, multiSortMeta, filters);
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		System.out.println(String.format("load(): [first: %d][pageSize: %d][sortField: %s][sortOrder: %s][filterSize: %d]", first, pageSize, sortField, sortOrder != null ? sortOrder.name() : "", filters != null ? filters.size() : 0));
		if (loadSimple != null) {
			int total = count.count(filters);
			System.out.println("LazyDataModelCount.count() = " + total);
			setRowCount(total);
			List<T> list = loadSimple.load(first, pageSize, sortField, sortOrder, filters);
			System.out.println("LazyDataModelLoadSimple.load() = " + list.size());
			return list;
		}
		return super.load(first, pageSize, sortField, sortOrder, filters);
	}
}
