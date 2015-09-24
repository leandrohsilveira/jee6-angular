package br.com.lhs.jee6angular.model;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.lhs.jee6angular.commons.Constants;

@MappedSuperclass
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = Constants.DEFAULT_SEQUENCE_GENERATOR)
    private Long id;

    @Column(name = "query_value")
    private String query;

    @Override
    public boolean equals(Object obj) {
	if (obj != null && obj instanceof Model) {
	    Long objId = ((Model) obj).getId();
	    if (objId != null)
		return objId.equals(id);
	}
	return false;
    }

    @PreUpdate
    @PrePersist
    public void beforeSave() {
	StringBuffer sb = new StringBuffer();
	Field[] fields = this.getClass().getDeclaredFields();
	for (Field field : fields) {
	    Class<?> clazz = field.getType();
	    if (clazz.isPrimitive()
		    && (int.class.isAssignableFrom(clazz)
			    || long.class.isAssignableFrom(clazz)
			    || float.class.isAssignableFrom(clazz) || double.class
				.isAssignableFrom(clazz)))
		clazz = Number.class;
	    if (!field.getName().equalsIgnoreCase("query")
		    && (clazz == String.class || Number.class
			    .isAssignableFrom(clazz)))
		try {
		    field.setAccessible(true);
		    sb.append(field.get(this)).append(",");
		    field.setAccessible(false);
		} catch (IllegalArgumentException | IllegalAccessException e) {
		    e.printStackTrace();
		}
	}
	query = sb.toString().toUpperCase();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getQuery() {
	return query;
    }

    public void setQuery(String query) {
	this.query = query;
    }

}
