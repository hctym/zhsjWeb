package com.zhsj.model;

import java.io.Serializable;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：商户和组织的关联
 * 类名称：com.zhsj.model.StoreBindOrg     
 * 创建人：xulinchuang
 * 创建时间：2016年12月28日 下午1:13:15
 */
public class StoreBindOrg implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1211240601751101000L;
	
	private long id;
	private String storeNo;//商户编号
	private long orgId;//组织id
	private String orgIds;//父组织的id拼接（便于查询）。
	private int valid;//0、无效1、有效
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
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
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
