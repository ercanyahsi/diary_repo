package dear.diary.diary.service;

import org.hibernate.SessionFactory;

import dear.diary.diary.dao.DiaryDAO;
import dear.diary.diary.dao.DiaryDAOImpl;
import dear.diary.diary.model.Diary;

public class DiaryService {

	private DiaryDAO diaryDAO;
	
	public DiaryService(SessionFactory sessionFactory) {
		this.diaryDAO = new DiaryDAOImpl(sessionFactory);
	}
	
	public void saveDiary(Diary diary){
		this.diaryDAO.saveDiary(diary);
	}
}
