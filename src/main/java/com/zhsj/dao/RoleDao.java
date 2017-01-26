package com.zhsj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.Module;
import com.zhsj.model.Role;

public interface RoleDao {
    /**
     * 
     * @Title: insert
     * @Description: 添加角色
     * @param role
     * @return
     */
	int add(Role role);

	/**
	 * 
	 * @Title: getModules
	 * @Description: 通过角色id查找关联的模块
	 * @param roleId
	 * @return
	 */
	List<Module> getParentModulesByRoleId(int roleId);
    /**
     * 
     * @Title: getRoles
     * @Description: 获取当前有效的角色
     * @return
     */
	List<Role> getRoles(@Param("row")int row,@Param("pageSize")int pageSize);
    /**
     * 
     * @Title: getAllModuleByRoleId
     * @Description: 获取所有子模块(以及关联的父模块)
     * @param roleId
     * @return
     */
	List<Module> getAllModuleByRoleId(int roleId);
    
	int deleteById(int id);
    
	void update(Role role);
	
	
	
}
