package com.diary.dear.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dear.diary.diarypage.service.DiaryPageService;
import ui.model.UserProfile;
import ui.tools.LoginController;

@Controller
@RequestMapping("/shared")
public class SharedDiaryController {

	@Autowired
	DiaryPageService diarypageService;
	
	@RequestMapping("/listshared")
	public String sharedList(Model model, HttpSession session) {

		UserProfile up = LoginController.getUserProfile(session);
		model.addAttribute("sharedList", diarypageService.getSharedList(up.getDiaries().iterator().next().getDiaryId()));
		return "shared/listshared";
	}
	@RequestMapping("/view/{recordId}")
	public String viewPage(Model model, @PathVariable int recordId, HttpSession session) throws Exception {
		UserProfile up = LoginController.getUserProfile(session);
		model.addAttribute("diaryPage", diarypageService.viewPage(up.getId(), recordId));
		return "shared/view";
	}
}
