package beans;


public class Commodity {

  private long commodityId;
  private String name;
  private double price;
  private String superCategory;
  private String subCategory;
  private String appearance;
  private String functionality;
  private String accessory;
  private long amount;
  private String instruction;
  private long merchantId;
  private java.sql.Timestamp addedTime;
  private String imgsrc;
  private long deleted;


  public long getCommodityId() {
    return commodityId;
  }

  public void setCommodityId(long commodityId) {
    this.commodityId = commodityId;
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


  public String getSuperCategory() {
    return superCategory;
  }

  public void setSuperCategory(String superCategory) {
    this.superCategory = superCategory;
  }


  public String getSubCategory() {
    return subCategory;
  }

  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
  }


  public String getAppearance() {
    return appearance;
  }

  public void setAppearance(String appearance) {
    this.appearance = appearance;
  }


  public String getFunctionality() {
    return functionality;
  }

  public void setFunctionality(String functionality) {
    this.functionality = functionality;
  }


  public String getAccessory() {
    return accessory;
  }

  public void setAccessory(String accessory) {
    this.accessory = accessory;
  }


  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }


  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }


  public long getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(long merchantId) {
    this.merchantId = merchantId;
  }


  public java.sql.Timestamp getAddedTime() {
    return addedTime;
  }

  public void setAddedTime(java.sql.Timestamp addedTime) {
    this.addedTime = addedTime;
  }


  public String getImgsrc() {
    return imgsrc;
  }

  public void setImgsrc(String imgsrc) {
    this.imgsrc = imgsrc;
  }


  public long getDeleted() {
    return deleted;
  }

  public void setDeleted(long deleted) {
    this.deleted = deleted;
  }

}
