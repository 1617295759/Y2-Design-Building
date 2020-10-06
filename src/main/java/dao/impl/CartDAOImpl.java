package dao.impl;

import beans.Cart;
import dao.CartDAO;
import database.DBUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {
    JdbcTemplate template;
    String cart_schema = "iotbackstage2.cart";
    public CartDAOImpl() {
        template = DBUtils.getTemplate();
    }

    @Override
    public void addCart(int userID, int commodityID, int amount) {
        String sql0 = "SELECT price FROM iotbackstage2.commodity " +
                "WHERE commodityID = ?";
        double price = template.queryForObject(sql0,Double.class,commodityID);

        String sql = "insert into iotbackstage2.cart " +
                "(userID, commodityID, time, amount,price,deleted) " +
                "values (?,?,?,?,?,?)";
        template.update(sql,userID, commodityID,
                new Timestamp(System.currentTimeMillis()),amount,price,0);
    }

    @Override
    public void deleteCart(int cartID) {
        String sql = "UPDATE `iotbackstage2`.`cart` " +
                "SET `deleted` = '1' WHERE (`cartID` = ?)";
        template.update(sql,cartID);
    }

    @Override
    public ArrayList<Cart> sortCartBytime(int userID) {
        List<Cart> list = new ArrayList<Cart>();
        String sql = "select * from "+ cart_schema + " where `deleted`=0 and userID=?  order by time";
        list = template.query(sql,new BeanPropertyRowMapper<Cart>(Cart.class),userID);
        return (ArrayList<Cart>) list;
    }
}
