package com.zhsj.model;

import java.io.Serializable;
/**
 * 
 * @author xulinchuang
 *
 */
public class City implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4846530548664207354L;
	
	private int id;
	private String name;//城市名称
	private int cityCode;//城市代号
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
	public int getCityCode() {
		return cityCode;
	}
	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}
	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}

	
	
}
