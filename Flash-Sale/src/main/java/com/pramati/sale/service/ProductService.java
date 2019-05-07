/**
 * 
 */
package com.pramati.sale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramati.sale.entity.Availability;
import com.pramati.sale.entity.Products;
import com.pramati.sale.repository.ProductRepository;

/**
 * @author sudhirk
 *
 */
@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Products> getAllAvailableProducts() {
		List<Products> productLists = productRepository.findByAvailability(Availability.available);
		return productLists;
	}
}