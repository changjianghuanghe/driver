package com.jimeng.entity;


import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by admin on 2017/9/14.
 */
@Entity
@Table(name="user")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private Integer uid;
    private String username;
    private String password;
    private String phonenum;
    private String address;
    private String email;
    private Integer sex;
    
    
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public User(Integer uid, String username, String password, String phonenum, String address, String email,
			Integer sex) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.phonenum = phonenum;
		this.address = address;
		this.email = email;
		this.sex = sex;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", phonenum=" + phonenum
				+ ", address=" + address + ", email=" + email + ", sex=" + sex + "]";
	}
    
    
}
