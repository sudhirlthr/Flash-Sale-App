package com.pramati.sale.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * @author sudhirk
 * 24-Apr-2019
 */


@Entity
@Table(name = "products")
public class Products implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private Double price;
	private Double discountInPrecentage;
	private Double actualPrice;
	private String model;
	private String materialType;
	private Category category;
	private Availability availability;
	private Integer numberOfItemAvailable;
	
	@ManyToOne
	private Company company;
	
	public Products() {}
	

	public Products(String name, Double price, Double discountInPrecentage, Double actualPrice, String model,
			String materialType, Category category, Availability availability, Company company, Integer numberOfItemAvailable) {
		this.name = name;
		this.price = price;
		this.discountInPrecentage = discountInPrecentage;
		this.actualPrice = actualPrice;
		this.model = model;
		this.materialType = materialType;
		this.category = category;
		this.availability = availability;
		this.company = company;
		this.numberOfItemAvailable = numberOfItemAvailable;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscountInPrecentage() {
		return discountInPrecentage;
	}

	public void setDiscountInPrecentage(Double discountInPrecentage) {
		this.discountInPrecentage = discountInPrecentage;
	}

	public Double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	

	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	public Integer getNumberOfItemAvailable() {
		return numberOfItemAvailable;
	}


	public void setNumberOfItemAvailable(Integer numberOfItemAvailable) {
		this.numberOfItemAvailable = numberOfItemAvailable;
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
		Products other = (Products) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
