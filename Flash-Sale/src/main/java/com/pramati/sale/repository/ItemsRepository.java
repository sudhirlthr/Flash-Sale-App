/**
 * 
 */
package com.pramati.sale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pramati.sale.model.Product;

/**
 * @author sudhirk
 * 24-Apr-2019
 */
public interface ItemsRepository extends JpaRepository<Product, Long> {

}
