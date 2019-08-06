package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="user")
public class UserEntity {
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	private String id;
	@Column(length=32,unique=true,nullable=false)
	private String account;
	@Column(length=32,nullable=false)
	private String password;
	@Column(length=32)
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	@Column(length=1)
	private String role="0";
	@OneToOne
	@JoinColumn(name="userInfo_id")
	private UserInfoEntity userInfo;
	@Column(length=11)
	private String tel;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public UserInfoEntity getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoEntity userInfo) {
		this.userInfo = userInfo;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", account=" + account + ", password="
				+ password + ", name=" + name + ", createTime=" + createTime
				+ ", role=" + role + ", userInfo=" + userInfo + ", tel=" + tel
				+ "]";
	}
}
