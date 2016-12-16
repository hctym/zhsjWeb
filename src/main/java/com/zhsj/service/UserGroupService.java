package com.zhsj.service;

import java.util.List;

import com.zhsj.model.UserGroup;
import com.zhsj.model.UserGroupModule;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：用户组的接口
 * 类名称：com.zhsj.service.UserGroupService     
 * 创建人：xulinchuang
 * 创建时间：2016年12月8日 上午10:36:18
 */
public interface UserGroupService {
	/**
	 * 
	 * @Title: add
	 * @Description: 添加用户组
	 * @param userGroup
	 * @return
	 */
	int add(UserGroup userGroup) throws Exception;
    /**
     * 
     * @Title: getList
     * @Description: 获取用户组list
     * @return
     */
	List<UserGroup> getList() throws Exception;
	
	/**
	 * 
	 * @Title: addUserGroupModule
	 * @Description: 给用户组添加模块
	 * @param userGroupId  用户组id
	 * @param moduleIds   模块集合
	 * @return
	 * @throws Exception
	 */
	int addUserGroupModule(int userGroupId,int[] moduleIds) throws Exception;
	
	/**
	 * 
	 * @Title: getModulesByUserGroupId
	 * @Description: TODO
	 * @param UserGroupId
	 * @return
	 */
	List<UserGroupModule> getModulesByUserGroupId(int userGroupId);
}
