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
	
	
	
	
	


}
