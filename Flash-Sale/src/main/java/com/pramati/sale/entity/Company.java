/**
 * 
 */
package com.pramati.sale.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author sudhirk
 * 25-Apr-2019
 */
@Entity
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String gstin;
	
	@OneToMany
	private Set<Address> address = new HashSet<>();

	public Company() {}

	public Company(String name, String gstin) {
		this.name = name;
		this.gstin = gstin;
	}

	public Company(String name, String gstin, Set<Address> address) {
		this.name = name;
		this.gstin = gstin;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}	
}
