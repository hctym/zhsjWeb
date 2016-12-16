package com.zhsj.model;

import java.io.Serializable;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：用户和用户组（角色）的关联
 * 类名称：com.zhsj.model.BmUserGroup     
 * 创建人：xulinchuang
 * 创建时间：2016年12月14日 上午9:25:52
 */
public class BmUserGroup implements Serializable{

	/**
	 * @Fields serialVersionUID : 
	 */ 
	private static final long serialVersionUID = -6152196206065314356L;
	private int id;
	private int bmUserId;//用户的id
	private int userGroupId;//用户组id(角色)
	
	
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
	public int getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}
	
	
}
