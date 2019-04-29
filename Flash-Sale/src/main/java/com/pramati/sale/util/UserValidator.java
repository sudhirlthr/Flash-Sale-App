/**
 * 
 */
package com.pramati.sale.util;


import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pramati.sale.model.Users;
import com.pramati.sale.repository.UserRepository;

/**
 * @author sudhirk
 * 24-Apr-2019
 */
@Component
public class UserValidator implements Validator{
	
	private EmailValidator emailValidator = EmailValidator.getInstance();
	
	@Autowired
	UserRepository userRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == Users.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Users users = (Users) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.users.userName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.users.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.users.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.users.phone");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.users.gender");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.users.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.users.email");
		
		Users dbUser = userRepository.findByEmail(users.getEmail());
		
		if(! emailValidator.isValid(users.getEmail()))
			errors.rejectValue("email", "Pattern.users.email");
		else if(dbUser != null)
			 errors.rejectValue("email", "Duplicate.users.email");
		
		dbUser = userRepository.findByUsername(users.getUserName());
		if(dbUser != null) {
			errors.rejectValue("userName", "Duplicate.users.userName");
		}
	}
		
	
}
