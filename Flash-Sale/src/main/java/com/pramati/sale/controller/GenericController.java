/**
 * 
 */
package com.pramati.sale.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sudhirk
 *
 */
@RestController
public class GenericController {
	
	@GetMapping("/flash-sale")
	public String home() {
		return "Flash-Sale greeting";
	}
}
