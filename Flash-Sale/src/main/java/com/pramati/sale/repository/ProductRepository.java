/**
 * 
 */
package com.pramati.sale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pramati.sale.entity.Availability;
import com.pramati.sale.entity.Products;

/**
 * @author sudhirk
 * 25-Apr-2019
 */
public interface ProductRepository extends JpaRepository<Products, Long> {
	public List<Products> findByAvailability(Availability availability);
	public Products findByNumberOfItemAvailableAndId(Integer numberOfItemAvailable, Long productId);
}
