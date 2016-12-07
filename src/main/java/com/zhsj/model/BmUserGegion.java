package com.zhsj.model;

import java.io.Serializable;
/**
 * 
 * @author xulinchuang
 * 用户和区域的绑定
 *
 */
public class BmUserGegion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3173937437206482678L;
	
	private int id;
	private int bmUserId;//人员id
	private int regionId;//区域id
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
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	
	

}
