package com.diary.dear.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/navigate")
public class NavigationController {

	@RequestMapping(value = "/{page}")
	public String navigate(@PathVariable String page, Model model) {
		String pageToGo =  page.replace('_', '/');
		return pageToGo;
	}

	
	
}
