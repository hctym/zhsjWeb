package com.zhsj.service;

import java.util.List;

import com.zhsj.model.Module;
import com.zhsj.model.Role;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：角色接口
 * 类名称：com.zhsj.service.RoleService     
 * 创建人：xulinchuang
 * 创建时间：2016年12月29日 上午11:07:12
 */
public interface RoleService {

	/**
	 * 
	 * @Title: insert
	 * @Description: 添加
	 * @param role
	 * @return
	 * @throws Exception
	 */
	int insert(Role role) throws Exception;

	/**
	 * 
	 * @Title: getModules
	 * @Description: 通过角色id查询模块
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	List<Module> getParentModulesByRoleId(int roleId) throws Exception;

	/**
	 * 
	 * @Title: getRoles
	 * @Description: 获取有效的角色列表
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	List<Role> getRoles(int page,int pageSize) throws Exception;
    /**
     * 
     * @Title: getAllModuleByRoleId
     * @Description: 通过角色id获取所有的子模块以及关联的父模块(用于展示角色拥有的模块)
     * @param roleId
     * @return
     */
	List<Module> getAllModuleByRoleId(int roleId) throws Exception;
    /**
     * 
     * @Title: deleteById
     * @Description: TODO
     * @param id
     * @return
     */
	int deleteById(int id) throws Exception;
    /**
     * 
     * @Title: update
     * @Description: 更新
     * @param role
     * @throws Exception
     */
	void update(Role role) throws Exception;
    /**
     * 
     * @Title: getParentModulesByRoleIds
     * @Description: 通过角色id  查询该用户所有的父模块
     * @param roleIds
     * @return
     */
	List<Module> getParentModulesByRoleIds(List<Integer> roleIds) throws Exception;
    /**
     * 
     * @Title: getModulesByPmIdAndRoleIds
     * @Description: 通过父模块id和当前用户的角色集合 查找该模块的子模块
     * @param moduleId
     * @param roleIds
     * @return
     */
	List<Module> getModulesByPmIdAndRoleIds(int moduleId, List<Integer> roleIds) throws Exception;
    /**
     * 
     * @Title: getListByType
     * @Description: 通过角色所属的类型  获取角色
     * @param type
     * @return
     */
	List<Role> getListByType(int type) throws Exception;
}
