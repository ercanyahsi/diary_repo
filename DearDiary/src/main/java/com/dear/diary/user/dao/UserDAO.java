package com.dear.diary.user.dao;

import com.dear.diary.user.model.User;

public interface UserDAO {
    public void saveUser(User user);
    public User loadUserByUserName(String username)  throws UserNotExistException;
    public User loadUserByUserNameAndPassword(String username, String password) throws Exception;
    public boolean userLike(int userId, int recordId);
	public void like(int userId, int recordId);
	public void unlike(int userId, int recordId);
}
