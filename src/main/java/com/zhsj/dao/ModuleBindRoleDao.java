package com.zhsj.dao;

import java.util.List;

import com.zhsj.model.ModuleBindRole;

public interface ModuleBindRoleDao {
  
	/**
	 * 
	 * @Title: insert
	 * @Description: 添加模块和角色关联
	 * @param moduleBindRole
	 * @return
	 */
	int add(ModuleBindRole moduleBindRole);

	/**
	 * 
	 * @Title: getModulesByRoleId
	 * @Description: 通过角色id查询关联的模块
	 * @param roleId
	 * @return
	 */
	List<ModuleBindRole> getModulesByRoleId(int roleId);
	
	
	/**
	 * 
	 * @Title: deleteAllByRoleId
	 * @Description: 通过角色id删掉关联的模块
	 * @param roleId
	 * @return
	 */
	int deleteAllByRoleId(int roleId);
}
