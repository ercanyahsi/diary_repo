package com.diary.dear.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dear.diary.diarypage.service.DiaryPageService;
import com.dear.diary.user.model.User;
import com.dear.diary.user.service.UserService;

import ui.model.UserProfile;
import ui.tools.DateUtils;
import ui.tools.LoginController;
import util.common.constants.IConstants;

@Controller
@RequestMapping("/shared")
public class SharedDiaryController {

	@Autowired
	DiaryPageService diarypageService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/listshared/{pageNumber}")
	public String sharedList(Model model, @PathVariable int pageNumber, HttpSession session) {

		UserProfile up = LoginController.getUserProfile(session);

		long totalCount = diarypageService.getSharedListCount(up.getId(), up.getDiaries().iterator().next().getDiaryId());
		int lastPageNumber = (int) ((totalCount / IConstants.PAGE_COUNT_DIARY_LIST) + 1);

		model.addAttribute("lastPageNumberSharedList", new Integer(lastPageNumber)); 
		model.addAttribute("activePageSharedList", new Integer(pageNumber)); 
		model.addAttribute("sharedList", diarypageService.getSharedList(up.getId(), up.getDiaries().iterator().next().getDiaryId(), pageNumber, IConstants.PAGE_COUNT_DIARY_LIST ));
		model.addAttribute("userViewedList", diarypageService.getSharedUserViewedList(up.getId(), up.getDiaries().iterator().next().getDiaryId()));
		return "shared/listshared";
	}

	@RequestMapping("/like/{recordId}")
	public String like(Model model, @PathVariable int recordId, HttpSession session) throws Exception {
		UserProfile up = LoginController.getUserProfile(session);
		userService.like(up.getId(), recordId);
		return "redirect:/shared/view/"+recordId;
	}

	@RequestMapping("/unlike/{recordId}")
	public String unlike(Model model, @PathVariable int recordId, HttpSession session) throws Exception {
		UserProfile up = LoginController.getUserProfile(session);
		userService.unlike(up.getId(), recordId);
		return "redirect:/shared/view/"+recordId;
	}
	
	
	@RequestMapping("/view/{recordId}")
	public String viewPage(Model model, @PathVariable int recordId, HttpSession session) throws Exception {
		UserProfile up = LoginController.getUserProfile(session);
		model.addAttribute("diaryPage", diarypageService.viewPage(up.getId(), recordId));
		model.addAttribute("userLike", userService.userLike(up.getId(), recordId));
		return "shared/view";
	}
}
