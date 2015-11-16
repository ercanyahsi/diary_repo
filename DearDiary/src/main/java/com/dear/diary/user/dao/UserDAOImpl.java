package com.dear.diary.user.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dear.diary.diarypage.model.DiaryPage;
import com.dear.diary.user.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public void saveUser(User user) {
		currentSession().save(user);
	}

	public User loadUserByUserName(String username) throws UserNotExistException {
		Criteria cr = currentSession().createCriteria(User.class);
		cr.add(Restrictions.eq("username", username));
		List<User> list = (List<User>) cr.list();
		if (list.size() == 0)
			throw new UserNotExistException();
		return (User) cr.list().iterator().next();
	}

	public User loadUserByUserNameAndPassword(String username, String password) throws Exception {
		Query query = currentSession().createQuery(
				"select u from User u left join fetch u.diaries d where u.username=:username and u.password=:password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<User> list = query.list();
		if (list.size() == 0)
			throw new Exception("User Not Found");
		return (User) list.iterator().next();
	}
	
	public boolean userLike(int userId, int recordId) {

    	Query queryShared = currentSession().createQuery("select count(*) from User u join u.userLikes l where u.id=:userId and l.recordId=:recordId ")
    			.setParameter("userId", userId)
    			.setParameter("recordId", recordId);
    	
    	List lst = queryShared.list();
    	
    	if (lst.size()==0)
    		return false;
    	if (Integer.parseInt(lst.get(0).toString())==1)
    		return true;
    	else 
    		return false;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	public void like(int userId, int recordId) {

    	Query queryShared = currentSession().createQuery("select u from User u left join fetch u.userLikes l where u.id=:userId ")
    			.setParameter("userId", userId);
    	
    	
    	DiaryPage dp = (DiaryPage) currentSession().get(DiaryPage.class, recordId);
    	
    	User user =  (User) queryShared.list().iterator().next();
    	if (!user.getUserLikes().contains(dp)) {
    		user.getUserLikes().add(dp);
    		dp.setLikeCount(dp.getLikeCount()+1);
    	}
	}
	


	public void unlike(int userId, int recordId) {

    	Query queryShared = currentSession().createQuery("select u from User u left join fetch u.userLikes l where u.id=:userId ")
    			.setParameter("userId", userId);
    	
    	
    	DiaryPage dp = (DiaryPage) currentSession().get(DiaryPage.class, recordId);
    	
    	User user =  (User) queryShared.list().iterator().next();
    	if (user.getUserLikes().contains(dp)) {
    		user.getUserLikes().remove(dp);
    		dp.setLikeCount(dp.getLikeCount()-1);
    	}
	}
}
