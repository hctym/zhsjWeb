package com.zhsj.model;

import java.io.Serializable;

/**
 * 
 * @author xulinchuang
 * 商家和区域的绑定
 *
 */
public class BusinessInfoRegion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3565588738477787924L;
	
	private int id;
	private int businessInfoId;//商家id
	private int regionId;//区域id
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
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	
	

}
