package br.com.lhs.jee6angular.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import br.com.lhs.jee6angular.commons.Constants;

@Entity
@Table(name = "user")
@SequenceGenerator(name = Constants.DEFAULT_SEQUENCE_GENERATOR, sequenceName = "user_seq", initialValue = 1, allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends Model {

	private static final long serialVersionUID = -2279696192142561257L;

	@Column(name = "username", updatable = false)
	private String username;

	@Column(name = "password")
	private String password;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "member_id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Member member;

	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
