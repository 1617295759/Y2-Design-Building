package dao;

import beans.User;

public interface UserDAO {
	//
	public int queryByUsername(User user);
	
	public boolean updateInfo(int userid,User user);
	public boolean updateInfo(int userid,String newinfo,String info);
	public User getUser(String username);
	public User getUser(int userID);
	//注册新用户
	public int addUser(User user);
}
