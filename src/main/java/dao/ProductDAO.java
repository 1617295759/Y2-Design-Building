package dao;

import beans.Product;

import java.util.List;

public interface ProductDAO {
	//获取某一产品
	public Product getProduct(int commodityID);
	//根据关键词检索
	public List<Product> searchKey(String keyword);
	//根据种类检索
	public List<Product> searchKind(String kind);
	//根据功能检索
	public List<Product> searchFunc(String func);
	//按时间升序降序
	public List<Product> sortByAddedtimeAsc();
	public List<Product> sortByAddedtimeDesc();
	//按价格升序降序
	public List<Product> sortByPriceDesc();
	public List<Product> sortByPriceAsc();
	//按照价格区间检索
	public List<Product> searchPriceDomain(int index);

}
