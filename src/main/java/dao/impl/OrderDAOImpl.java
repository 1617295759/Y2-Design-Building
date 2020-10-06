package dao.impl;

import beans.Order;
import beans.Sorting;
import dao.OrderDAO;
import database.DBUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    JdbcTemplate template;
    String order_schema = "iotbackstage2.order";
    public OrderDAOImpl() {
        template = DBUtils.getTemplate();
    }
    @Override
    public ArrayList<Order> getAllOrder(int userID) {
        List<Order> list = new ArrayList<Order>();
        String sql = "select * from "+ order_schema +
                " where deleted = 0 where userID=?";
        list = template.query(sql,new BeanPropertyRowMapper<Order>(Order.class),userID);
        return (ArrayList<Order>) list;
    }

    @Override
    public boolean addOrder(Order order, List<Sorting> sortings) {
        //插入订单信息
        String sql = "insert into iotbackstage2.order " +
                "(ordertime,price,amount,receiveAddress,deleted,userID) " +
                "values (?,?,?,?,?,?)";
        int i = template.update(sql,new Timestamp(System.currentTimeMillis()),order.getPrice(),
                sortings.size(),order.getReceiveAddress(),0,order.getUserId());
        //插入订单每个条目信息
        if(i>0) {
            String sql1 = "INSERT INTO `iotbackstage2`.`sorting` " +
                    "(`amount`, `state`, `price`, `deleted`, `orderID`, `commodityID`) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            for (Sorting sort : sortings) {
                template.update(sql1, sort.getAmount(), sort.getState(), sort.getPrice()
                        , 0, sort.getOrderId(), sort.getCommodityId());
            }
        }
        return (i>0);
    }

    @Override
    public boolean deleteOrder(int OrderID) {
        String sql = "UPDATE `iotbackstage2`.`order` SET `deleted` = '0' WHERE (`orderID` = ?)";
        int i = template.update(sql,OrderID);
        return (i>0);
    }

    @Override
    public ArrayList<Order> sortOrderByTime(int userID) {
        List<Order> list = new ArrayList<Order>();
        String sql = "select * from `iotbackstage2`.`order` " +
                "where `deleted`=0 and userID=? order by ordertime";
        list = template.query(sql,new BeanPropertyRowMapper<Order>(Order.class),userID);
        return (ArrayList<Order>) list;
    }
}
