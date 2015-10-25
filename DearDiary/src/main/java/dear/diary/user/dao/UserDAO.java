package dear.diary.user.dao;

import dear.diary.user.model.User;

public interface UserDAO {
    public void saveUser(User user);
    public User loadUserByUserName(String username)  throws UserNotExistException;
    public User loadUserByUserNameAndPassword(String username, String password) throws Exception;
}
