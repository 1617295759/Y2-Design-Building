package dao;

import beans.Cart;

import java.util.ArrayList;

public interface CartDAO {
    //
    public void addCart(int userID, int commodityID, int amount);
    //
    public void deleteCart(int cartID);
    //
    public ArrayList<Cart> sortCartBytime(int userID);
}
