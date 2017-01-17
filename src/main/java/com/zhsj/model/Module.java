package com.zhsj.model;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：模块
 * 类名称：com.zhsj.model.Module     
 * 创建人：xulinchuang
 * 创建时间：2016年12月28日 上午10:58:21
 */
public class Module implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 7678414307778373120L;

	private int id;
	private String name;//名称
	private String url;//url
	private String parentId;//父模块id
	private int valid;//1、有效0、无效
	private long utime;
	private long ctime;
	private transient List<Module> childrens;//子节点
	private transient Module parent;//父节点
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
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
	public List<Module> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<Module> childrens) {
		this.childrens = childrens;
	}
	public Module getParent() {
		return parent;
	}
	public void setParent(Module parent) {
		this.parent = parent;
	}
	
	
}
