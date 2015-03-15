package com.diary.dear.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ui.tools.MessageBinder;
import util.common.constants.IConstants;
import dear.diary.user.model.User;
import dear.diary.user.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	UserService userService;

	@RequestMapping(method=RequestMethod.GET, params="new")
	public String newLogin(Model model){
		
		model.addAttribute("user", new User());
		return "user/login";
	}
	

	@RequestMapping(method=RequestMethod.GET, params="logout")
	public String newLogin(Model model, HttpSession session){
		
		session.removeAttribute(IConstants.USERPROFILE_KEY);
		model.addAttribute("user", new User());
		return "user/login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String checkLogin(@ModelAttribute("user") User user, HttpSession session, HttpServletRequest request, Model model){
		try{
			User loadedUser = userService.loadUserByUserNameAndPassword(user.getUsername(), user.getPassword());
			ui.tools.LoginController.setUserProfile(session, loadedUser);
			return "home";
		}catch(Exception ex){
			MessageBinder.bindErrorMessage(model, ex.getMessage());
			return "user/login";
		}
	}
}