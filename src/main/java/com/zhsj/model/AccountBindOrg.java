package com.zhsj.model;

import java.io.Serializable;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：账户和机构
 * 类名称：com.zhsj.model.AccountBindOrg     
 * 创建人：xulinchuang
 * 创建时间：2016年12月28日 上午10:48:26
 */
public class AccountBindOrg implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 3177487301135161434L;
	
	private long id;
	private long accountId;//账号id
	private long orgId;//结构id(总部、自营、代理)
	private int valid;//1、有效0、无效
	private long utime;
	private long ctime;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
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
