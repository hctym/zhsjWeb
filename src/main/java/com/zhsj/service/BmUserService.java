package com.zhsj.service;

import java.util.List;

import com.zhsj.model.BmUser;
import com.zhsj.model.BmUserGroup;

public interface BmUserService {
    /**
     * 
     * @Title: insert
     * @Description: 添加
     * @param bmUser
     * @return
     * @throws Exception
     */
	int insert(BmUser bmUser) throws Exception;
	/**
	 * 
	 * @Title: getList
	 * @Description: 分页
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	List<BmUser> getList(int page,int pageSize) throws Exception;
	/**
	 * 
	 * @Title: getBmUserById
	 * @Description: 通过id获取详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BmUser getBmUserById(int id) throws Exception;
	/**
	 * 
	 * @Title: getBmUser
	 * @Description: 登录验证
	 * @param account
	 * @param password
	 * @return
	 */
	BmUser getBmUser(String account,String password);
	/**
	 * 
	 * @Title: addOrUpdateUserGroup
	 * @Description: 添加或者修改用户和用户组的关联(添加关联，注意一个用户只能关联一个用户组)
	 * @param bmUserGroup
	 * @return
	 */
	int addOrUpdateUserGroup(BmUserGroup bmUserGroup);
}
