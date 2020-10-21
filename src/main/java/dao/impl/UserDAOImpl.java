package dao.impl;

import beans.User;
import dao.UserDAO;
import database.DBUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDAOImpl implements UserDAO {
    JdbcTemplate template;
    String user_schema = "iotbackstage2.user";
    public UserDAOImpl() {
        template = DBUtils.getTemplate();
    }

    //judge whether user's name and password matches 1-success,0-error
    @Override
	public int queryByUsername(User user) {
		//0-无账号，1-匹配，2-不匹配
        int flag;
        String sql = "select * from "+ user_schema +" where name=?";
        try{
            User dbuser = template.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class),
                    user.getName());
            if(dbuser.getPassword().equals(user.getPassword())){
                flag = 1;
            }else{
                flag = 2;
            }
        }catch(EmptyResultDataAccessException e){
            flag = 0;
        }
		return flag;
	}

    @Override
    public User getUser(String username) {
        String sql = "select * from "+user_schema+" where name=?";
        User user = null;
        try{
            user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<beans.User>(beans.User.class), username);
        }catch(Exception e){
            return null;
        }
        return user;
    }

    @Override
    public User getUser(int userID) {
        String sql = "select * from "+user_schema+" where userID=?";
        User user = null;
        try{
            user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<beans.User>(beans.User.class), userID);
        }catch(Exception e){
            return null;
        }
        return user;
    }

    @Override
    public int addUser(User user) {
        User tem = getUser(user.getName());
        if(tem!=null){
            return 0;
        }
        String sql = "INSERT INTO "+ user_schema +
                " (`name`, `gender`, `telNo`, `password`, `address`, `email`, `deleted`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        int i = template.update(sql,user.getName(),user.getGender(),user.getTelNo(),
                user.getPassword(),user.getAddress(),user.getEmail(),0);
        //返回新增数据的ID
        String sql1 = "select userID from " + user_schema + " where name=?";
        int userID = template.queryForObject(sql1, Integer.class,user.getName());
        return userID;
    }

    @Override
	public boolean updateInfo(int userid, User user) {
		String sql = "UPDATE "+ user_schema +" SET `name` = ?, `gender` = ?, " +
                "`telNo` = ?, `password` = ?, `address` = ?, `email` = ? WHERE (`userID` = ?)";

		User dbuser = new UserDAOImpl().getUser(userid);

        String name,gender,telNo,password,address,email;
        boolean deleted;
        if((user.getName()!=null)&&(!user.getName().equals(""))){
            name = user.getName();
        }else{
            name = dbuser.getName();
        }
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
        int i = template.update(sql,name,gender,telNo,password,address,email,userid);
        return (i>0);
	}
    public boolean updateInfo(int userid,String newinfo,String info){
        int i = 0;
        String sql = "";
        switch(newinfo){
            case "name":
                sql = "UPDATE "+ user_schema +" SET `name` = ? WHERE (`userID` = ?)";
                break;
            case "gender":
                sql = "UPDATE "+ user_schema +" SET `gender` = ? WHERE (`userID` = ?)";
                break;
            case "telNo":
                sql = "UPDATE "+ user_schema +" SET `telNo` = ? WHERE (`userID` = ?)";
                break;
            case "address":
                sql = "UPDATE "+ user_schema +" SET `address` = ? WHERE (`userID` = ?)";
                break;
            case "email":
                sql = "UPDATE "+ user_schema +" SET `email` = ? WHERE (`userID` = ?)";
                break;
        }
        try{
            i = template.update(sql,info,userid);
        }catch(Exception e){
            i=0;
        }
        return (i>0);
    }
}