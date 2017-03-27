package com.zhsj.model;

import java.io.Serializable;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：商户和账户的关联
 * 类名称：com.zhsj.model.StoreBindAccount     
 * 创建人：xulinchuang
 * 创建时间：2016年12月28日 上午11:49:06
 */
public class StoreBindAccount implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 2040821260792565692L;
	
	private long id;
	private String storeNo;//商户编号
	private long storeAccountId;//商户账户id
	private int status;//状态 0、无效1、有效
	private long utime;
	private long ctime;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public long getStoreAccountId() {
		return storeAccountId;
	}
	public void setStoreAccountId(long storeAccountId) {
		this.storeAccountId = storeAccountId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getUtime() {
		return utime;
	}
	public void setUtime(long utime) {
		this.utime = utime;
	}
	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}
	
	

}
