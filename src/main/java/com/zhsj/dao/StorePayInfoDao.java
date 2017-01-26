package com.zhsj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.StorePayInfo;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：支付方式Dao
 * 类名称：com.zhsj.dao.StorePayInfoDao     
 * 创建人：xulinchuang
 * 创建时间：2017年1月20日 上午9:50:16
 */
public interface StorePayInfoDao {
    
	int add(StorePayInfo storePayInfo);
	/**
	 * 
	 * @Title: getListByStoreNo
	 * @Description: 通过storeNo查询
	 * @param storeNo
	 * @return
	 */
	List<StorePayInfo> getListByStoreNo(@Param("storeNo")String storeNo);
	
	/**
	 * 
	 * @Title: update
	 * @Description: 更新
	 * @param storePayInfo
	 * @return
	 */
	int update(StorePayInfo storePayInfo);
	
	/**
	 * 
	 * @Title: getById
	 * @Description: 通过id查询支付方式
	 * @param id
	 * @return
	 */
	StorePayInfo getById(@Param("id")int id);
}
