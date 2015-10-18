package br.com.lhs.jee6angular.model;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.lhs.jee6angular.commons.Constants;

@Entity
@Table(name = "search_map")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "entity_name")
@SequenceGenerator(name = Constants.DEFAULT_SEQUENCE_GENERATOR, sequenceName = "search_map_seq", initialValue = 1, allocationSize = 1)
public class Searchable extends Model {

	private static final long serialVersionUID = 1L;

	@Column(name = "query_value")
	private String query;

	@PreUpdate
	@PrePersist
	public void beforeSave() {
		StringBuffer sb = new StringBuffer();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			Class<?> clazz = field.getType();
			if (clazz.isPrimitive()
					&& (int.class.isAssignableFrom(clazz) || long.class.isAssignableFrom(clazz) || float.class.isAssignableFrom(clazz) || double.class
							.isAssignableFrom(clazz)))
				clazz = Number.class;
			if (!field.getName().equalsIgnoreCase("serialVersionUID") && !field.getName().equalsIgnoreCase("query")
					&& (clazz == String.class || Number.class.isAssignableFrom(clazz)))
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

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}
