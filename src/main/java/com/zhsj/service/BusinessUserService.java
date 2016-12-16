package com.zhsj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.BusinessUser;

public interface BusinessUserService {
    /**
     * 
     * @Title: addBusinessUser
     * @Description: 给商户信息添加商户用户以及商户信息和商户用户之间关联
     * @param businessInfoId
     * @param businessUser
     * @return
     */
	int addBusinessUser(int businessInfoId,BusinessUser businessUser) throws Exception;
	
	/**
	 * 
	 * @Title: getBusinessUsersByBusinessInfoId
	 * @Description: 通过商户信息id 查询该商户中的用户
	 * @param bid
	 * @return
	 */
	List<BusinessUser> getBusinessUsersByBusinessInfoId(@Param("bid")int bid) throws Exception;
    /**
     * 
     * @Title: getUser
     * @Description: 用于商家 商户登录
     * @param name
     * @param encrypt
     * @return
     */
	BusinessUser getUser(String name, String encrypt);
}
