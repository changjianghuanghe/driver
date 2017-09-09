package com.mpicture.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 产品类
 * @author Administrator
 *
 */
@Entity
@Table(name="product")
public class Product {
	@Id
	private Integer pid;
	private String name;
	/**
	 * 产品封面
	 */
	private String cover;
	private Double price;
	private Integer type;
	/*商品下单显示的大头像*/
	private String bighead;
	
	
	public String getBighead() {
		return bighead;
	}
	public void setBighead(String bighead) {
		this.bighead = bighead;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Product() {
		super();
	}
	public Product(Integer pid, String name, String cover, Double price, Integer type, String bighead) {
		super();
		this.pid = pid;
		this.name = name;
		this.cover = cover;
		this.price = price;
		this.type = type;
		this.bighead = bighead;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", name=" + name + ", cover=" + cover + ", price=" + price + ", type=" + type
				+ ", bighead=" + bighead + "]";
	}
	
	
	
	
}
