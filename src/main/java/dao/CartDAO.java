package dao;

import beans.Cart;

import java.util.ArrayList;

public interface CartDAO {
    //
    public boolean addCart(int userID, int commodityID, int amount,String cus);
    //
    public boolean deleteCart(int cartID);
    //
    public ArrayList<Cart> sortCartByTime(int userID);
}
