package beans;

public class User {

  private String name;
  private int userId;
  private String gender;
  private String telNo;
  private String password;
  private String address;
  private String email;
  private boolean deleted;

  public User() {
    this.deleted = false;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }


  public String getTelNo() {
    return telNo;
  }

  public void setTelNo(String telNo) {
    this.telNo = telNo;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  @Override
  public String toString() {
    return "User{" +
            "name='" + name + '\'' +
            ", userId=" + userId +
            ", gender='" + gender + '\'' +
            ", telNo='" + telNo + '\'' +
            ", password='" + password + '\'' +
            ", address='" + address + '\'' +
            ", email='" + email + '\'' +
            ", deleted=" + deleted +
            '}';
  }
}
