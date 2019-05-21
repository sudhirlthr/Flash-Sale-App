/**
 * 
 */
package com.pramati.sale.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pramati.sale.entity.Users;
import com.pramati.sale.repository.UserRepository;
import com.pramati.sale.service.UserService;
import com.pramati.sale.util.UserNotFoundException;

/**
 * @author sudhirk
 * 24-Apr-2019
 */
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	
	
	
	@PostMapping(path = "/flash-sale/user/registerUser", consumes = "application/json")
	public ResponseEntity<?> registerUser(@RequestBody Users users) {
		String message = userService.registerNewUser(users);
		
		/*
		 * Below code will give created status as HTTP 201 status
		 * 
		 * 
		Users savedUser = userRepository.save(users);
		
		URI uriLocation = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId())
		.toUri();
		
		return ResponseEntity.created(uriLocation).build(); */
		
		return new ResponseEntity(message,new HttpHeaders(),HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/flash-sale/user/usersList")
	public List<Users> getAllUsers() {
		return userService.getAllUsers();
	}	
	
	@GetMapping(path = "/flash-sale/user/{id}")
	public Users getUserDetailsById(@PathVariable Long id) {
		Users userById = userService.getUserById(id);
		if(userById == null) throw new UserNotFoundException("id-"+id);
		
		return userById;
	}
	
	/*
	 * @PostMapping(path = "/flash-sale/test", consumes = "application/json") public
	 * ResponseEntity<?> testUser(@RequestBody UserTest userTest) { return new
	 * ResponseEntity("Successsss",new HttpHeaders(),HttpStatus.OK); }
	 */
}
