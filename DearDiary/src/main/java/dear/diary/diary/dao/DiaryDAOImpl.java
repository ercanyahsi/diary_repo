package dear.diary.diary.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dear.diary.diary.model.Diary;

public class DiaryDAOImpl implements DiaryDAO {

    private SessionFactory sessionFactory;

    public DiaryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    

    
    private Session currentSession(){
    	return this.sessionFactory.getCurrentSession();
    }
    
    public void saveDiary(Diary diary){
    	currentSession().save(diary);
    }
    
}
