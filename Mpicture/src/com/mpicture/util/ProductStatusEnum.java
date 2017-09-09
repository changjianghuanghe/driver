package com.mpicture.util;

public enum ProductStatusEnum {
	Headportrait(1,"半身头像"),
	Groupphoto(2,"情侣合照");
	private int status;
	private String describe;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	private ProductStatusEnum(int status, String describe) {
		this.status = status;
		this.describe = describe;
	}
	
}
