package dao;

import beans.Sorting;

import java.util.ArrayList;

public interface SortingDAO {
    //获取该OrderID的所有条目
    public ArrayList<Sorting> getSorting(int orderID);
}
