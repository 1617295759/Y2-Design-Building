package beans;


import java.sql.Timestamp;

public class Product {

  private int commodityId;
  private String name;
  private double price;
  private String superCategory;
  private String subCategory;
  private String appearance;
  private String functionality;
  private String accessory;
  private int amount;
  private String instruction;
  private int merchantId;
  private Timestamp addedTime;
  private String imgsrc;
  //0-exist,1-deleted
  private boolean deleted;

  public int getCommodityId() { return commodityId; }

  public String getName() { return name; }

  public double getPrice() { return price; }

  public String getSuperCategory() { return superCategory; }

  public String getSubCategory() { return subCategory; }

  public String getAppearance() { return appearance; }

  public String getFunctionality() { return functionality; }

  public String getAccessory() { return accessory; }

  public int getAmount() { return amount; }

  public String getInstruction() { return instruction; }

  public int getMerchantId() { return merchantId; }

  public Timestamp getAddedTime() { return addedTime; }

  public String getImgsrc() { return imgsrc; }

  public boolean isDeleted() { return deleted; }

  public void setCommodityId(int commodityId) { this.commodityId = commodityId; }

  public void setName(String name) { this.name = name; }

  public void setPrice(double price) { this.price = price; }

  public void setSuperCategory(String superCategory) { this.superCategory = superCategory; }

  public void setSubCategory(String subCategory) { this.subCategory = subCategory; }

  public void setAppearance(String appearance) { this.appearance = appearance; }

  public void setFunctionality(String functionality) { this.functionality = functionality; }

  public void setAccessory(String accessory) { this.accessory = accessory; }

  public void setAmount(int amount) { this.amount = amount; }

  public void setInstruction(String instruction) { this.instruction = instruction; }

  public void setMerchantId(int merchantId) { this.merchantId = merchantId; }

  public void setAddedTime(Timestamp addedTime) { this.addedTime = addedTime; }

  public void setImgsrc(String imgsrc) { this.imgsrc = imgsrc; }

  public void setDeleted(boolean deleted) { this.deleted = deleted; }

  @Override
  public String toString() {
    return "Product{" +
            "commodityId=" + commodityId +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", superCategory='" + superCategory + '\'' +
            ", subCategory='" + subCategory + '\'' +
            ", appearance='" + appearance + '\'' +
            ", functionality='" + functionality + '\'' +
            ", accessory='" + accessory + '\'' +
            ", amount=" + amount +
            ", instruction='" + instruction + '\'' +
            ", merchantId=" + merchantId +
            ", addedTime=" + addedTime +
            ", imgsrc='" + imgsrc + '\'' +
            ", deleted=" + deleted +
            '}';
  }
}
