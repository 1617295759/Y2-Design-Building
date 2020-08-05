package dao.impl;
import dao.UserDAO;
import database.DBConnect;
import vo.Cart;
import vo.Orders;
import vo.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;


public class UserDAOImpl implements UserDAO {

	//judge whether user's name and password matches
	public int queryByUsername(User user) {
		int flag = 0;
		String sql = "select * from mydb.user where username=?";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;
        try{   
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql); 
            pstmt.setString(1,user.getUsername()) ;   
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){  
                if(rs.getString("password").equals(user.getPassword())){
                	flag = 1;
                } 
            }   
            rs.close() ; 
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        }   
		return flag;
	}

	
	@Override
	public void updateInfo(String username, User user) {
		String sql = "update mydb.user set password = ?, sex=?,email=?,phone=?,age=? where username = ?";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;   
        try{

            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setString(1,user.getPassword());
            pstmt.setString(2, user.getSex());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhone());
            pstmt.setInt(5, user.getAge());
            pstmt.setString(6, username);
            pstmt.execute();
            pstmt.close() ;  
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        } 
		
	}

	@Override
	public void addCart(String username, String productname) {
		String sql = "insert into mydb.carts (username, productname, time) values (?,?,?)";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;   
        try{   
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql); 
            pstmt.setString(1,username) ; 
            pstmt.setString(2, productname);
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.execute();
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        } 
	}

	@Override
	public void deleteCart(Cart deletedcart) {
		String sql = "delete from mydb.carts where username = ? and productname = ? and time = ?";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;   
        try{   
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql); 
            pstmt.setString(1, deletedcart.getUser());
            pstmt.setString(2, deletedcart.getProduct());
            pstmt.setTimestamp(3, deletedcart.getTime());
            pstmt.execute();
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        } 
	}

	@Override
	public ArrayList<Cart> sortCartBytime(String user) {
		ArrayList<Cart> list = new ArrayList<Cart>();
		String sql = "select * from mydb.carts where username =? order by time";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;   
        try{   
        	dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql); 
            pstmt.setString(1, user);
            ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { 
	        	Cart cart = new Cart();
	        	cart.setUser(rs.getString(1));
	        	cart.setProduct(rs.getString(2));
	        	cart.setTime(rs.getTimestamp(3));
            	list.add(cart);
            }   
            rs.close() ; 
            pstmt.close() ;  
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        }   
		return list;
	}

	@Override
	public void addOrder(String username, String productname) {
		String sql = "insert into mydb.orders (username, productname, orderedtime) values (?,?,?)";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;   
        try{   
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql); 
            pstmt.setString(1,username) ; 
            pstmt.setString(2, productname);
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.execute();
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        }   
	}

	@Override
	public void deleteOrder(Orders order) {
		String sql = "delete from mydb.orders where username = ? and productname = ? and orderedtime = ?";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;   
        try{   
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql); 
            pstmt.setString(1, order.getUser());
            pstmt.setString(2, order.getProduct());
            pstmt.setTimestamp(3, order.getOrderedtime());
            pstmt.execute();
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        } 
	}

	@Override
	public ArrayList<Orders> sortOrderByTime(String user) {
		ArrayList<Orders> list = new ArrayList<Orders>();
		String sql = "select * from mydb.orders where username = ?";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;   
        try{     	
        	dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setString(1, user);
            ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { 
	        	Orders order = new Orders();
            	order.setUser(rs.getString(1));
            	order.setProduct(rs.getString(2));
            	order.setOrderedtime(rs.getTimestamp(3));
            	list.add(order);
            }   
            rs.close() ; 
            pstmt.close() ;  
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        }   
		return list;
	}


	@Override
	public User getUser(String username) {
		User user = new User();
		String sql = "select * from mydb.user where username = ?";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;   
        try{   
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql); 
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            user.setUsername(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setSex(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setPhone(rs.getString(5));
            user.setAge(rs.getInt(6));
            rs.close();
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        }
		return user; 
	}
}	