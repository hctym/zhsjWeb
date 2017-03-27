package com.zhsj.model;

import java.io.Serializable;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：
 * 类名称：com.zhsj.model.User     
 * 创建人：xulinchuang
 * 创建时间：2016年12月28日 下午1:06:39
 */
public class User implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 5900094947921482542L;
	
	private long id;
	private String openId;//微信id  如果是支付宝，前面加ZF
	private String nickName;//昵称
	private String headImg;//头像
	private int sex;//0、未知1、男2、女
	private String city;//城市
	private String province;//省
	private String country;//国家
	private String privilege;//微信用户特权
	private String unionid;//微信用户的唯一id
	private long utime;
	private long ctime;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
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
