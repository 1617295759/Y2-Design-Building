package dao;

import beans.User;

public interface UserDAO {
	//
	public int queryByUsername(User user);
	
	public boolean updateInfo(String username,User user);

	public User getUser(String username);
	public User getUser(int userID);
	//注册新用户
	public int addUser(User user);
}
