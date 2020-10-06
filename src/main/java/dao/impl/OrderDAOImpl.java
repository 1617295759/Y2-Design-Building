package dao.impl;

import beans.Order;
import beans.Sorting;
import dao.OrderDAO;
import database.DBUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
    public void addOrder(Order order, ArrayList<Sorting> sortings) {
        //插入订单信息
        String sql = "insert into iotbackstage2.order " +
                "(ordertime,price,amount,receiveAddress,deleted,userID) " +
                "values (?,?,?,?,?,?)";
        template.update(sql,order.getOrderTime(),order.getPrice(),sortings.size(),
                order.getReceiveAddress(),0,order.getUserId());
        //插入订单每个条目信息
        String sql1 = "INSERT INTO `iotbackstage2`.`sorting` " +
                "(`amount`, `state`, `price`, `deleted`, `orderID`, `commodityID`) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        for(Sorting sort : sortings){
            template.update(sql1,sort.getAmount(),sort.getState(),sort.getPrice()
                ,0,sort.getOrderId(),sort.getCommodityId());
        }
    }

    @Override
    public void deleteOrder(Order deletedorder) {
        String sql = "UPDATE `iotbackstage2`.`order` SET `deleted` = '0' WHERE (`orderID` = ?)";
        template.update(sql,deletedorder.getOrderId());
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
