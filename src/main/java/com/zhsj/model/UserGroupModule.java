package com.zhsj.model;

import java.io.Serializable;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：用户组和子模块的关联
 * 类名称：com.zhsj.model.UserGroupModule     
 * 创建人：xulinchuang
 * 创建时间：2016年12月13日 上午9:41:41
 */
public class UserGroupModule implements Serializable{

	private static final long serialVersionUID = -9162170128110265028L;
	
	private int id;
	private int userGroupId;//用户组的id
	private int moduleId;//子模块的id
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	
	
}
