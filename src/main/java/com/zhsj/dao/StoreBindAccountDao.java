package com.zhsj.dao;


import org.apache.ibatis.annotations.Param;

import com.zhsj.model.StoreBindAccount;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：商户门店和账户 的绑定
 * 类名称：com.zhsj.dao.StoreBindAccountDao     
 * 创建人：xulinchuang
 * 创建时间：2017年1月3日 上午11:16:56
 */
public interface StoreBindAccountDao {

	int add(StoreBindAccount sba);
	/**
	 * 
	 * @Title: getByAccountId
	 * @Description: 通过accountID  获取关联的门店信息
	 * @param id
	 * @return
	 */
	StoreBindAccount getByAccountId(@Param("id")long id);
}
