package com.zhsj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.BusinessUser;

public interface BusinessUserDao {
    /**
     * 
     * @Title: add
     * @Description: 添加商户用户
     * @param businessUser
     * @return
     */
	int insert(BusinessUser businessUser);
	
	/**
	 * 
	 * @Title: getBusinessUsersByBusinessInfoId
	 * @Description: 根据商户id 查询该商户中的用户
	 * @param bid
	 * @return
	 */
	List<BusinessUser> getBusinessUsersByBusinessInfoId(@Param("bid")int bid);
    /**
     * 
     * @Title: getUser
     * @Description: 用于 商家 商户登录
     * @param name
     * @param encrypt
     * @return
     */
	BusinessUser getUser(@Param("name")String name, @Param("encrypt")String encrypt);
	
}
