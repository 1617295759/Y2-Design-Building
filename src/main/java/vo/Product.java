package vo;

import java.sql.Timestamp;

public class Product {
	private String name;
	private double price;
	private String info;
	private Timestamp addedTime;
	private String kind;
	private String color;
	private String imgsrc;
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Timestamp getAddedTime() {
		return addedTime;
	}
	public void setAddedTime(Timestamp addedTime) {
		this.addedTime = addedTime;
	}
	
}
