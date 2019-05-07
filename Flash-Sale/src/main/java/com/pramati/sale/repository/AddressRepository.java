/**
 * 
 */
package com.pramati.sale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pramati.sale.entity.Address;

/**
 * @author sudhirk
 * 24-Apr-2019
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

}
