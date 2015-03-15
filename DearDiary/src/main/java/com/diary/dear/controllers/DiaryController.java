package com.diary.dear.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ui.model.UserProfile;
import ui.tools.DateUtils;
import ui.tools.LoginController;
import dear.diary.diary.model.Diary;
import dear.diary.diarypage.model.DiaryPage;
import dear.diary.diarypage.service.DiaryPageService;

@Controller
@RequestMapping("/diary")
public class DiaryController {
	
	@Autowired
	DiaryPageService diarypageService;
	
	@RequestMapping(method=RequestMethod.GET, params="list")
	public String listDiaries(Model model, HttpSession session){

		UserProfile up = LoginController.getUserProfile(session);
		Set<Diary> list = (Set<Diary>) up.getDiaries();
		Diary diary = (Diary) list.iterator().next();
		
		List<DiaryPage> somePages = new ArrayList<DiaryPage>();
		Date current = DateUtils.getCurrentDate();
		for (int i=0;i<10;i++){
			
			DiaryPage diaryPage = diarypageService.getByDate(diary, current);
			if (diaryPage==null)
				diaryPage = new DiaryPage(current);
			
			somePages.add(diaryPage);
			current = DateUtils.before(current, 1);
		}
		
		model.addAttribute("somePages", somePages);
		
		
		return "diary/listdiaries";
	}
	
	@RequestMapping(method=RequestMethod.GET, params="write")
	public String toWrite(@RequestParam("write") String write, HttpSession session, Model model){
		
		UserProfile up = LoginController.getUserProfile(session);
		
		Set<Diary> list = (Set<Diary>) up.getDiaries();
		Diary diary = (Diary) list.iterator().next();
		
		DiaryPage diaryPage = diarypageService.getByDate(diary, Date.valueOf(write));
		if (diaryPage==null){
			diaryPage = new DiaryPage();
			diaryPage.setDiaryId(diary.getDiaryId());
			diaryPage.setPageDate(Date.valueOf(write));
		}
		
		model.addAttribute("diaryPage", diaryPage);
		
		return "diary/write";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String write(@ModelAttribute("diaryPage") DiaryPage diaryPage, BindingResult result){
		
		diaryPage.setContentLanguage(1);
		diarypageService.saveOrUpdateDiaryPage(diaryPage);
		return "redirect:diary?list";
	}

}
