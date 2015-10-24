package dear.diary.sharedpage.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dear.diary.sharedpage.model.SharedPage;

public class SharedPageDAO {

	@Autowired
    private SessionFactory sessionFactory;

    public SharedPageDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void saveSharedPage(SharedPage sharedPage){
    	this.sessionFactory.getCurrentSession().save(sharedPage);
    }
}
