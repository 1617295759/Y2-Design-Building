package vo;

import java.sql.Timestamp;

public class Cart {
	private String user;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	private String product;
	private Timestamp time;
	
	public Cart() {
	}
	public Cart(String user,String product) {
		this.user = user;
		this.product = product;
		this.time = new Timestamp(System.currentTimeMillis());
	}
}
