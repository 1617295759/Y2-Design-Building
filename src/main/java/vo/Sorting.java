package vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description  
 * @Author  YuzheWang
 * @Date 2020-08-06 
 */

@Entity
@Table ( name ="sorting" , schema = "")
public class Sorting  implements Serializable {

	private static final long serialVersionUID =  3086268424818583971L;

   	@Column(name = "sortID" )
	private Long sortId;

   	@Column(name = "amount" )
	private Long amount;

   	@Column(name = "state" )
	private String state;

   	@Column(name = "price" )
	private Double price;

   	@Column(name = "deleted" )
	private Integer deleted;

   	@Column(name = "orderID" )
	private Long orderId;

   	@Column(name = "commodityID" )
	private Long commodityId;

	public Long getSortId() {
		return this.sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	public Long getAmount() {
		return this.amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	@Override
	public String toString() {
		return "TpApiConfig{" +
				"sortId='" + sortId + '\'' +
				"amount='" + amount + '\'' +
				"state='" + state + '\'' +
				"price='" + price + '\'' +
				"deleted='" + deleted + '\'' +
				"orderId='" + orderId + '\'' +
				"commodityId='" + commodityId + '\'' +
				'}';
	}

}
