package com.zhsj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.Module;

public interface ModuleDao {

	int add(Module module);
	
	int update(Module module);
	
	int delete(@Param("id")int id);
	/**
	 * 
	 * @Title: getModulesByUserGroupId
	 * @Description: 通过用户组id 查询它所有的子模块(具体的模块)
	 * @return
	 */
	List<Module> getParentModulesByUserGroupId(@Param("userGroupId")int userGroupId);
	
	
	/**
	 * 
	 * @Title: getModulesByParentModuleId
	 * @Description: 通过父模块id和当前的用户组id  查询它所有的二级模块以及关联的三级子模块()
	 * @param moduleId
	 * @return
	 */
	List<Module> getModulesByParentModuleId(@Param("moduleId")int moduleId,@Param("userGroupId")int userGroupId);
	
	/**
	 * 
	 * @Title: getModules
	 * @Description: 查询所有的模块
	 * @return
	 */
	List<Module> getModules();
    /**
     * 
     * @Title: getModulesByUserGroupId
     * @Description: 根据用户组id查询该用户组关联的模块(子模块以及二级模块和三级模块)
     * @param userGroupId
     * @return
     */
	List<Module> getModulesByUserGroupId(@Param("userGroupId")int userGroupId);
}
