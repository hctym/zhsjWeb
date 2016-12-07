package com.zhsj.model;

import java.io.Serializable;
/**
 * 
 * @author xulinchuang
 * 用户组
 *
 */
public class UserGroup implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4935900622728949612L;
	private int id;
	private String name;//用户组名称
	private int isValid;//是否有效(涉及到关联的商家。不合适,如果无效。则是否还需要判断关联该用户组的商家或者用户？)
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
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
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
