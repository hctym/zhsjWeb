package com.zhsj.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhsj.dao.OrderDao;
import com.zhsj.dao.OrgDao;
import com.zhsj.dao.StoreBindAccountDao;
import com.zhsj.dao.StoreDao;
import com.zhsj.dao.UserDao;
import com.zhsj.model.Order;
import com.zhsj.model.Org;
import com.zhsj.model.Store;
import com.zhsj.model.StoreAccount;
import com.zhsj.model.StoreBindAccount;
import com.zhsj.model.User;
import com.zhsj.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrgDao orgDao;
	@Autowired
	private StoreBindAccountDao storeBindAccountDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private StoreDao storeDao;
	/**
	 * 
	 * @see com.zhsj.service.OrderService#add(com.zhsj.model.Order)
	 */
	@Override
	public int add(Order order) throws Exception {
		order.setCtime(System.currentTimeMillis()/1000);
		order.setUtime(System.currentTimeMillis()/1000);
		return orderDao.add(order);
	}
    /**
     * 
     * @see com.zhsj.service.OrderService#getListAndCountByPage(int, int)
     */
	@Override
	public Map<String, Object> getListAndCountByPage(int page, int pageSize)
			throws Exception {
		List<Order> list = orderDao.getListByPage((page-1)*pageSize, pageSize);
		int count = orderDao.getCount();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("count", count);
		return map;
	}
	/**
	 * 
	 * @see com.zhsj.service.OrderService#getOrderListByPageAndParamOrgId(int, int, long, long, int, java.lang.String, int, java.math.BigDecimal, java.math.BigDecimal, java.lang.String, long)
	 */
	@Override
	public Map<String, Object> getOrderListByPageAndParamOrgId(int page,
			int pageSize, long startTime, long endTime, int payType,
			String payMethod, int status, BigDecimal startAmount,
			BigDecimal endAmount,String orderId,long orgId) throws Exception {
		String orgIds = "";
		if(orgId != 0){
			Org org = orgDao.getOrgById(orgId);
		    orgIds = org.getOrgIds();
		}
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("start", (page-1)*pageSize);
		map.put("pageSize", pageSize);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("payType", payType);
		map.put("payMethod", payMethod);
		map.put("status", status);
		map.put("startAmount", startAmount);
		map.put("endAmount", endAmount);
		map.put("orderId", orderId);
		map.put("orgIds", orgIds);
		
		List<Order> list = orderDao.getOrderListByMapOrgId(map);
		Set<Long> userset = new HashSet<Long>();
		Set<String> storeNoSet = new HashSet<String>();
		for(Order order:list){
			userset.add(order.getUserId());
			storeNoSet.add(order.getStoreNo());
		}
		if(list.size() > 0){
			List<User> users = userDao.getByIds(userset.toArray());
			List<Store> stores = storeDao.getListBystoreNos(storeNoSet.toArray());
			for(Order order:list){
				for(int i= 0;i<users.size();i++){
					if(order.getUserId() == users.get(i).getId()){
						order.setUser(users.get(i));
						break;
					}
				}
				for(int j= 0;j<stores.size();j++){
					if(order.getStoreNo().equals(stores.get(j).getStoreNo())){
						order.setStore(stores.get(j));
						break;
					}
				}
			}
		}
		int count = orderDao.getCountByMapOrgId(map);
		Map<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put("list", list);
		resultMap.put("count", count);
		return resultMap;
	}
	/**
	 * 
	 * @see com.zhsj.service.OrderService#getOrderListByPageAndParamStoreNo(int, int, long, long, int, java.lang.String, int, java.math.BigDecimal, java.math.BigDecimal, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> getOrderListByPageAndParamStoreNo(int page,
			int pageSize, long startTime, long endTime, int payType,
			String payMethod, int status, BigDecimal startAmount,
			BigDecimal endAmount, String orderId, StoreAccount storeAccount) throws Exception {
		StoreBindAccount storeBindAccount = storeBindAccountDao.getByAccountId(storeAccount.getId());
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("start", (page-1)*pageSize);
		map.put("pageSize", pageSize);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("payType", payType);
		map.put("payMethod", payMethod);
		map.put("status", status);
		map.put("startAmount", startAmount);
		map.put("endAmount", endAmount);
		map.put("orderId", orderId);
		map.put("storeNo", storeBindAccount.getStoreNo());
		
		List<Order> list = orderDao.getOrderListByMapStoreNo(map);
		Set<Long> userset = new HashSet<Long>();
		Set<String> storeNoSet = new HashSet<String>();
		if(list.size() > 0){
			for(Order order:list){
				userset.add(order.getUserId());
				storeNoSet.add(order.getStoreNo());
			}
			List<User> users = userDao.getByIds(userset.toArray());
			List<Store> stores = storeDao.getListBystoreNos(storeNoSet.toArray());
			for(Order order:list){
				for(int i= 0;i<users.size();i++){
					if(order.getUserId() == users.get(i).getId()){
						order.setUser(users.get(i));
						break;
					}
				}
				for(int j= 0;j<stores.size();j++){
					if(order.getStoreNo().equals(stores.get(j).getStoreNo())){
						order.setStore(stores.get(j));
						break;
					}
				}
			}
		}
		int count = orderDao.getCountByMapStoreNo(map);
		Map<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put("list", list);
		resultMap.put("count", count);
		return resultMap;
	}

}
