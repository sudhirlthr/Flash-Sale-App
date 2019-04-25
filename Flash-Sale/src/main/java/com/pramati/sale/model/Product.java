package com.pramati.sale.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


/**
 * @author sudhirk
 * 24-Apr-2019
 */


@Entity
public class Product implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private Double price;
	private Double discountInPrecentage;
	private Double actualPrice;
	private String model;
	private String materialType;
	private String category;
	private Gender itemFor;
	private Availability availability;
	
	@ManyToOne
	private Company company;
	
	public Product() {}

	public Product(String name, Double price, Double discountInPrecentage, Double actualPrice, Company company,
			String model, String materialType, String category, Gender itemFor, Availability availability) {
		this.name = name;
		this.price = price;
		this.discountInPrecentage = discountInPrecentage;
		this.actualPrice = actualPrice;
		this.company = company;
		this.model = model;
		this.materialType = materialType;
		this.category = category;
		this.itemFor = itemFor;
		this.availability = availability;
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
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Gender getItemFor() {
		return itemFor;
	}

	public void setItemFor(Gender itemFor) {
		this.itemFor = itemFor;
	}

	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
