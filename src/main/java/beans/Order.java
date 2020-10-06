package beans;


import java.sql.Timestamp;

public class Order {

  private int orderId;
  private Timestamp orderTime;
  private double price;
  private int amount;
  private String receiveAddress;
  private boolean deleted;
  private int userId;

  public Order(){
    orderTime =  new Timestamp(System.currentTimeMillis());
  }
  public Order(double price,String receiveAddress,int userID){
    this.price = price;
    this.receiveAddress = receiveAddress;
    this.userId = userID;
  }

  public int getOrderId() { return orderId; }

  public void setOrderId(int orderId) { this.orderId = orderId; }

  public Timestamp getOrderTime() { return orderTime; }

  public void setOrderTime(Timestamp orderTime) { this.orderTime = orderTime; }

  public double getPrice() { return price; }

  public void setPrice(double price) { this.price = price; }

  public int getAmount() { return amount; }

  public void setAmount(int amount) { this.amount = amount; }

  public String getReceiveAddress() { return receiveAddress; }

  public void setReceiveAddress(String receiveAddress) { this.receiveAddress = receiveAddress; }

  public boolean isDeleted() { return deleted; }

  public void setDeleted(boolean deleted) { this.deleted = deleted; }

  public int getUserId() { return userId; }

  public void setUserId(int userId) { this.userId = userId; }

  @Override
  public String toString() {
    return "Order{" +
            "orderId=" + orderId +
            ", orderTime=" + orderTime +
            ", price=" + price +
            ", amount=" + amount +
            ", receiveAddress='" + receiveAddress + '\'' +
            ", deleted=" + deleted +
            ", userId=" + userId +
            '}';
  }
}
