package com.pramati.sale.model;

import java.util.ArrayList;
import java.util.List;

public class ProductWrapperDTO {
	private List<Product> products = new ArrayList<>();

	public ProductWrapperDTO() {
	}

	public ProductWrapperDTO(List<Product> products) {
		this.products = products;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProducts(Product product) {
		this.products.add(product);
	}
}
