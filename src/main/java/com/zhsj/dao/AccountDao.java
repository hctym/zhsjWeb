package com.zhsj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.Account;
import com.zhsj.model.Role;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：账户
 * 类名称：com.zhsj.dao.AccountDao     
 * 创建人：xulinchuang
 * 创建时间：2016年12月29日 下午3:20:37
 */
public interface AccountDao {

	
	int add(Account account);
    /**
     * 
     * @Title: getByName
     * @Description: 通过用户的账户名称  获取账户(用于用户登录)
     * @param accountName
     * @return
     */
	Account getByName(@Param("accountName")String accountName);
	
	/**
	 * 
	 * @Title: getByAccount
	 * @Description: 通过账号  验证是否 该手机号已被注册
	 * @param account
	 * @return
	 */
	Account getByAccount(@Param("account")String account);
	/**
	 * 
	 * @Title: getByNameAndMd5Password
	 * @Description: 通过用户名和加密的密码 查询用户
	 * @param username
	 * @param md5password
	 * @return
	 */
	Account getByNameAndMd5Password(@Param("username")String username, @Param("md5password")String md5password);
	/**
	 * 
	 * @Title: getrRole
	 * @Description: 通过用户id查询角色
	 * @param id
	 * @return
	 */
	Role getRole(@Param("id")int id);
	/**
	 * 
	 * @Title: getList
	 * @Description: 分页查询 account
	 * @param row
	 * @param pageSize
	 * @param orgId
	 * @return
	 */
	List<Account> getListByPage(@Param("row")int row,
			@Param("pageSize") int pageSize,@Param("orgId")long orgId);
	/**
	 * 
	 * @Title: getCount
	 * @Description: 获取总数量(用于分页)
	 * @param orgId
	 * @return
	 */
	int getCount(@Param("orgId")long orgId);
	/**
	 * 
	 * @Title: getById
	 * @Description: 通过id查询用户
	 * @param id
	 * @return
	 */
	Account getById(@Param("id")long id);
	/**
	 * 
	 * @Title: update
	 * @Description: 更新用户
	 * @param account
	 */
	void update(Account account);
}
