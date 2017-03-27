package com.zhsj.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：商户账户
 * 类名称：com.zhsj.model.StoreAccount     
 * 创建人：xulinchuang
 * 创建时间：2016年12月28日 下午1:12:56
 */
public class StoreAccount implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 6447960615293910570L;
	
	private long id;
	private String account;//账户，最好用手机号
	private String password;
	private String openId;//微信唯一id
	private String name;
	private String headImg;//头像
	private int gender;//1、男2、女
	private String mobile;
	private String email;
	private int status;//账户状态：0、禁用  1、启用
	private int valid;//1、有效   0、无效
	private long utime;
	private long ctime;
	//transient
	private transient List<StoreAccountBindRole> storeAccountBindRoles;
	
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
	public List<StoreAccountBindRole> getStoreAccountBindRoles() {
		return storeAccountBindRoles;
	}
	public void setStoreAccountBindRoles(
			List<StoreAccountBindRole> storeAccountBindRoles) {
		this.storeAccountBindRoles = storeAccountBindRoles;
	}
	
}
