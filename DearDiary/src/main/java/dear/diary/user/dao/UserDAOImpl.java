package dear.diary.user.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dear.diary.user.model.User;

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

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
