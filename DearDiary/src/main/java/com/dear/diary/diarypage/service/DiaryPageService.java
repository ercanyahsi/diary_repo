package com.dear.diary.diarypage.service;

import java.sql.Date;
import java.util.List;
import java.util.Random;

import javax.naming.Context;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;

import com.dear.diary.diary.model.Diary;
import com.dear.diary.diarypage.dao.DiaryPageDAO;
import com.dear.diary.diarypage.dao.DiaryPageDAOImpl;
import com.dear.diary.diarypage.model.DiaryPage;

public class DiaryPageService {

	private DiaryPageDAO diaryPageDAO;

	public DiaryPageService(SessionFactory sessionFactory) {
		this.diaryPageDAO = new DiaryPageDAOImpl(sessionFactory);
	}

	@Transactional
	public List<DiaryPage> getByDate(Diary diary, Date date, int pageNumber, int pageSize) {
		return diaryPageDAO.getByDate(diary, date, pageNumber, pageSize);
	}

	@Transactional
	public void saveDiaryPage(DiaryPage diaryPage) {
		diaryPageDAO.saveDiaryPage(diaryPage);
	}

	@Transactional
	public void saveOrUpdateDiaryPage(DiaryPage diaryPage) {
		diaryPageDAO.saveOrUpdateDiaryPage(diaryPage);
	}

	@Transactional
	public DiaryPage getRandomDiaryPage(int diaryId) {
		int max = diaryPageDAO.getMaxDiaryPageId(diaryId);
		Random generator = new Random();
		int choosen = generator.nextInt(max) + 1;
		return diaryPageDAO.loadByRecordId(diaryId, choosen);

	}

	@Transactional
	public DiaryPage loadByDate(int diaryId, Date date) {
		return diaryPageDAO.loadByDate(diaryId, date);
	}

	@Transactional
	public void sharePage(int diaryId, Date date) {
		diaryPageDAO.sharePage(diaryId, date);
	}
	
	@Transactional
	public List<DiaryPage> getSharedList(int userId, int diaryId, int pageNumber, int pageSize){
		return diaryPageDAO.getSharedList(userId, diaryId, pageNumber, pageSize);
	}
	
	@Transactional
	 public DiaryPage viewPage(int userId, int recordId) throws Exception {
		return diaryPageDAO.viewPage(userId, recordId);
	}
	
	@Transactional
	public List<DiaryPage> getSharedUserViewedList(int userId, int diaryId){
		return diaryPageDAO.getSharedUserViewedList(userId, diaryId);
	}
	
	@Transactional
	public long getTotalCount(Diary diary, Date date) {
		return diaryPageDAO.getTotalCount(diary, date);
	}
	

	@Transactional
    public long getSharedListCount(int userId, int diaryId) {
    	return diaryPageDAO.getSharedListCount(userId, diaryId);
    }
}
