package com.pramati.sale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pramati.sale.entity.Orders;
import com.pramati.sale.entity.Users;

public interface OrderRepository extends JpaRepository<Orders, Long> {
	List<Orders> findByUsers(Users users);
}
