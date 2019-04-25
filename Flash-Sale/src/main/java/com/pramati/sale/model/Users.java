package com.pramati.sale.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 * @author sudhirk
 * 24-Apr-2019
 */


@Entity
public class Users implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String userName;
	private String firstName;
	private String lastName;
	private BigInteger phone;
	private String gender;
	
	private String password;
	private String email;
	
	@OneToMany
	private Set<Address> address = new HashSet<>();

	
	public Users() {}
	
	public Users(String firstName, String lastName, BigInteger phone, String gender, String password, String email,String userName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.gender = gender;
		this.password = password;
		this.email = email;
		this.userName = userName;
	}

	public Users(String firstName, String lastName, BigInteger phone, String gender, String password, String email,
			Set<Address> address, String userName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.gender = gender;
		this.password = password;
		this.email = email;
		this.address = address;
		this.userName = userName;
	}
	
	
	/*
	 * public Users(String userName, String firstName, String lastName, BigInteger
	 * phone, String gender, String password, String email, Set<Order> orders) {
	 * this.userName = userName; this.firstName = firstName; this.lastName =
	 * lastName; this.phone = phone; this.gender = gender; this.password = password;
	 * this.email = email; this.orders = orders; }
	 */

	/*
	 * public Users(String userName, String firstName, String lastName, BigInteger
	 * phone, String gender, String password, String email, Set<Address> address,
	 * Set<Order> orders) { this.userName = userName; this.firstName = firstName;
	 * this.lastName = lastName; this.phone = phone; this.gender = gender;
	 * this.password = password; this.email = email; this.address = address;
	 * this.orders = orders; }
	 */

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
