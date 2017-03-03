package com.zhsj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.Discount;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：优惠Dao
 * 类名称：com.zhsj.dao.DiscountDao     
 * 创建人：xulinchuang
 * 创建时间：2017年1月4日 下午5:15:06
 */
public interface DiscountDao {

	int add(Discount discount);
	/**
	 * 
	 * @Title: getListByPage
	 * @Description: 分页
	 * @param row
	 * @param pageSize
	 * @param storeNo
	 * @return
	 */
	List<Discount> getListByPageAndStoreNo(@Param("row")int row,
			@Param("pageSize")int pageSize,@Param("storeNo")String storeNo);
	
	
	/**
	 * 
	 * @Title: getListByParam
	 * @Description: 查看优惠活动分页
	 * @param startTime
	 * @param entTime
	 * @param status
	 * @param type
	 * @param name
	 * @param row
	 * @param pageSize
	 * @return
	 */
    List<Discount> getListByParam(@Param("startTime")int startTime,
    		@Param("endTime")int entTime,
    		@Param("status")int status,
    		@Param("type")int type,
    		@Param("name")String name,
    		@Param("row")int row,
    		@Param("pageSize")int pageSize);
    
    int getCountByParam(@Param("startTime")int startTime,
    		@Param("endTime")int entTime,
    		@Param("status")int status,
    		@Param("type")int type,
    		@Param("name")String name);
    
	void updateValidByDiscountId(@Param("discountId")int discountId);
	
	void updateStatusByDiscountId(@Param("discountId")int discountId);
}
