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
 * 创建时间：2016年12月13日 上午9:49:37
 */
public class Module implements Serializable{

	/**
	 * @Fields serialVersionUID : 
	 */ 
	private static final long serialVersionUID = 7678414307778373120L;
	
	private int id;
	private int parentId;//父id
	private String mname;//模块名称
	private String url;//模块url
	private String mdesc;//描述
	private long ctime;//
	
	private transient List<Module> childrenModules;//用户映射 子模块
	private transient Module parentModule;//用户映射 父模块
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMdesc() {
		return mdesc;
	}
	public void setMdesc(String mdesc) {
		this.mdesc = mdesc;
	}
	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}
	public List<Module> getChildrenModules() {
		return childrenModules;
	}
	public void setChildrenModules(List<Module> childrenModules) {
		this.childrenModules = childrenModules;
	}
	public Module getParentModule() {
		return parentModule;
	}
	public void setParentModule(Module parentModule) {
		this.parentModule = parentModule;
	}
	
}
