package dear.diary.diarypage.dao;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dear.diary.diary.model.Diary;
import dear.diary.diarypage.model.DiaryPage;
import dear.diary.user.model.User;

@Repository
public class DiaryPageDAOImpl implements DiaryPageDAO {


    private SessionFactory sessionFactory;
    
    @Autowired
    public DiaryPageDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public DiaryPage getByDate(Diary diary, Date date){
    	DiaryPage result = null;
    	

        Criteria cr = currentSession().createCriteria(DiaryPage.class);
        cr.add(Restrictions.eq("diaryId", diary.getDiaryId()));
        cr.add(Restrictions.eq("pageDate", date));
        List<DiaryPage> list = (List<DiaryPage>) cr.list();
        if (list.size()>0)
        	result = (DiaryPage) list.iterator().next();
        	
    	
    	return result;
    }
    

    public void saveDiaryPage(DiaryPage diaryPage) {
       currentSession().save(diaryPage);
    }


    public void saveOrUpdateDiaryPage(DiaryPage diaryPage) {
       currentSession().saveOrUpdate(diaryPage);
    }
    

    private Session currentSession(){
    	return this.sessionFactory.getCurrentSession();
    }
 
}
