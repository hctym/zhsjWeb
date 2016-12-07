package com.zhsj.model;

import java.io.Serializable;
/**
 * 
 * @author xulinchuang
 * 用户
 *
 */
public class BmUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3867938711318040371L;
	
	private int id;//主键
	private String account;//账户：手机号作为登录名
	private String password;//密码
	private String name;//名称
	private char gender;//性别 1：男 2：女
	private String mobile;//手机号
	private String email;//邮箱
	private int userGroupId;//用户组id
	private long ctime;//
	private long utime;//
	private int isValid;//是否有效 1：有效 0：无效
	private	int status;//账户状态 0：禁用 1：启用
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}
	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}
	public long getUtime() {
		return utime;
	}
	public void setUtime(long utime) {
		this.utime = utime;
	}
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
	
	

}
