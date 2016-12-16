package com.zhsj.dao;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.BmUserGroup;

public interface BmUserGroupDao {
    /**
     * 
     * @Title: add
     * @Description: 给用户赋予那个用户组(用户和角色的关系)
     * @param bmUserGroup
     * @return
     */
	int add(BmUserGroup bmUserGroup);
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除该用户的角色
	 * @param id
	 * @return
	 */
	int delete(@Param("id")int id);
	
	/**
	 * 
	 * @Title: update
	 * @Description: 更新该用户的角色
	 * @param bmUserGroup
	 * @return
	 */
	int update(BmUserGroup bmUserGroup);
	
	/**
	 * 
	 * @Title: getBmUserGroupByBmUserId
	 * @Description: 通过用户id查询是否该用户已分配角色
	 * @param bmUserId
	 * @return
	 */
	BmUserGroup getBmUserGroupByBmUserId(@Param("bmUserId")int bmUserId);
}
