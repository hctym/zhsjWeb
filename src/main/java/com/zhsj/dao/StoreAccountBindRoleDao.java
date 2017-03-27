package com.zhsj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.StoreAccountBindRole;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：门店账户 和角色的关联dao
 * 类名称：com.zhsj.dao.StoreAccountBindRoleDao     
 * 创建人：xulinchuang
 * 创建时间：2017年1月20日 上午11:34:05
 */
public interface StoreAccountBindRoleDao {

	int add(StoreAccountBindRole storeAccountBindRole);
	/**
	 * 
	 * @Title: getListByAccountId
	 * @Description: 通过账户id  查询账户和组织的关联
	 * @param accountId
	 * @return
	 */
	List<StoreAccountBindRole> getListByAccountId(@Param("accountId")long accountId);
}
