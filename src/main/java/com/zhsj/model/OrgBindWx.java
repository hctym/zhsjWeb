package com.zhsj.model;

import java.io.Serializable;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：组织和公众号
 * 类名称：com.zhsj.model.OrgBindWx     
 * 创建人：xulinchuang
 * 创建时间：2016年12月30日 下午1:44:27
 */
public class OrgBindWx implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 8001629367339482113L;
	
	private int id;
	private int orgId;//组织id
	private int wxId;//
	private int valid;//1、有效0、无效
	private int utime;
	private int ctime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public int getWxId() {
		return wxId;
	}
	public void setWxId(int wxId) {
		this.wxId = wxId;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
	public int getUtime() {
		return utime;
	}
	public void setUtime(int utime) {
		this.utime = utime;
	}
	public int getCtime() {
		return ctime;
	}
	public void setCtime(int ctime) {
		this.ctime = ctime;
	}
	
}
