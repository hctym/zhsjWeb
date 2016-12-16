package com.zhsj.model;

import java.io.Serializable;
/**
 * 
 * @author xulinchuang
 * 商家信息
 *
 */
public class BusinessInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4546050751077693566L;
	private int id;
	private String shopName;//门店名称
	private int parentId;//父id
	private int shopType;//门店类型0：特约门店(自己有字商户号)1：一般商户(系统代收款)
	private int areaId;//所在区域id
	private String address;//地址
	private String contact;//联系人
	private String contactPhone;//联系电话
	private String shopLogo;//店招
	private String intro;//介绍
	private int status;//状态0：启用1：禁用2：删除
	
	private int iscooperate;//是否是合作母商户 0为否 1为是'
	private String latitude;//门店维度
	private String longitude;//门店经度a
	private int iszx;//支付方式
	private long ctime;
	private long utime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getShopType() {
		return shopType;
	}
	public void setShopType(int shopType) {
		this.shopType = shopType;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getShopLogo() {
		return shopLogo;
	}
	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIscooperate() {
		return iscooperate;
	}
	public void setIscooperate(int iscooperate) {
		this.iscooperate = iscooperate;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getIszx() {
		return iszx;
	}
	public void setIszx(int iszx) {
		this.iszx = iszx;
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
	
	
	
	
	


}
