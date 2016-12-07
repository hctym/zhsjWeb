package com.zhsj.model;

import java.io.Serializable;
/**
 * 
 * @author xulinchuang
 * 商家支付设置
 *
 */
public class ShopsPayset implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 89173740070606811L;
	
	private int id;
	private int bmUserId;//商家id
	private String type;//支付类型(应该创建一个支付类。用于做关联？)
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBmUserId() {
		return bmUserId;
	}
	public void setBmUserId(int bmUserId) {
		this.bmUserId = bmUserId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
