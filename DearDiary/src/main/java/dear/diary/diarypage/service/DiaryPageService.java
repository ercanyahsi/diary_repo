package dear.diary.diarypage.service;

import java.sql.Date;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dear.diary.diary.model.Diary;
import dear.diary.diarypage.dao.DiaryPageDAO;
import dear.diary.diarypage.dao.DiaryPageDAOImpl;
import dear.diary.diarypage.model.DiaryPage;

public class DiaryPageService {

	private DiaryPageDAO diaryPageDAO;
	
	public DiaryPageService(SessionFactory sessionFactory) {
		this.diaryPageDAO = new DiaryPageDAOImpl(sessionFactory);
	}

	@Transactional
	 public DiaryPage getByDate(Diary diary, Date date){
		 return diaryPageDAO.getByDate(diary, date);
	 }
	
	@Transactional
	public void saveDiaryPage(DiaryPage diaryPage) {
		diaryPageDAO.saveDiaryPage(diaryPage);
	}
	
	@Transactional
	public void saveOrUpdateDiaryPage(DiaryPage diaryPage) {
		diaryPageDAO.saveOrUpdateDiaryPage(diaryPage);
	}
}
