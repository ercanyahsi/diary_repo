package com.dear.diary.diary.service;

import org.hibernate.SessionFactory;

import com.dear.diary.diary.dao.DiaryDAO;
import com.dear.diary.diary.dao.DiaryDAOImpl;
import com.dear.diary.diary.model.Diary;

public class DiaryService {

	private DiaryDAO diaryDAO;
	
	public DiaryService(SessionFactory sessionFactory) {
		this.diaryDAO = new DiaryDAOImpl(sessionFactory);
	}
	
	public void saveDiary(Diary diary){
		this.diaryDAO.saveDiary(diary);
	}
}
