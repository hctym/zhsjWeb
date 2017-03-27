package com.zhsj.service;

import java.math.BigDecimal;
import java.util.Map;

import com.zhsj.model.Order;
import com.zhsj.model.StoreAccount;

public interface OrderService {
    
	int add(Order order) throws Exception;
	/**
	 * 
	 * @Title: getListAndCountByPage
	 * @Description: 分页以及总数
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getListAndCountByPage(int page,int pageSize) throws Exception;
	/**
	 * 
	 * @Title: getOrderListByPageAndParam
	 * @Description: 通过组织id 以及条件  查询订单 分页
	 * @param page
	 * @param pageSize
	 * @param startTime
	 * @param endTime
	 * @param payType
	 * @param payMethod
	 * @param status
	 * @param startAmount
	 * @param endAmount
	 * @param orderId
	 * @param orgId
	 * @return
	 */
	Map<String, Object> getOrderListByPageAndParamOrgId(int page, int pageSize,
			long startTime, long endTime, int payType, String payMethod,
			int status, BigDecimal startAmount, BigDecimal endAmount,
			String orderId,long orgId) throws Exception;
	
	
	/**
	 * 
	 * @Title: getOrderListByPageAndParamStoreNo
	 * @Description: 通过门店编号以及条件 查找
	 * @param page
	 * @param pageSize
	 * @param startTime
	 * @param endTime
	 * @param payType
	 * @param payMethod
	 * @param status
	 * @param startAmount
	 * @param endAmount
	 * @param orderId
	 * @param storeNo
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getOrderListByPageAndParamStoreNo(int page, int pageSize,
			long startTime, long endTime, int payType, String payMethod,
			int status, BigDecimal startAmount, BigDecimal endAmount,
			String orderId,StoreAccount storeAccount) throws Exception;
}
