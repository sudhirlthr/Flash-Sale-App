/**
 * 
 */
package com.pramati.sale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pramati.sale.entity.Company;

/**
 * @author sudhirk
 * 25-Apr-2019
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
