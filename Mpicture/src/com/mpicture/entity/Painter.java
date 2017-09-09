package com.mpicture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 画师类
 * @author Administrator
 *
 */

public class Painter {
	
	private Integer paid;
	private String name;
	
	/**
	 * 微信openid
	 */
	private String openid;
	
}
