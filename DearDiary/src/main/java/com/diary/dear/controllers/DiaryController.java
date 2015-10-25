package com.diary.dear.controllers;

import java.sql.Date;
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

		return "redirect:/diary/write/"+DateUtils.getCurrentDate();
	}

    @RequestMapping(value="/write/{date}", method=RequestMethod.GET)
	public String toWrite(@PathVariable Date date, HttpSession session, Model model){
		UserProfile up = LoginController.getUserProfile(session);
		
		Set<Diary> list = (Set<Diary>) up.getDiaries();
		Diary diary = (Diary) list.iterator().next();
		
		DiaryPage diaryPage = diarypageService.loadByDate(diary.getDiaryId(), date);
		if (diaryPage==null){
			diaryPage = new DiaryPage();
			diaryPage.setPageDate(date);
		}
		
		model.addAttribute("diaryPage", diaryPage);
		
		return "diary/write";
	}
    
	
	@RequestMapping("/save")
	public String write(@ModelAttribute("diaryPage") DiaryPage diaryPage, BindingResult result, Model model, HttpSession session, RedirectAttributes redirectAttributes){
		
		UserProfile up = LoginController.getUserProfile(session);
		
		Set<Diary> list = (Set<Diary>) up.getDiaries();
		Diary diary = (Diary) list.iterator().next();
		diaryPage.setDiaryId(diary.getDiaryId());
		
		diaryPage.setContentLanguage(1);
		diarypageService.saveOrUpdateDiaryPage(diaryPage);
        redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("islem.basarili", new String[]{}, Locale.ENGLISH));
        
		return "redirect:/diary/write/"+diaryPage.getPageDate()+"/";
	}

    @RequestMapping(value="/share/{date}", method=RequestMethod.GET)
	public String sharePage(@PathVariable Date date, Model model, HttpSession session, RedirectAttributes redirectAttributes) {

    	UserProfile up = LoginController.getUserProfile(session);
    	Diary diary = up.getDiaries().iterator().next();
    	diarypageService.sharePage(diary.getDiaryId(), date);
    	
    	redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("islem.basarili", new String[]{}, Locale.ENGLISH));
		return "redirect:/diary/list/";
	}

}
