package beans;

public class Administrator {

  private int administratorId;
  private String account;
  private String password;
  private boolean deleted;


  public long getAdministratorId() {
    return administratorId;
  }

  public void setAdministratorId(int administratorId) {
    this.administratorId =  administratorId;
  }


  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

}
