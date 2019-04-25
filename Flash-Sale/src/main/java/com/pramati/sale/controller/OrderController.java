/**
 * 
 */
package com.pramati.sale.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pramati.sale.model.Availability;
import com.pramati.sale.model.Orders;
import com.pramati.sale.model.Product;
import com.pramati.sale.model.Users;
import com.pramati.sale.repository.OrderRepository;
import com.pramati.sale.repository.ProductRepository;
import com.pramati.sale.repository.UserRepository;

/**
 * @author sudhirk
 * 25-Apr-2019
 */
@Controller
public class OrderController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@RequestMapping(path = "/buy", method = RequestMethod.POST)
	public String buyProduct(Model model, @ModelAttribute("product") Product product) {
		
		product = productRepository.findById(product.getId()).get();
		product.setAvailability(Availability.sold); // this product has been sold
		productRepository.save(product);
		
		// get the user details
		Users users = userRepository.findByUserName("sudhir");
		
		// update Order table
		Set<Product> productSet = new HashSet<>();
		productSet.add(product);
		Orders orders = new Orders(users, productSet);
		orderRepository.save(orders);
		
		model.addAttribute("order", orders);
		//redirectAttributes.addFlashAttribute("order", order);
		return "redirect:/order";
	}
	
	@RequestMapping(path = "/order")
	public String orderRedirect(Model model) {
		//model.addAttribute("order", orderRepository.findAll());
		return "order";
	}
}
