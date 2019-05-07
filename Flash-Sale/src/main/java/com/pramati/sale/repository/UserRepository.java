/**
 * 
 */
package com.pramati.sale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pramati.sale.entity.Users;

/**
 * @author sudhirk
 * 24-Apr-2019
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	public Users findByEmail(String email);
	public Users findByUserName(String userName);
}
