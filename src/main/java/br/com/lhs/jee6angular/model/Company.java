package br.com.lhs.jee6angular.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.lhs.jee6angular.commons.Constants;

@Entity
@Table(name = "company")
@SequenceGenerator(name = Constants.DEFAULT_SEQUENCE_GENERATOR, sequenceName = "company_seq", initialValue = 1, allocationSize = 1)
public class Company extends Model {

    private static final long serialVersionUID = -5372689998340170972L;

    @NotNull
    @Size(min = 1, max = 25, message = "1-25 letters and spaces")
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = {
	    CascadeType.MERGE, CascadeType.PERSIST })
    private List<Member> members;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<Member> getMembers() {
	return members;
    }

    public void setMembers(List<Member> members) {
	this.members = members;
    }

}
