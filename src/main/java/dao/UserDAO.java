package dao;

import vo.Cart;
import vo.Orders;
import vo.User;

import java.util.ArrayList;

public interface UserDAO {	
	//
	public int queryByUsername(User user);
	
	public void updateInfo(String username,User user);
	
	public User getUser(String username);
	
	//
	public void addCart(String username, String productname);
	//
	public void deleteCart(Cart deletedcart);
	//
	public ArrayList<Cart> sortCartBytime(String user);
	
	
	//
	public void addOrder(String username, String productname);
	//
	public void deleteOrder(Orders deletedorder);
	//
	public ArrayList<Orders> sortOrderByTime(String user);
	
}
