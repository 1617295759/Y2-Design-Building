package dao.impl;

import beans.User;
import dao.UserDAO;
import database.DBConnect;
import database.DBUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import vo.Cart;
import vo.Orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    JdbcTemplate template;
    public UserDAOImpl() {
        template = DBUtils.getTemplate();
    }

    //judge whether user's name and password matches 1-success,0-error
    @Override
	public int queryByUsername(User user) {
		int flag = 0;
        String sql = "select * from iotbackstage2.user where name=?";
        try{
            User dbuser = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    user.getName());
            if(dbuser.getPassword().equals(user.getPassword())){
                flag = 1;
            }else{
                flag = 0;
            }
        }catch(EmptyResultDataAccessException e){
            flag = 0;
        }

		return flag;
	}

    @Override
    public User getUser(String username) {
        String sql = "select * from iotbackstage2.user where name=?";
        User dbuser = template.queryForObject(sql,
                new BeanPropertyRowMapper<beans.User>(beans.User.class),
                username);
        return dbuser;
    }
	
	@Override
	public void updateInfo(String username, User user) {
		String sql = "UPDATE `iotbackstage2`.`user` SET `gender` = ?, " +
                "`telNo` = ?, `password` = ?, `address` = ?, `email` = ?, `deleted` = ? " +
                "WHERE (`name` = ?)";

		User dbuser = new UserDAOImpl().getUser(username);

        String gender,telNo,password,address,email;
        boolean deleted;
        if((user.getGender()!=null)&&(!user.getGender().equals(""))){
            gender = user.getGender();
        }else{
            gender = dbuser.getGender();
        }
        if((user.getTelNo()!=null)&&(!user.getTelNo().equals(""))){
            telNo = user.getTelNo();
        }else{
            telNo = dbuser.getTelNo();
        }
        if ((user.getPassword()!=null)&&(!user.getPassword().equals(""))){
            password = user.getPassword();
        }else{
            password = dbuser.getPassword();
        }
        if ((user.getAddress()!=null)&&(!user.getAddress().equals("")) ){
            address = user.getAddress();
        }else{
            address = dbuser.getAddress();
        }
        if((user.getEmail()!=null)&&(!user.getEmail().equals(""))){
            email = user.getEmail();
        }else{
            email = dbuser.getEmail();
        }

        template.update(sql,gender,telNo,password,address,email,user.getDeleted(),username);
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



}	