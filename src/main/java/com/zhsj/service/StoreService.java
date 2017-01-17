package com.zhsj.service;

import java.util.List;
import java.util.Map;

import com.zhsj.model.Store;

public interface StoreService {

	int add(Store store,long orgId) throws Exception;
    /**
     * 
     * @param pageSize 
     * @param page 
     * @Title: getListBy
     * @Description: 查询门店列表
     * @return
     */
	List<Store> getListBy(int page, int pageSize) throws Exception;
	/**
	 * 
	 * @Title: getListByOrgIdAndPage
	 * @Description: 通过组织id。查询门店
	 * @param page
	 * @param pageSize
	 * @param orgId
	 * @param status
	 * @return
	 */
	Map<String, Object> getListByOrgIdAndPage(int page, int pageSize, long orgId,int status) throws Exception;
	/**
	 * 
	 * @Title: getListByStoreNo
	 * @Description: 通过门店编号查找门店以及子门店
	 * @param page
	 * @param pageSize
	 * @param storeAccountId
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getListByStoreNo(int page, int pageSize, long  storeAccountId,int status) throws Exception;
	/**
	 * 
	 * @Title: addc
	 * @Description: 添加子门店
	 * @param store
	 * @param id  当期用户的账户id
	 * @return
	 */
	int addc(Store store, long id) throws Exception;
	/**
	 * 
	 * @Title: deleteById
	 * @Description: TODO
	 * @param id
	 */
	void deleteById(long id) throws Exception;
	/**
	 * 
	 * @Title: getById
	 * @Description: TODO
	 * @param id
	 * @return
	 */
	Store getById(long id) throws Exception;
	/**
	 * 
	 * @Title: update
	 * @Description: TODO
	 * @param store
	 * @return
	 */
	int update(Store store) throws Exception;
}
