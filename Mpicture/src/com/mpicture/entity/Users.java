package com.mpicture.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 用户类
 * @author Administrator
 *
 */
@Entity
@Table(name="users")
public class Users {
	@Id
	private Integer uid;
	private String username;
	private String openid;
	private	String headportrait;
	private Double balance;
	private String email;
	private String phonenumber;
	private Integer sex;	//1代表男，2代表女
	private Date birth;
	
	
	@Override
	public String toString() {
		return "Users [uid=" + uid + ", username=" + username + ", openid=" + openid + ", headportrait=" + headportrait
				+ ", balance=" + balance + ", email=" + email + ", phonenumber=" + phonenumber + ", sex=" + sex
				+ ", birth=" + birth + "]";
	}
	public Users(Integer uid, String username, String openid, String headportrait, Double balance, String email,
			String phonenumber, Integer sex, Date birth) {
		super();
		this.uid = uid;
		this.username = username;
		this.openid = openid;
		this.headportrait = headportrait;
		this.balance = balance;
		this.email = email;
		this.phonenumber = phonenumber;
		this.sex = sex;
		this.birth = birth;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getHeadportrait() {
		return headportrait;
	}
	public void setHeadportrait(String headportrait) {
		this.headportrait = headportrait;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Users() {
		super();
	}
	
	
	
	
	
}
