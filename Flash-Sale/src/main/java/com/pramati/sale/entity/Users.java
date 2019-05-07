package com.pramati.sale.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author sudhirk
 * 24-Apr-2019
 */


@Entity
@Table(name = "users")
public class Users implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private Long id;
	
	@Column(name="userName", unique = true)
	private String userName;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="phone")
	private BigInteger phone;
	
	@Column(name="gender")
	private Category gender;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "userrole", joinColumns = @JoinColumn(referencedColumnName = "userid"), inverseJoinColumns = @JoinColumn(name = "roleid"))
	private Set<Authority> authority = new HashSet<>();
	
	@OneToMany
	private Set<Address> address = new HashSet<>();

	
	public Users() {}


	public Users(String userName, String firstName, String lastName, BigInteger phone, Category gender, String password,
			String email, Boolean enabled) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.gender = gender;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
	}


	public Users(String userName, String firstName, String lastName, BigInteger phone, Category gender, String password,
			String email, Boolean enabled, Set<Authority> authority, Set<Address> address) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.gender = gender;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.authority = authority;
		this.address = address;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public BigInteger getPhone() {
		return phone;
	}


	public void setPhone(BigInteger phone) {
		this.phone = phone;
	}


	public Category getGender() {
		return gender;
	}


	public void setGender(Category gender) {
		this.gender = gender;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public Set<Authority> getAuthority() {
		return authority;
	}


	public void setAuthority(Set<Authority> authority) {
		this.authority = authority;
	}


	public Set<Address> getAddress() {
		return address;
	}


	public void setAddress(Set<Address> address) {
		this.address = address;
	}
}
