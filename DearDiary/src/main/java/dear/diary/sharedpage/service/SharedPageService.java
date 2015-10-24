package dear.diary.sharedpage.service;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;

import dear.diary.sharedpage.dao.SharedPageDAO;
import dear.diary.sharedpage.model.SharedPage;

public class SharedPageService {

	private SharedPageDAO sharedPageDAO;
	
	public SharedPageService(SessionFactory sessionFactory) {
		this.sharedPageDAO = new SharedPageDAO(sessionFactory);
	}

	
	@Transactional
	public void save(SharedPage sharedPage) {
		sharedPageDAO.saveSharedPage(sharedPage);
	}
	
}
