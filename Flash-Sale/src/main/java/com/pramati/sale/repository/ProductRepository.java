/**
 * 
 */
package com.pramati.sale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pramati.sale.model.Availability;
import com.pramati.sale.model.Product;

/**
 * @author sudhirk
 * 25-Apr-2019
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
	public List<Product> findByAvailability(Availability availability);
}
