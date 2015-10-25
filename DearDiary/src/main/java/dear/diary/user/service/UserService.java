package dear.diary.user.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dear.diary.diary.model.Diary;
import dear.diary.diary.service.DiaryService;
import dear.diary.user.dao.UserDAO;
import dear.diary.user.dao.UserDAOImpl;
import dear.diary.user.dao.UserExistException;
import dear.diary.user.dao.UserNotExistException;
import dear.diary.user.model.User;

public class UserService {
	
	@Autowired
	private DiaryService diaryService;

	private UserDAO userDAO;
	
	public UserService(SessionFactory sessionFactory) {
		this.userDAO = new UserDAOImpl(sessionFactory);
	}
	
	
	@Transactional
	public void createUser(User user) throws UserExistException, Exception {
		
		try{
			userDAO.loadUserByUserName(user.getUsername());
			//if user exist with same username throw exception
			throw new UserExistException();
		}
		catch(UserNotExistException ex){
			//do nothing if user not exist
		}

		Diary diary = new Diary();
		diary.setDiaryName("<>");
		
		user.getDiaries().add(diary);
		
		userDAO.saveUser(user);
	}

	@Transactional
	public User loadUserByUserName(String username)  throws Exception {
		return userDAO.loadUserByUserName(username);
	}
	
	@Transactional
	public User loadUserByUserNameAndPassword(String username, String password) throws Exception {
		return userDAO.loadUserByUserNameAndPassword(username, password);
	}
}
