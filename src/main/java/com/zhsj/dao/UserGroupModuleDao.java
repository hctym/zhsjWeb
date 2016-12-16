package com.zhsj.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.UserGroupModule;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：给用户组(角色)分配模块
 * 类名称：com.zhsj.dao.UserGroupModuleDao     
 * 创建人：xulinchuang
 * 创建时间：2016年12月14日 上午11:46:48
 */
public interface UserGroupModuleDao {

	/**
	 * 
	 * @Title: add
	 * @Description: 添加一条关联关系
	 * @param userGroupModule
	 * @return
	 */
	int add(UserGroupModule userGroupModule);
	
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除一条关联关系
	 * @param id
	 * @return
	 */
	int delete(@Param("id")int id);
	/**
	 * 
	 * @Title: deleteMultiple
	 * @Description: 删除多天记录
	 * @param ugModuleIds
	 * @return
	 */
	int deleteMultiple(List<Integer> ugModuleIds);
	
	/**
	 * 
	 * @Title: update
	 * @Description: 更新一个关联关系
	 * @param userGroupModule
	 * @return
	 */
	int update(UserGroupModule userGroupModule);
    /**
     * 
     * @Title: getModulesByUserGroupId
     * @Description: 通过用户组获取该用户组对应的模块
     * @param userGroupId
     * @return
     */
	List<UserGroupModule> getModulesByUserGroupId(@Param("userGroupId")int userGroupId);
}
