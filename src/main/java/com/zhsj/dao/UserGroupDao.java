package com.zhsj.dao;

import java.util.List;

import com.zhsj.model.UserGroup;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：用户组的dao
 * 类名称：com.zhsj.dao.UserGroupDao     
 * 创建人：xulinchuang
 * 创建时间：2016年12月8日 上午10:30:51
 */
public interface UserGroupDao {
    /**
     * 
     * @Title: getList
     * @Description: 查询所有的用户组
     * @return
     */
	List<UserGroup> getList();
	
	/**
	 * 
	 * @Title: add
	 * @Description: 添加用户组
	 * @param userGroup
	 * @return
	 */
	int add(UserGroup userGroup);
}
