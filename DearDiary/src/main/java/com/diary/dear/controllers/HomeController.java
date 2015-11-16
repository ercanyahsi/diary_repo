package com.diary.dear.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dear.diary.user.dao.UserDAO;
import com.dear.diary.user.model.User;
import com.dear.diary.user.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {    
	@Autowired
    private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
//		List<User> users = userService.listUsers();
//		model.addAttribute("userList", users);
		
		return "home";
	}
	

	
}
