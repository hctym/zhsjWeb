package com.zhsj.dao;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.AccountBindOrg;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：账户和组织(结构)关联
 * 类名称：com.zhsj.dao.AccountbindOrgDao     
 * 创建人：xulinchuang
 * 创建时间：2016年12月29日 下午3:52:38
 */
public interface AccountBindOrgDao {

	int add(AccountBindOrg accountBindOrg);
	/**
	 * 
	 * @Title: getByAccountId
	 * @Description: 通过用户的id 查询账户和组合的关联
	 * @param accountId
	 * @return
	 */
	AccountBindOrg getByAccountId(@Param("accountId")long accountId);
	
}
