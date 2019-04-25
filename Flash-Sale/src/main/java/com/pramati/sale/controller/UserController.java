/**
 * 
 */
package com.pramati.sale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.pramati.sale.model.Users;
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
	UserValidator userValidator;
	
	@RequestMapping(path = "/")
	public String homePage() {
		return "home";
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String forwardRegisterPage(Model model) {
		Users users = new Users();
		Address address = new Address();
		model.addAttribute("users",users);
		model.addAttribute("address", address);
		return "Registration"; //forward the view name
	}
	
	@RequestMapping(path = "/registerUser", method = RequestMethod.POST)
	public String registerUser(Model model, @ModelAttribute("Users") @Validated Users users,
	         BindingResult result, final RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return "redirect:/register";
		}
		userRepository.save(users);
		redirectAttributes.addFlashAttribute("user", users);
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
	public String getUsers(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "users";
	}
	
	@RequestMapping(path="/success")
	public String successMessage(Model model) {
		return "success";
	}
		
	
}
