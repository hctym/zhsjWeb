package com.zhsj.model;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：组织
 * 类名称：com.zhsj.model.Org     
 * 创建人：xulinchuang
 * 创建时间：2016年12月28日 上午11:16:05
 */
public class Org implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 2999594048794937504L;
	private long id;
	private long parentId;//父组织id
	private String orgIds;//父组织的id拼接（便于查询）。
	private String name;//组织名称
	private int levelType;//类型1、总部2、自营3、代理
	private String contactPhone;//联系电话
	private String email;//
	private int cityId;//
	private int isAllow;//是否允许招商0、不允许1、允许
	private int valid;//1、有效0、无效
	private int status;//状态0、禁用1、启用
	private long utime;
	private long ctime;
	//2017
	private transient List<Org> corg;
	private transient CityCode cityCode;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevelType() {
		return levelType;
	}
	public void setLevelType(int levelType) {
		this.levelType = levelType;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getIsAllow() {
		return isAllow;
	}
	public void setIsAllow(int isAllow) {
		this.isAllow = isAllow;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public List<Org> getCorg() {
		return corg;
	}
	public void setCorg(List<Org> corg) {
		this.corg = corg;
	}
	public CityCode getCityCode() {
		return cityCode;
	}
	public void setCityCode(CityCode cityCode) {
		this.cityCode = cityCode;
	}
	

}
