/**
 * 
 */
package com.pramati.sale.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pramati.sale.entity.Availability;
import com.pramati.sale.entity.Orders;
import com.pramati.sale.entity.Products;
import com.pramati.sale.entity.Users;
import com.pramati.sale.repository.OrderRepository;
import com.pramati.sale.repository.ProductRepository;
import com.pramati.sale.repository.UserRepository;


/**
 * @author sudhirk
 *
 */
@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	/*
	 * @Transactional public String buyProduct(Model model, Long productId, Integer
	 * numberOfItem, String username, HttpServletRequest request) {
	 * 
	 * model.addAttribute("uri", request.getRequestURI());
	 * model.addAttribute("user", username); //model.addAttribute("roles",
	 * auth.getAuthorities());
	 * 
	 * 
	 * if (username == null || username.equals("")) { return "failure"; }
	 * 
	 * Products product = productRepository.findById(productId).get();
	 * 
	 * if(product != null && product.getNumberOfItemAvailable() >= numberOfItem) {
	 * 
	 * if(product.getNumberOfItemAvailable() == numberOfItem )// if number of item
	 * is same as number of stock for a product
	 * product.setAvailability(Availability.out_of_stock); // Number of item for a
	 * product is sold in in stock else
	 * product.setAvailability(Availability.available); // Number of available
	 * product is more than ordered quantity
	 * 
	 * // update number of item available for same product after purchase
	 * product.setNumberOfItemAvailable(product.getNumberOfItemAvailable()-
	 * numberOfItem); productRepository.save(product);
	 * 
	 * // get the user details to create a map between order and user Users users =
	 * userRepository.findByUsername(username);
	 * 
	 * // update Order table Set<Products> productSet = new HashSet<>();
	 * productSet.add(product); Orders orders = new Orders(users, productSet);
	 * orderRepository.save(orders);
	 * 
	 * model.addAttribute("order", orders); //
	 * redirectAttributes.addFlashAttribute("order", order); return "Success"; }else
	 * { return "Number of item requested is not available"; } }
	 */
	
	@Transactional
	public String userBoughtProducts(String data){
		String message = "success";
		// To update product stocks and their availability one at a time
		Set<Products> productSets = new HashSet<>();

		/*
		ObjectMapper objectMapper = new ObjectMapper();
		JSONObject readValue = objectMapper.readValue(httpServletRequest.getInputStream(), JSONObject.class);

		//JSONObject jsonObject = new JSONObject(httpServletRequest.getReader().lines().collect(Collectors.joining()));
		Enumeration<String> parameterNamesEnumeration = httpServletRequest.getParameterNames();
		while(parameterNamesEnumeration.hasMoreElements()) { // iterate over number of product ordered against each product id
			String productId = parameterNamesEnumeration.nextElement(); // get product id
			String numberOfItemsPurchased = httpServletRequest.getParameter(productId);
			if(productId != null && numberOfItemsPurchased != null) {
				Products productsToBeUpdated = productRepository.findById(Long.parseLong(productId)).get();
				Integer numberOfItemsOrdered = Integer.parseInt(numberOfItemsPurchased);
				if(productsToBeUpdated.getNumberOfItemAvailable() < numberOfItemsOrdered) {
					message = "items ordered is more than availability";
					break;
				}else {
					if(productsToBeUpdated.getNumberOfItemAvailable() == numberOfItemsOrdered) // since number ordered products is equal to number of 
						productsToBeUpdated.setAvailability(Availability.out_of_stock);
					else
						productsToBeUpdated.setAvailability(Availability.sold);
					productsToBeUpdated.setNumberOfItemAvailable(productsToBeUpdated.getNumberOfItemAvailable()-numberOfItemsOrdered);
					productSets.add(productsToBeUpdated);
				}

			}
		}*/


		JSONObject jsonObject = new JSONObject(data);
		JSONArray jsonArray = jsonObject.getJSONArray("order");
		String username = jsonArray.getJSONObject(0).getString("username");
		for(int i=0 ; i < jsonArray.length() ; i++) {
			JSONObject userData = jsonArray.getJSONObject(i); // get each json object
			Long productId = userData.getLong("productid"); // get product id
			Integer numberOfItemsPurchased = userData.getInt("numberOfItemPurchased"); // number of item purchased for a product
			if(productId != null && numberOfItemsPurchased != null) {
				Products productsToBeUpdated = productRepository.findById(productId).get();
				if(productsToBeUpdated.getNumberOfItemAvailable() < numberOfItemsPurchased) {
					message = "items ordered is more than availability";
					break;
				}else {
					if(productsToBeUpdated.getNumberOfItemAvailable() == numberOfItemsPurchased) // if number ordered products is equal to number of item available
						productsToBeUpdated.setAvailability(Availability.out_of_stock);
					
					// else no need since if item quantity is more than purchased then it is already in available state
					productsToBeUpdated.setNumberOfItemAvailable(productsToBeUpdated.getNumberOfItemAvailable()-numberOfItemsPurchased);
					productSets.add(productsToBeUpdated);
				}
			}
		}
		productRepository.saveAll(productSets); // update all products with their availability
		// save ordered products
		Users users = userRepository.findByUserName(username);
		Orders orders = new Orders(users, productSets);
		orderRepository.save(orders);
		return message;
	}

	public List<Orders> myOrderedProducts(String username) {

		if(username != null && !username.equals("")) {
			Users users = userRepository.findByUserName(username);
			return orderRepository.findByUsers(users);

		}else {
			// model.addAttribute("myProducts", new ArrayList<Orders>(0));// otherwise send zero records
			return new ArrayList<Orders>(0);
		}
	}
}
