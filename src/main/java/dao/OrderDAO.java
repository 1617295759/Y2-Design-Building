package dao;

import beans.Order;
import beans.Sorting;

import java.util.ArrayList;
import java.util.List;

public interface OrderDAO {
    //根据用户ID返回用户的全部订单信息
    public ArrayList<Order> getAllOrder(int userID);
    //增加一份订单,不仅要在Product中增一条，还要在sorting中加入所有条目
    public boolean addOrder(Order order, List<Sorting> sortings);
    //删除订单
    public boolean deleteOrder(int orderID);

    ArrayList<Order> sortOrderByTime(int userID);
}
