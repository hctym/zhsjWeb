package com.zhsj.service;


import java.util.List;

import com.zhsj.model.Module;

public interface ModuleService {

	/**
	 * 
	 * @Title: insert
	 * @Description: 添加模块
	 * @param module
	 * @return
	 * @throws Exception
	 */
	int insert(Module module) throws Exception;
	
	/**
	 * 
	 * @Title: getModules
	 * @Description: 获取所有模块
	 * @return
	 * @throws Exception
	 */
	List<Module> getModules() throws Exception;
    /**
     * 
     * @Title: getModulesByParentModuleIdAndRoleId
     * @Description: 通过父模块id和roleID  查询子模块
     * @param parentModuleId
     * @param roleId
     * @return
     * @throws Exception
     */
	List<Module> getModulesByParentModuleIdAndRoleId(int parentModuleId,
			int roleId) throws Exception;
}
