package com.diary.dear.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dear.diary.user.dao.UserExistException;
import com.dear.diary.user.model.User;
import com.dear.diary.user.service.UserService;
import com.dear.diary.validators.UserRegistrationValidator;

import ui.tools.LoginController;
import ui.tools.MessageBinder;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	UserRegistrationValidator userRegistrationValidator;
	

	@RequestMapping(method=RequestMethod.GET, params="new")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "user/register";
	}
	

	@RequestMapping(method=RequestMethod.POST)
	public String createUser(@ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) throws Exception {
		userRegistrationValidator.validate(user, result);
		if (result.hasErrors())
			return "user/register";
		else{
			try{
				userService.createUser(user);
				LoginController.setUserProfile(session, user);
			}catch(UserExistException ex){
				MessageBinder.bindErrorMessage(model, messageSource.getMessage("lbl.kullanicivar", null, null));
				return "user/register";
			}
		}
		return "user/view";
	}
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public String showUserProfile(@PathVariable String username, 	Model model) throws Exception {
		model.addAttribute("user", userService.loadUserByUserName(username));
		return "user/view";
	}
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(userRegistrationValidator);
	}
}
