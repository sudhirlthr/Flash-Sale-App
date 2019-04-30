/**
 * 
 */
package com.pramati.sale.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pramati.sale.model.Address;
import com.pramati.sale.model.Authority;
import com.pramati.sale.model.Users;
import com.pramati.sale.repository.AuthorityRepositories;
import com.pramati.sale.repository.UserRepository;
import com.pramati.sale.util.UserValidator;

/**
 * @author sudhirk
 * 24-Apr-2019
 */
@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepositories authorityRepository;
	
	
	@Autowired
	UserValidator userValidator;
	
	@RequestMapping(path = "/")
	public String homePage() {
		return "home";
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String forwardRegisterPage(Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("uri", request.getRequestURI());
		model.addAttribute("user", auth.getName());
		model.addAttribute("roles", auth.getAuthorities());
		
		Users users = new Users();
		Address address = new Address();
		model.addAttribute("users",users);
		model.addAttribute("address", address);
		return "Registration"; //forward the view name
	}
	
	@RequestMapping(path = "/registerUser", method = RequestMethod.POST)
	public String registerUser(Model model, @ModelAttribute("Users") @Validated Users users,
	         BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("uri", request.getRequestURI());
		model.addAttribute("user", auth.getName());
		model.addAttribute("roles", auth.getAuthorities());
		
		
		if(result.hasErrors()) {
			return "redirect:/register";
		}
		System.out.println("\n\nPassword = "+users.getPassword());
		String springSecuritySupportedPassword = "{noop}".concat(users.getPassword().trim());
		users.setPassword(springSecuritySupportedPassword);
		
		//enable user
		users.setEnabled(true);
		
		
		//Since username is also registered with Authority, so add it in Authority also
		Authority userAuthority = new Authority(users.getUserName(), "ROLE_USER");
		//save it to database
		authorityRepository.save(userAuthority);
		
		userRepository.save(users);
		redirectAttributes.addFlashAttribute("users", users);
		return "redirect:/success";
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
	
	
	@RequestMapping(path = "/usersList", method = RequestMethod.GET)
	public String getUsers(Model model, HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("uri", request.getRequestURI());
		model.addAttribute("user", auth.getName());
		model.addAttribute("roles", auth.getAuthorities());
		
		model.addAttribute("users", userRepository.findAll());
		return "users";
	}
	
	@RequestMapping(path="/success")
	public String successMessage(Model model, HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("uri", request.getRequestURI());
		model.addAttribute("user", auth.getName());
		model.addAttribute("roles", auth.getAuthorities());
		
		return "success";
	}
		
	
}
