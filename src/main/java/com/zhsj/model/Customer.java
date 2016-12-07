package com.zhsj.model;

import java.io.Serializable;
/**
 * 
 * @author xulinchuang
 * 消费者
 *
 */
public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8743031478733836819L;
	
	
	private int id;
	private String nick;//昵称
	private String headImg;//头像
	private long utime;//
	private long ctime;//
	private String regionAttr;//归属地
	private int regionId;//区域id
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
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
	public String getRegionAttr() {
		return regionAttr;
	}
	public void setRegionAttr(String regionAttr) {
		this.regionAttr = regionAttr;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	
	
}
