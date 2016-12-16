package com.zhsj.service;

import java.util.List;

import com.zhsj.model.Module;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：模块接口
 * 类名称：com.zhsj.service.ModuleService     
 * 创建人：xulinchuang
 * 创建时间：2016年12月13日 上午10:31:30
 */
public interface ModuleService {

	int add(Module module) throws Exception;
	 
	int update(Module module) throws Exception;
	
	int delete(int id) throws Exception;
	/**
	 * 
	 * @Title: getParentModulesByUserGroupId
	 * @Description: 通过用户组的id 查询 父模块
	 * @param userGroupId
	 * @return
	 */
	List<Module> getParentModulesByUserGroupId(int userGroupId) throws Exception;
	/**
	 * 
	 * @Title: getChildrenModulesByModuleId
	 * @Description: 通过父模块的id和用户组的id 查询该用户组在该父模块下的子模块
	 * @param moduleId
	 * @param userGroupId
	 * @return
	 */
	List<Module> getChildrenModulesByModuleId(int moduleId,int userGroupId) throws Exception;
	
	/**
	 * 
	 * @Title: geModules
	 * @Description: 查询所有模块以及模块的关系
	 * @return
	 */
	List<Module> getModules() throws Exception;
    /**
     * 
     * @Title: getModulesByUserGroupId
     * @Description: 通过用户组id查看该用户组i关联的模块(子模块关联的二级模块和三级模块)
     * @param userGroupId
     * @return
     * @throws Exception
     */
	List<Module> getModulesByUserGroupId(int userGroupId) throws Exception;
}
