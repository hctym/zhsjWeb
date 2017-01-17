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

}
