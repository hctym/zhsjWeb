package com.zhsj.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：账户
 * 类名称：com.zhsj.model.Account     
 * 创建人：xulinchuang
 * 创建时间：2016年12月28日 上午10:48:22
 */
public class Account implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 5518418016227935460L;
	private long id;
	private String account;//账户，最好用手机号
	private String password;
	private String openId;//微信唯一id
	private String name;
	private String headImg;//头像
	private int gender;//1、男2、女
	private String mobile;//手机
	private String email;
	private int status;//账户状态0、禁用1、启用
	private int valid;//1、有效0、无效
	private long ctime;
	private long utime;
	//用于映射transient
	private transient List<AccountBindRole> accountBindRoles;
	private transient AccountBindOrg accountBindOrg;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
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
	public List<AccountBindRole> getAccountBindRoles() {
		return accountBindRoles;
	}
	public void setAccountBindRoles(List<AccountBindRole> accountBindRoles) {
		this.accountBindRoles = accountBindRoles;
	}
	public AccountBindOrg getAccountBindOrg() {
		return accountBindOrg;
	}
	public void setAccountBindOrg(AccountBindOrg accountBindOrg) {
		this.accountBindOrg = accountBindOrg;
	}

}
