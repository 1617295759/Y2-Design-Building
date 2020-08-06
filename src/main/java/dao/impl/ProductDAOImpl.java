package dao.impl;

import beans.Product;
import dao.ProductDAO;
import database.DBUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
	private String dbname = "iotbackstage2";
	private JdbcTemplate template;
	public ProductDAOImpl() {
		template  = DBUtils.getTemplate();
	}

	public List<Product> searchKey(String keyword) {
		List<Product> list = new ArrayList<Product>();
		String sql1 = "select * from "+ dbname+ ".commodity where name like ?"+"%"+keyword+"%";
		String sql2 = "select * from "+ dbname+ ".commodity where superCategory like ?"+"%"+keyword+"%";
		String sql3 = "select * from "+ dbname+ ".commodity where subCategory like ?"+"%"+keyword+"%";
		String sql4 = "select * from "+ dbname+ ".commodity where appearance like ?"+"%"+keyword+"%";
		String sql5 = "select * from "+ dbname+ ".commodity where instruction like ?"+"%"+keyword+"%";

		list = template.query(sql1,new BeanPropertyRowMapper<Product>(Product.class));
		list.addAll(template.query(sql2,new BeanPropertyRowMapper<Product>(Product.class)));
		list.addAll(template.query(sql3,new BeanPropertyRowMapper<Product>(Product.class)));
		list.addAll(template.query(sql4,new BeanPropertyRowMapper<Product>(Product.class)));
		list.addAll(template.query(sql5,new BeanPropertyRowMapper<Product>(Product.class)));
		list = new ArrayList<Product>(new LinkedHashSet<>(list));
		return list;
	}

	public List<Product> searchKind(String kind) {
		List<Product> list = new ArrayList<Product>();
		String sql = "select * from "+ dbname+ ".commodity where superCategory=?";
        list = template.query(sql,new BeanPropertyRowMapper<Product>(Product.class),kind);
		return list;
	}

	public List<Product> sortByAddedtimeAsc() {
		List<Product> list = new ArrayList<Product>();
		String sql = "select * from "+ dbname+ ".commodity order by addedTime";
		list = template.query(sql,new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}

	public List<Product> sortByAddedtimeDesc() {
		List<Product> list = new ArrayList<Product>();
		String sql = "select * from "+ dbname+ ".commodity order by addedTime DESC ";
		list = template.query(sql,new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}

	@Override
	public List<Product> sortByPriceDesc() {
		List<Product> list = new ArrayList<Product>();
		String sql = "select * from "+ dbname+ ".commodity order by price DESC";
		list = template.query(sql,new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}

	@Override
	public List<Product> sortByPriceAsc() {
		List<Product> list = new ArrayList<Product>();
		String sql = "select * from "+ dbname+ ".commodity order by price ";
		list = template.query(sql,new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}
}
