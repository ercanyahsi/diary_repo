package com.diary.dear.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dear.diary.diary.model.Diary;
import dear.diary.diarypage.model.DiaryPage;
import dear.diary.diarypage.service.DiaryPageService;
import dear.diary.sharedpage.model.SharedPage;
import dear.diary.sharedpage.service.SharedPageService;
import ui.model.UserProfile;
import ui.tools.DateUtils;
import ui.tools.LoginController;

@Controller
@RequestMapping("/diary")
public class DiaryController {
	//test
	@Autowired
	DiaryPageService diarypageService;
	
	@Autowired
	SharedPageService sharedPageService;

    @Autowired
    private MessageSource messageSource;
	
	@RequestMapping("/list")
	public String listDiaries(Model model, HttpSession session){

		UserProfile up = LoginController.getUserProfile(session);
		Set<Diary> list = (Set<Diary>) up.getDiaries();
		Diary diary = (Diary) list.iterator().next();
		
		
		
//		List<DiaryPage> somePages = new ArrayList<DiaryPage>();
//		Date current = DateUtils.getCurrentDate();
//		for (int i=0;i<10;i++){
//			
//			DiaryPage diaryPage = diarypageService.getByDate(diary, current);
//			if (diaryPage==null)
//				diaryPage = new DiaryPage(current);
//			
//			somePages.add(diaryPage);
//			current = DateUtils.before(current, 1);
//		}
		
		model.addAttribute("somePages", diarypageService.getByDate(diary, DateUtils.getCurrentDate()));
		
		
		return "diary/listdiaries";
	}

	@RequestMapping("/select")
	public String selectDiary(Model model, HttpSession session){

		return "diary/selectdiary";
	}

    @RequestMapping(value="/write/{date}", method=RequestMethod.GET)
	public String toWrite(@PathVariable String date, HttpSession session, Model model){
		Date parsedDate = DateUtils.parseDate(date);
		UserProfile up = LoginController.getUserProfile(session);
		
		Set<Diary> list = (Set<Diary>) up.getDiaries();
		Diary diary = (Diary) list.iterator().next();
		
		DiaryPage diaryPage = diarypageService.loadByDate(diary, parsedDate);
		if (diaryPage==null){
			diaryPage = new DiaryPage();
			diaryPage.setDiaryId(diary.getDiaryId());
			diaryPage.setPageDate(parsedDate);
		}
		
		model.addAttribute("diaryPage", diaryPage);
		
		return "diary/write";
	}
	
	@RequestMapping("/save")
	public String write(@ModelAttribute("diaryPage") DiaryPage diaryPage, BindingResult result, Model model, RedirectAttributes redirectAttributes){
		
		diaryPage.setContentLanguage(1);
		diarypageService.saveOrUpdateDiaryPage(diaryPage);
        redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("islem.basarili", new String[]{}, Locale.ENGLISH));
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        
		return "redirect:/diary/write/"+df.format(diaryPage.getPageDate())+"/";
	}
	
	@RequestMapping("/share")
	public String sharePage(@ModelAttribute("diaryPage") DiaryPage diaryPage, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

		diaryPage.setContentLanguage(1);
		diarypageService.saveOrUpdateDiaryPage(diaryPage);
		
		SharedPage sharedPage = new SharedPage();
		sharedPage.setRecordId(diaryPage.getRecordId());
		sharedPageService.save(sharedPage);
        redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("islem.basarili", new String[]{}, Locale.ENGLISH));
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		return "redirect:/diary/write/"+df.format(diaryPage.getPageDate())+"/";
	}

}
