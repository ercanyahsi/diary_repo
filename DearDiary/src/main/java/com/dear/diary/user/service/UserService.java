package com.dear.diary.user.service;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dear.diary.diary.model.Diary;
import com.dear.diary.diary.service.DiaryService;
import com.dear.diary.user.dao.UserDAO;
import com.dear.diary.user.dao.UserDAOImpl;
import com.dear.diary.user.dao.UserExistException;
import com.dear.diary.user.dao.UserNotExistException;
import com.dear.diary.user.model.User;

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

	@Transactional
	public boolean userLike(int userId, int recordId) {
		return userDAO.userLike(userId, recordId);
	}
	
	@Transactional
	public void like(int userId, int recordId) {
		userDAO.like(userId, recordId);
	}
	
	@Transactional
	public void unlike(int userId, int recordId) {
		userDAO.unlike(userId, recordId);
	}
}
