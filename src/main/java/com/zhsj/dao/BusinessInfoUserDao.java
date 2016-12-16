package com.zhsj.dao;

import com.zhsj.model.BusinessInfoUser;

public interface BusinessInfoUserDao {
    /**
     * 
     * @Title: insert
     * @Description: 添加商家信息和商家用户的关系
     * @param businessInfoUser
     * @return
     */
	int insert(BusinessInfoUser businessInfoUser);
}
