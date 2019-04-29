/**
 * 
 */
package com.pramati.sale.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pramati.sale.model.Availability;
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
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(path = "/products")
	public String getAllProducts(Model model, HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("uri", request.getRequestURI());
		model.addAttribute("user", auth.getName());
		model.addAttribute("roles", auth.getAuthorities());
		
		List<Product> productLists = productRepository.findByAvailability(Availability.available);
		model.addAttribute("product", productLists);
		return "product";
	}
	
	@RequestMapping(path = "/myOrderedProducts/user/{user}",method = RequestMethod.GET)
	public String getMyOrdredProductsByPathVariable(@PathVariable("user") String user, Model model, HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("uri", request.getRequestURI());
		model.addAttribute("user", auth.getName());
		model.addAttribute("roles", auth.getAuthorities());
		
		Users users = userRepository.findByUsername(user);
		model.addAttribute("myProducts", orderRepository.findByUsers(users));
		return "myProducts";
	}
	
	@RequestMapping(path = "/myOrderedProducts",method = RequestMethod.GET)
	public String getMyOrdredProducts(Model model, Principal principal, HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("uri", request.getRequestURI());
		model.addAttribute("user", auth.getName());
		model.addAttribute("roles", auth.getAuthorities());
		
		String username = "";
		if(null != principal && null != principal.getName())
			username = principal.getName();
		System.out.println("Order for user = "+username);
		Users users = userRepository.findByUsername(username);
		model.addAttribute("myProducts", orderRepository.findByUsers(users));
		return "myProducts";
	}
}
