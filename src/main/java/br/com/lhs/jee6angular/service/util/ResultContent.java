package br.com.lhs.jee6angular.service.util;

import java.io.Serializable;
import java.util.List;

import br.com.lhs.jee6angular.model.Model;

public class ResultContent<T extends Model> implements Serializable {

	private static final long serialVersionUID = -93329997768881531L;
	private List<T> findResult;
	private Long countResult;

	public ResultContent(List<T> findResult, Long countResult) {
		this.findResult = findResult;
		this.countResult = countResult;

	}

	public List<T> getFindResult() {
		return findResult;
	}

	public void setFindResult(List<T> findResult) {
		this.findResult = findResult;
	}

	public Long getCountResult() {
		return countResult;
	}

	public void setCountResult(Long countResult) {
		this.countResult = countResult;
	}

}
