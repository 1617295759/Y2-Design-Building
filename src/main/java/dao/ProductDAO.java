package dao;

import vo.Product;

import java.util.ArrayList;

public interface ProductDAO {
	//
	public ArrayList<Product> searchKey(String keyword);
	//
	public ArrayList<Product> searchKind(String kind);
	//
	public ArrayList<Product> sortByAddedtimeAsc();
	//
	public ArrayList<Product> sortByAddedtimeDesc();
	
	public ArrayList<Product> sortByPriceDesc();
	
	public ArrayList<Product> sortByPriceAsc();

}
