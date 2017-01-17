package com.zhsj.dao;


import org.apache.ibatis.annotations.Param;

import com.zhsj.model.StoreDiscount;
/**
 * 
 * 项目名称：zhsjWeb   
 * 
 * 类描述：门店编号和优惠关联绑定
 * 类名称：com.zhsj.dao.StoreDiscountDao     
 * 创建人：xulinchuang
 * 创建时间：2017年1月4日 下午5:21:07
 */
public interface StoreDiscountDao {
    
	int add(StoreDiscount storeDiscount);
    /**
     * 
     * @Title: getCount
     * @Description: 通过storeNo 查询总数(用于分页)
     * @param storeNo
     * @return
     */
	int getCountByStoreNo(@Param("storeNo")String storeNo);
}
