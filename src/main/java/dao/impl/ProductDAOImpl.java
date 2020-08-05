package dao.impl;

import dao.ProductDAO;
import database.DBConnect;
import vo.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {

	public ArrayList<Product> searchKey(String keyword) {
		ArrayList<Product> list = new ArrayList<Product>();
		
		String sql1 = "select * from mydb.product where name like ?";
		String sql2 = "select * from mydb.product where kind like ?";
		String sql3 = "select * from mydb.product where color like ?";
		String sql4 = "select * from mydb.product where info like ?";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;
        try{   
        	dbc = new DBConnect() ;
        	//search in the String value columns
            pstmt = dbc.getConnection().prepareStatement(sql1); 
            pstmt.setString(1, "%"+keyword+"%");
            ResultSet rs1 = pstmt.executeQuery();
			while (rs1.next()) { 
	        	Product product = new Product();
	        	product.setName(rs1.getString(1));
	        	product.setKind(rs1.getString(2));
	        	product.setAddedTime(rs1.getTimestamp(3));
	        	product.setColor(rs1.getString(4));
	        	product.setPrice(rs1.getDouble(5));
	        	product.setInfo(rs1.getString(6));
	        	product.setImgsrc(rs1.getString(7));
            	list.add(product);
            }   
            rs1.close() ; 
            pstmt.close() ; 
            
            pstmt = dbc.getConnection().prepareStatement(sql2); 
            pstmt.setString(1, "%"+keyword+"%");
            ResultSet rs2 = pstmt.executeQuery();
			while (rs2.next()) { 
	        	Product product = new Product();
	        	product.setName(rs2.getString(1));
	        	product.setKind(rs2.getString(2));
	        	product.setAddedTime(rs2.getTimestamp(3));
	        	product.setColor(rs2.getString(4));
	        	product.setPrice(rs2.getDouble(5));
	        	product.setInfo(rs2.getString(6));
	        	product.setImgsrc(rs2.getString(7));
            	list.add(product);
            }   
            rs2.close() ; 
            pstmt.close() ;

            pstmt = dbc.getConnection().prepareStatement(sql3); 
            pstmt.setString(1, "%"+keyword+"%");
            ResultSet rs3 = pstmt.executeQuery();
			while (rs3.next()) { 
	        	Product product = new Product();
	        	product.setName(rs3.getString(1));
	        	product.setKind(rs3.getString(2));
	        	product.setAddedTime(rs3.getTimestamp(3));
	        	product.setColor(rs3.getString(4));
	        	product.setPrice(rs3.getDouble(5));
	        	product.setInfo(rs3.getString(6));
	        	product.setImgsrc(rs3.getString(7));
            	list.add(product);
            }   
            rs3.close() ; 
            pstmt.close() ;
            
            pstmt = dbc.getConnection().prepareStatement(sql4); 
            pstmt.setString(1, "%"+keyword+"%");
            ResultSet rs4 = pstmt.executeQuery();
			while (rs4.next()) { 
	        	Product product = new Product();
	        	product.setName(rs4.getString(1));
	        	product.setKind(rs4.getString(2));
	        	product.setAddedTime(rs4.getTimestamp(3));
	        	product.setColor(rs4.getString(4));
	        	product.setPrice(rs4.getDouble(5));
	        	product.setInfo(rs4.getString(6));
	        	product.setImgsrc(rs4.getString(7));
            	list.add(product);
            }   
            rs4.close() ; 
            pstmt.close() ;
            
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        }   
		return list;
	}

	public ArrayList<Product> searchKind(String kind) {
		ArrayList<Product> list = new ArrayList<Product>();
		String sql = "select * from mydb.product where kind=?";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;
        try{   
        	dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql); 
            pstmt.setString(1, kind);
            ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { 
	        	Product product = new Product();
	        	product.setName(rs.getString(1));
	        	product.setKind(rs.getString(2));
	        	product.setAddedTime(rs.getTimestamp(3));
	        	product.setColor(rs.getString(4));
	        	product.setPrice(rs.getDouble(5));
	        	product.setInfo(rs.getString(6));
	        	product.setImgsrc(rs.getString(7));
            	list.add(product);
            }   
            rs.close() ; 
            pstmt.close() ;  
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        }   
		return list;
	}

	public ArrayList<Product> sortByAddedtimeAsc() {
		ArrayList<Product> list = new ArrayList<Product>();
		String sql = "select * from mydb.product order by addedtime";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;
        try{   
        	dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql); 
            ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { 
				
	        	Product product = new Product();
	        	product.setName(rs.getString(1));
	        	product.setKind(rs.getString(2));
	        	product.setAddedTime(rs.getTimestamp(3));
	        	product.setColor(rs.getString(4));
	        	product.setPrice(rs.getDouble(5));
	        	product.setInfo(rs.getString(6));
	        	product.setImgsrc(rs.getString(7));
            	list.add(product);
            }   
            rs.close() ; 
            pstmt.close() ;  
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        }   
		return list;
	}

	public ArrayList<Product> sortByAddedtimeDesc() {
		ArrayList<Product> list = new ArrayList<Product>();
		String sql = "select * from mydb.product order by addedtime DESC";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;
        try{   
        	dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql); 
            ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { 
	        	Product product = new Product();
	        	product.setName(rs.getString(1));
	        	product.setKind(rs.getString(2));
	        	product.setAddedTime(rs.getTimestamp(3));
	        	product.setColor(rs.getString(4));
	        	product.setPrice(rs.getDouble(5));
	        	product.setInfo(rs.getString(6));
	        	product.setImgsrc(rs.getString(7));
            	list.add(product);
            }   
            rs.close() ; 
            pstmt.close() ;  
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        }   
		return list;
	}

	@Override
	public ArrayList<Product> sortByPriceDesc() {
		ArrayList<Product> list = new ArrayList<Product>();
		String sql = "select * from mydb.product order by price DESC";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;
        try{   
        	dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql); 
            ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { 
	        	Product product = new Product();
	        	product.setName(rs.getString(1));
	        	product.setKind(rs.getString(2));
	        	product.setAddedTime(rs.getTimestamp(3));
	        	product.setColor(rs.getString(4));
	        	product.setPrice(rs.getDouble(5));
	        	product.setInfo(rs.getString(6));
	        	product.setImgsrc(rs.getString(7));
            	list.add(product);
            }   
            rs.close() ; 
            pstmt.close() ;  
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        }   
		return list;
	}

	@Override
	public ArrayList<Product> sortByPriceAsc() {
		ArrayList<Product> list = new ArrayList<Product>();
		String sql = "select * from mydb.product order by price";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;
        try{   
        	dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql); 
            ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { 
	        	Product product = new Product();
	        	product.setName(rs.getString(1));
	        	product.setKind(rs.getString(2));
	        	product.setAddedTime(rs.getTimestamp(3));
	        	product.setColor(rs.getString(4));
	        	product.setPrice(rs.getDouble(5));
	        	product.setInfo(rs.getString(6));
	        	product.setImgsrc(rs.getString(7));
            	list.add(product);
            }   
            rs.close() ; 
            pstmt.close() ;  
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        }   
		return list;
	}


}
