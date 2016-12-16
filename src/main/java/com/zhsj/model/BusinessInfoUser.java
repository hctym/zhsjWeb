package com.zhsj.model;

import java.io.Serializable;

public class BusinessInfoUser implements Serializable{

	/**
	 * @Fields serialVersionUID : 
	 */ 
	private static final long serialVersionUID = 1293479485762760426L;
	private int id;
	private int businessInfoId;//商家信息
	private int businessUserId;//店长或者员工
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBusinessInfoId() {
		return businessInfoId;
	}
	public void setBusinessInfoId(int businessInfoId) {
		this.businessInfoId = businessInfoId;
	}
	public int getBusinessUserId() {
		return businessUserId;
	}
	public void setBusinessUserId(int businessUserId) {
		this.businessUserId = businessUserId;
	}
	
	

}
