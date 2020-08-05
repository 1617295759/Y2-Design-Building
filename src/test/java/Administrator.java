

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description  
 * @Author  YuzheWang
 * @Date 2020-08-05 
 */

@Entity
@Table ( name ="administrator" , schema = "")
public class Administrator  implements Serializable {

	private static final long serialVersionUID =  534276982919305391L;

   	@Column(name = "administratorID" )
	private Long administratorId;

   	@Column(name = "account" )
	private String account;

   	@Column(name = "password" )
	private String password;

   	@Column(name = "deleted" )
	private Integer deleted;

	public Long getAdministratorId() {
		return this.administratorId;
	}

	public void setAdministratorId(Long administratorId) {
		this.administratorId = administratorId;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "TpApiConfig{" +
				"administratorId='" + administratorId + '\'' +
				"account='" + account + '\'' +
				"password='" + password + '\'' +
				"deleted='" + deleted + '\'' +
				'}';
	}

}
