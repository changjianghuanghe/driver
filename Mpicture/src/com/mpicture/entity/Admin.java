package com.mpicture.entity;

import javax.persistence.Entity;
/**
 * 管理员类
 */
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * entity - admin 管理员 
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name="admin")
public class Admin {
	@Id
	private Integer aid;
	private String username;
	private String password;
	
	
	public String toString() {
		return "Admin [aid=" + aid + ", username=" + username + ", password=" + password + "]";
	}
	public Admin() {
		super();
	}
	public Admin(Integer aid, String username, String password) {
		super();
		this.aid = aid;
		this.username = username;
		this.password = password;
	}
	public Integer getaid() {
		return aid;
	}
	public void setaid(Integer aid) {
		this.aid = aid;
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
	
}
