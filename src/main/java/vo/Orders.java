package vo;

import java.sql.Timestamp;

public class Orders {
	private String user;
	private String product;
	private Timestamp orderedtime;
	private Timestamp payedtime;
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
	public Timestamp getOrderedtime() {
		return orderedtime;
	}
	public void setOrderedtime(Timestamp orderedtime) {
		this.orderedtime = orderedtime;
	}
	public Timestamp getPayedtime() {
		return payedtime;
	}
	public void setPayedtime(Timestamp payedtime) {
		this.payedtime = payedtime;
	}
	public Orders() {
	}
	public Orders(String user,String product) {
		this.user = user;
		this.product = product;
		this.orderedtime = new Timestamp(System.currentTimeMillis());
		this.payedtime = null;
	}

}
