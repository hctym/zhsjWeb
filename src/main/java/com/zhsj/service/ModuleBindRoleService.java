package com.zhsj.service;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：模块和角色关联的接口
 * 类名称：com.zhsj.service.ModuleBindRoleService     
 * 创建人：xulinchuang
 * 创建时间：2016年12月29日 上午11:18:04
 */
public interface ModuleBindRoleService {

	/**
	 * 
	 * @Title: addAndUpdate
	 * @Description: 给角色添加模块。如果角色有关联的模块。先删掉关联的模块。重新添加关联关系。
	 * @param roleId
	 * @param modules
	 * @return
	 * @throws Exception
	 */
	int addAndUpdate(int roleId,int moduleIds[]) throws Exception;
}
