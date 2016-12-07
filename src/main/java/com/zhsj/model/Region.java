package com.zhsj.model;

import java.io.Serializable;
/**
 * 
 * @author xulinchuang
 * 区域
 *
 */
public class Region implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1707312756834386715L;
	
	private int id;
	private String name;//区域表的名称(可以是城市、商圈)
	private int parentId;//区域的父id
	private char isValid;//是否有效1：有效0：无效
	private long utime;
	private long ctime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public char getIsValid() {
		return isValid;
	}
	public void setIsValid(char isValid) {
		this.isValid = isValid;
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
