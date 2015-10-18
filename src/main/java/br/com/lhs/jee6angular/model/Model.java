package br.com.lhs.jee6angular.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.com.lhs.jee6angular.commons.Constants;

@MappedSuperclass
public class Model implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = Constants.DEFAULT_SEQUENCE_GENERATOR)
	private Long id;

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Model) {
			Long objId = ((Model) obj).getId();
			if (objId != null)
				return objId.equals(id);
		}
		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
