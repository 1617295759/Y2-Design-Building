package dao;

import beans.Product;

import java.util.List;

public interface ProductDAO {
	//
	public List<Product> searchKey(String keyword);
	//
	public List<Product> searchKind(String kind);
	//
	public List<Product> sortByAddedtimeAsc();
	//
	public List<Product> sortByAddedtimeDesc();
	
	public List<Product> sortByPriceDesc();
	
	public List<Product> sortByPriceAsc();

}
