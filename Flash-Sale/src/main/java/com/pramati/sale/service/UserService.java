/**
 * 
 */
package com.pramati.sale.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.pramati.sale.entity.Address;
import com.pramati.sale.entity.Authority;
import com.pramati.sale.entity.Users;
import com.pramati.sale.repository.AuthorityRepositories;
import com.pramati.sale.repository.UserRepository;
import com.pramati.sale.util.UserValidator;

/**
 * @author sudhirk
 *
 */
@Service
@Transactional
public class UserService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	private AuthorityRepositories authorityRepository;
	
	
	@Autowired
	UserValidator userValidator;
	
	public List<Users> getAllUsers(){
		return userRepository.findAll();
	}
	

	public String registerNewUser(Users users) {
		
		Users dbUser = userRepository.findByUserName(users.getUserName());
		if(null != dbUser)
			return "username exist";
		
		// to store address
		Set<Address> userGivenAddress = users.getAddress();
		if(! addressService.processUserAddress(userGivenAddress))// means address is valid
			return "Address required";
		
		
		String springSecuritySupportedPassword = "{noop}".concat(users.getPassword().trim());
		users.setPassword(springSecuritySupportedPassword);
		users.setEnabled(true);
		
		
		//Since username is also registered with Authority, so add it in Authority also
		Authority userAuthority = new Authority(users.getUserName(), "ROLE_USER");
		//save it to database
		authorityRepository.save(userAuthority);
		
		if(userRepository.save(users) != null)
			return "success";
		
		return "failure";
	}
	
	// for form validator
		@InitBinder
		public void initBinder(WebDataBinder dataBinder) {
			Object validatorClass = dataBinder.getTarget();
			if(validatorClass == null)
				return;
			if(validatorClass.getClass() == Users.class)
				dataBinder.setValidator(userValidator);
		}
}
