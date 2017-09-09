package com.mpicture.entity;

import java.util.Date;

/**
 * entity - 资金
 * 
 * @author Administrator
 *
 */

public class Capital {
	private Integer cid;
	private Date time;
	/**
	 * 用户id
	 */
	private Users users_id;
	private Double quota;
	private String sketch;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Users getUsers_id() {
		return users_id;
	}
	public void setUsers_id(Users users_id) {
		this.users_id = users_id;
	}
	public Double getQuota() {
		return quota;
	}
	public void setQuota(Double quota) {
		this.quota = quota;
	}
	public String getSketch() {
		return sketch;
	}
	public void setSketch(String sketch) {
		this.sketch = sketch;
	}
	public Capital() {
		super();
	}
	public Capital(Integer cid, Date time, Users users_id, Double quota, String sketch) {
		super();
		this.cid = cid;
		this.time = time;
		this.users_id = users_id;
		this.quota = quota;
		this.sketch = sketch;
	}
	@Override
	public String toString() {
		return "Capital [cid=" + cid + ", time=" + time + ", users_id=" + users_id + ", quota=" + quota + ", sketch="
				+ sketch + "]";
	}
	
	
}
