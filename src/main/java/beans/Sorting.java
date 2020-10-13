package beans;

public class Sorting {

  private int sortId;
  private int amount;
  private String state;
  private double price;
  private boolean deleted;
  private int orderId;
  private int commodityId;
  public Sorting(){}
  public Sorting(int amount, double price, int commodityId){
    this.amount = amount;
    this.state = "0";
    this.price = price;
    this.deleted = false;
    this.commodityId = commodityId;
  }

  private Product product;

  public Product getProduct() { return product; }

  public void setProduct(Product product) { this.product = product;}

  public int getSortId() {
    return sortId;
  }

  public void setSortId(int sortId) {
    this.sortId = sortId;
  }


  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }


  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }


  public int getCommodityId() {
    return commodityId;
  }

  public void setCommodityId(int commodityId) {
    this.commodityId = commodityId;
  }

  @Override
  public String toString() {
    return "Sorting{" +
            "sortId=" + sortId +
            ", amount=" + amount +
            ", state='" + state + '\'' +
            ", price=" + price +
            ", deleted=" + deleted +
            ", orderId=" + orderId +
            ", commodityId=" + commodityId +
            '}';
  }
}
