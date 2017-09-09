package com.mpicture.entity;

import java.util.Date;
/**
 * 订单类
 * 
 * @author Administrator
 *
 */
public class Indent {
	private Integer tid;
	private Date systime;
	private String userphoto;
	private String require;
	private String e_mail;
	/**
	 * 用户id
	 */
	private Users users_id;
	
	/**
	 * 产品id
	 */
	private Product product_id;
	private Integer status;
	private String picture;
	private String statusintr;
	private Integer updatetotal;
	
	public Integer getUpdatetotal() {
		return updatetotal;
	}

	public void setUpdatetotal(Integer updatetotal) {
		this.updatetotal = updatetotal;
	}

	public String getStatusintr() {
		return statusintr;
	}

	public void setStatusintr(String statusintr) {
		this.statusintr = statusintr;
	}

	public Indent() {
		super();
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Date getSystime() {
		return systime;
	}

	public void setSystime(Date systime) {
		this.systime = systime;
	}

	public String getUserphoto() {
		return userphoto;
	}

	public void setUserphoto(String userphoto) {
		this.userphoto = userphoto;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public Users getUsers_id() {
		return users_id;
	}

	public void setUsers_id(Users users_id) {
		this.users_id = users_id;
	}

	public Product getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Product product_id) {
		this.product_id = product_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Indent(Integer tid, Date systime, String userphoto, String require, String e_mail, Users users_id,
			Product product_id, Integer status, String picture, String statusintr, Integer updatetotal) {
		super();
		this.tid = tid;
		this.systime = systime;
		this.userphoto = userphoto;
		this.require = require;
		this.e_mail = e_mail;
		this.users_id = users_id;
		this.product_id = product_id;
		this.status = status;
		this.picture = picture;
		this.statusintr = statusintr;
		this.updatetotal = updatetotal;
	}

	@Override
	public String toString() {
		return "Indent [tid=" + tid + ", systime=" + systime + ", userphoto=" + userphoto + ", require=" + require
				+ ", e_mail=" + e_mail + ", users_id=" + users_id + ", product_id=" + product_id + ", status=" + status
				+ ", picture=" + picture + ", statusintr=" + statusintr + ", updatetotal=" + updatetotal + "]";
	}

	
	
	

	

	
}
