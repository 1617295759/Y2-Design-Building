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
		int flag;
        String sql = "select * from "+ user_schema +" where name=?";
        try{
            User dbuser = template.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class),
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
    public void addUser(User user) {
        String sql = "INSERT INTO "+ user_schema +
                " (`name`, `gender`, `telNo`, `password`, `address`, `email`, `deleted`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql,user.getName(),user.getGender(),user.getTelNo(),
                user.getPassword(),user.getAddress(),user.getEmail(),0);
    }

    @Override
	public void updateInfo(String username, User user) {
		String sql = "UPDATE "+ user_schema +" SET `gender` = ?, " +
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
}