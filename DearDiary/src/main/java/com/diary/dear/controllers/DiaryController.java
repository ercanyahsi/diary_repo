package com.diary.dear.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ui.model.UserProfile;
import ui.tools.DateUtils;
import ui.tools.LoginController;
import ui.tools.MessageBinder;
import dear.diary.diary.model.Diary;
import dear.diary.diarypage.model.DiaryPage;
import dear.diary.diarypage.service.DiaryPageService;

@Controller
@RequestMapping("/diary")
public class DiaryController {
	//test
	@Autowired
	DiaryPageService diarypageService;

    @Autowired
    private MessageSource messageSource;
	
	@RequestMapping("/list")
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

    @RequestMapping(value="/write/{date}", method=RequestMethod.GET)
	public String toWrite(@PathVariable Date date, HttpSession session, Model model){
		
		UserProfile up = LoginController.getUserProfile(session);
		
		Set<Diary> list = (Set<Diary>) up.getDiaries();
		Diary diary = (Diary) list.iterator().next();
		
		DiaryPage diaryPage = diarypageService.getByDate(diary, date);
		if (diaryPage==null){
			diaryPage = new DiaryPage();
			diaryPage.setDiaryId(diary.getDiaryId());
			diaryPage.setPageDate(date);
		}
		
		model.addAttribute("diaryPage", diaryPage);
		
		return "diary/write";
	}
	
	@RequestMapping("/save")
	public String write(@ModelAttribute("diaryPage") DiaryPage diaryPage, BindingResult result, Model model, RedirectAttributes redirectAttributes){
		
		diaryPage.setContentLanguage(1);
		diarypageService.saveOrUpdateDiaryPage(diaryPage);
        redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("islem.basarili", new String[]{}, Locale.ENGLISH));
		return "redirect:/diary/write/"+diaryPage.getPageDate();
	}

}
