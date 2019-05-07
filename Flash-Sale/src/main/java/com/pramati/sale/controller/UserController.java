/**
 * 
 */
package com.pramati.sale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pramati.sale.entity.Users;
import com.pramati.sale.service.UserService;

/**
 * @author sudhirk
 * 24-Apr-2019
 */
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@PostMapping(path = "/flash-sale/registerUser", consumes = "application/json")
	public ResponseEntity<?> registerUser(@RequestBody Users users) {
		String message = userService.registerNewUser(users);
		return new ResponseEntity(message,new HttpHeaders(),HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/flash-sale/usersList")
	public List<Users> getAllUsers() {
		return userService.getAllUsers();
	}	
	
	/*
	 * @PostMapping(path = "/flash-sale/test", consumes = "application/json") public
	 * ResponseEntity<?> testUser(@RequestBody UserTest userTest) { return new
	 * ResponseEntity("Successsss",new HttpHeaders(),HttpStatus.OK); }
	 */
}
