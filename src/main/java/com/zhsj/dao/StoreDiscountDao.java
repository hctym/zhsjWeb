package com.zhsj.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.Discount;
import com.zhsj.model.Store;
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
	
	/**
	 * 
	 * @Title: getByParam
	 * @Description: 
	 * @param storenoList
	 * @param start
	 * @param end
	 * @return
	 */
	List<Discount> getByParam(@Param("storeNoList")List<String> storeNoList,
            @Param("start")int start,
            @Param("end")int end);
	/**
	 * 
	 * @Title: addMore
	 * @Description: TODO
	 * @param storeList
	 * @param start
	 * @param end
	 * @param discountid
	 * @return
	 */
	int addMore(@Param("storeList")List<Store> storeList,
			@Param("start")long start,
			@Param("end")long end,
			@Param("discountId")int discountid);
	
	void upDateValidByDiscountId(@Param("discountId")int discountId);
	
	void updateStatusByDiscountId(@Param("discountId")int discountId);
}
