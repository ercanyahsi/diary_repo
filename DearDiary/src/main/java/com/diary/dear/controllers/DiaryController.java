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
import util.common.constants.IConstants;

@Controller
@RequestMapping("/diary")
public class DiaryController {
	//test
	@Autowired
	DiaryPageService diarypageService;
	

    @Autowired
    private MessageSource messageSource;
	
	@RequestMapping("/list/{pageNumber}")
	public String listDiaries(Model model, @PathVariable int pageNumber, HttpSession session){

		UserProfile up = LoginController.getUserProfile(session);
		Set<Diary> list = (Set<Diary>) up.getDiaries();
		Diary diary = (Diary) list.iterator().next();

		long totalCount = diarypageService.getTotalCount(diary, DateUtils.getCurrentDate());
		int lastPageNumber = (int) ((totalCount / IConstants.PAGE_COUNT_DIARY_LIST) + 1);
		
		model.addAttribute("lastPageNumber", new Integer(lastPageNumber)); 
		model.addAttribute("activePage", new Integer(pageNumber)); 
		model.addAttribute("somePages", diarypageService.getByDate(diary, DateUtils.getCurrentDate(), pageNumber, IConstants.PAGE_COUNT_DIARY_LIST));
		
		
		return "diary/listdiaries";
	}

	@RequestMapping("/select")
	public String selectDiary(Model model, HttpSession session){

		return "redirect:/diary/write/"+DateUtils.getCurrentDate();
	}

    @RequestMapping(value="/write/{date}", method=RequestMethod.GET)
	public String toWrite(@PathVariable Date date, HttpSession session, Model model) throws Exception{
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
