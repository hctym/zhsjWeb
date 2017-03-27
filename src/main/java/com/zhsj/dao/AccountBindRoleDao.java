package com.zhsj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.AccountBindRole;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：账户角色关联
 * 类名称：com.zhsj.dao.AccountBindRole     
 * 创建人：xulinchuang
 * 创建时间：2016年12月29日 下午3:48:01
 */
public interface AccountBindRoleDao {

	int add(AccountBindRole accountBindRole);
	/**
	 * 
	 * @Title: getAccountBindRoleListByAccountId
	 * @Description: 通过用户id 查询关联的角色
	 * @param accountId
	 * @return
	 */
	List<AccountBindRole> getAccountBindRoleListByAccountId(@Param("accountId")long accountId);
	
	/**
	 * 
	 * @Title: deleteAllByAccountId
	 * @Description: 通过用户id 删除所有该用户关联的角色
	 * @param accountId
	 */
	void deleteAllByAccountId(@Param("accountId")int accountId);
}
