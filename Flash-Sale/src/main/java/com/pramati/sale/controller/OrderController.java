/**
 * 
 */
package com.pramati.sale.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.pramati.sale.entity.Orders;
import com.pramati.sale.repository.UserRepository;
import com.pramati.sale.service.OrderService;

/**
 * @author sudhirk 25-Apr-2019
 */
@RestController
public class OrderController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderService orderService;

	/*
	 * @RequestMapping(path =
	 * "/flash-sale/buy/productid/{productid}/numberOfItem/{numberOfItem}", method =
	 * RequestMethod.POST) public String buyProduct(Model
	 * model, @PathVariable("productid") Long productid,
	 * 
	 * @PathVariable("numberOfItem") Integer numberOfItem, HttpServletRequest
	 * request) { Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); return
	 * orderService.buyProduct(model, productid, numberOfItem, auth.getName(),
	 * request); }
	 */
	@RequestMapping(path="/flash-sale/buy", consumes = "application/json", method = RequestMethod.POST)
	public String buyProducts(@RequestBody String data) throws JsonParseException, JsonMappingException, IOException {
		return orderService.userBoughtProducts(data);
	}
	

	@RequestMapping(path = "/flash-sale/order") // redirecting to Order page if user buy a products successfully
	public String orderRedirect(Model model, HttpServletRequest request) {
		return "order";
	}

	@RequestMapping(path = "/flash-sale/failure") // redirecting to Failure in case of failure page if user buy a
													// products successfully
	public String failureRedirect(Model model, HttpServletRequest request) {
		return "Failure";
	}

	@RequestMapping(path = "/flash-sale/myOrderedProducts", method = RequestMethod.GET)
	public List<Orders> getMyOrdredProductsByPathVariable(Authentication auth, @RequestParam(value = "username") String username) {
		if(auth != null && auth.getName() != null)
			return orderService.myOrderedProducts(auth.getName());
		else if(username != null)
			return orderService.myOrderedProducts(username);
		
		return new ArrayList<Orders>();
	}
}
