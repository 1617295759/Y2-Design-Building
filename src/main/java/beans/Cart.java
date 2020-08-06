package beans;

import java.sql.Timestamp;

public class Cart {

  private int cartId;
  private int userId;
  private int commodityId;
  private Timestamp time;
  private int amount;
  private double price;
  //该条购物车信息是否被删除
  private boolean deleted;

  public int getCartId() { return cartId; }

  public void setCartId(int cartId) { this.cartId = cartId; }

  public int getUserId() { return userId; }

  public void setUserId(int userId) { this.userId = userId; }

  public int getCommodityId() { return commodityId; }

  public void setCommodityId(int commodityId) { this.commodityId = commodityId; }

  public Timestamp getTime() { return time; }

  public void setTime(Timestamp time) { this.time = time; }

  public int getAmount() { return amount; }

  public void setAmount(int amount) { this.amount = amount; }

  public double getPrice() { return price; }

  public void setPrice(double price) { this.price = price; }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  @Override
  public String toString() {
    return "Cart{" +
            "cartId=" + cartId +
            ", userId=" + userId +
            ", commodityId=" + commodityId +
            ", time=" + time +
            ", amount=" + amount +
            ", price=" + price +
            ", deleted=" + deleted +
            '}';
  }
}
