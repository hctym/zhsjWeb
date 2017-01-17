package com.zhsj.service;

import java.util.Map;

import com.zhsj.model.Account;
import com.zhsj.model.Role;

public interface AccountService {

	int add(Account account,int roleId,int orgId) throws Exception;

	
	/**
	 * 
	 * @Title: getByNameAndPassword
	 * @Description: 通过用户的账户名称和密码  获取用户
	 * @param accountName
	 * @param password
	 * @return 返回0：用户不存在 返回1：登录成功 2：密码错误
	 */
	int getByNameAndPassword(String accountName,String password) throws Exception;
	/**
	 * 
	 * @Title: getByName
	 * @Description: 通过用户名查找 用户账号
	 * @param accountName
	 * @return
	 * @throws Exception
	 */
	
	Account getByName(String accountName) throws Exception;

    /**
     * 
     * @Title: getByNameAndMd5Password
     * @Description: 通过用户和加密的密码 查询用户
     * @param username
     * @param md5password
     * @return
     */
	Account getByNameAndMd5Password(String username, String md5password) throws Exception;

    /**
     * 
     * @Title: getRole
     * @Description: 通过用户的id查询角色
     * @param id
     * @return
     */
	Role getRole(int id) throws Exception;

    /**
     * 
     * @Title: getList
     * @Description: 分页查询用户账户
     * @param page
     * @param pageSize
     * @param orgId
     * @return
     */
	Map<String, Object> getList(int page, int pageSize,long orgId) throws Exception;
}
