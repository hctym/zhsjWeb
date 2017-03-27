package com.zhsj.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.Order;

public interface OrderDao {

	int add(Order order);
	/**
	 * 
	 * @Title: getListByPage
	 * @Description: 分页
	 * @param row
	 * @param pageSize
	 * @return
	 */
	List<Order> getListByPage(@Param("row")int row,
			@Param("pageSize")int pageSize);
	
	/**
	 * 
	 * @Title: getCount
	 * @Description: 获取订单的总数(用于分页)
	 * @return
	 */
	int getCount();
	/**
	 * 
	 * @Title: getOrderListByMapOrgId
	 * @Description: 通过参数查询订单
	 * @param map
	 * @return
	 */
	List<Order> getOrderListByMapOrgId(Map<String, Object> map);
	/**
	 * 
	 * @Title: getCountByMapOrgId
	 * @Description: 根据参数获取订单的总数(分页)
	 * @param map
	 * @return
	 */
	int getCountByMapOrgId(Map<String, Object> map);
	/**
	 * 
	 * @Title: getOrderListByMapStoreNo
	 * @Description: 通过门店编号以及参数 查询订单 分页
	 * @param map
	 * @return
	 */
	List<Order> getOrderListByMapStoreNo(Map<String, Object> map);
	/**
	 * 
	 * @Title: getCountByMapStoreNo
	 * @Description: 根据门店编号 以及参数 获取订单的总数(用于分页)
	 * @param map
	 * @return
	 */
	int getCountByMapStoreNo(Map<String, Object> map);
}
