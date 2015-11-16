package com.dear.diary.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dear.diary.user.model.User;

@Component
public class UserRegistrationValidator implements Validator{

	@Autowired
	MessageSource messageSource;

	@Override
	public boolean supports(Class<?> clzz) {
		return User.class.isAssignableFrom(clzz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if (user.getUsername()==null || user.getUsername().trim().length()<6)
			errors.rejectValue("username", "required", messageSource.getMessage("lbl.kullaniciHatasi", null, null));
		
		if (user.getPassword()==null || user.getPassword().trim().length()<6)
			errors.rejectValue("password", "required", messageSource.getMessage("lbl.passwordHatasi", null, null));
		
		if (user.getEmail()==null || !new EmailValidator().validate(user.getEmail()) ) 
			errors.rejectValue("email", "required", messageSource.getMessage("lbl.emailHatasi", null, null));
			
		
		
	}

}
