/**
 * 
 */
package com.pramati.sale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pramati.sale.entity.Products;
import com.pramati.sale.service.ProductService;


/**
 * @author sudhirk
 * 25-Apr-2019
 */
@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(path = "/flash-sale/products/productList")
	@ResponseBody
	public List<Products> getAllProducts() {		
		return productService.getAllAvailableProducts();
	}
}
