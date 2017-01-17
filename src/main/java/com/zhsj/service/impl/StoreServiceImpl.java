package com.zhsj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhsj.dao.OrgDao;
import com.zhsj.dao.StoreBindAccountDao;
import com.zhsj.dao.StoreBindOrgDao;
import com.zhsj.dao.StoreDao;
import com.zhsj.model.Org;
import com.zhsj.model.Store;
import com.zhsj.model.StoreBindAccount;
import com.zhsj.model.StoreBindOrg;
import com.zhsj.service.StoreService;
import com.zhsj.util.NoUtil;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreDao storeDao;
	@Autowired
	private StoreBindOrgDao storeBindOrgDao;
	@Autowired
	private OrgDao orgDao;
	@Autowired
	private StoreBindAccountDao storeBindAccountDao;
	
	/**
	 * 
	 * @see com.zhsj.service.StoreService#add(com.zhsj.model.Store)
	 */
	@Override
	public int add(Store store,long orgId) throws Exception{
		Org org = orgDao.getOrgById(orgId);
		store.setOrgIds(org.getOrgIds()+","+orgId);//
		store.setStoreNo(NoUtil.getStoreNO());
		store.setStatus(1);//1 正常 2：下线
		store.setValid(1);//0 无效 1：有效
		store.setUtime(System.currentTimeMillis()/1000);
		store.setCtime(System.currentTimeMillis()/1000);
		StoreBindOrg storeBindOrg = new StoreBindOrg();
		storeBindOrg.setOrgId(orgId);
		storeBindOrg.setOrgIds(org.getOrgIds()+","+orgId);//
		storeBindOrg.setStoreNo(store.getStoreNo());
		storeBindOrg.setValid(1);
		storeBindOrg.setCtime(System.currentTimeMillis()/1000);
		storeBindOrg.setUtime(System.currentTimeMillis()/1000);
		storeBindOrgDao.add(storeBindOrg);
		return storeDao.add(store);
	}
	/**
	 * 
	 * @see com.zhsj.service.StoreService#addc(com.zhsj.model.Store, long)
	 */
	@Override
	public int addc(Store store, long id) {
		StoreBindAccount sba = storeBindAccountDao.getByAccountId(id);
		Store pStore = storeDao.getByStoreNo(sba.getStoreNo());
		store.setOrgIds(pStore.getOrgIds());//
		store.setParentNo(sba.getStoreNo());
		store.setStoreNo(NoUtil.getStoreNO());
		store.setStatus(1);//1 正常 2：下线
		store.setValid(1);//0 无效 1：有效
		store.setUtime(System.currentTimeMillis()/1000);
		store.setCtime(System.currentTimeMillis()/1000);
		StoreBindOrg sbOrg = storeBindOrgDao.getByStoreNo(sba.getStoreNo());
		StoreBindOrg storeBindOrg = new StoreBindOrg();
		storeBindOrg.setOrgId(sbOrg.getOrgId());
		storeBindOrg.setOrgIds(pStore.getOrgIds());//
		storeBindOrg.setStoreNo(store.getStoreNo());
		storeBindOrg.setValid(1);
		storeBindOrg.setCtime(System.currentTimeMillis()/1000);
		storeBindOrg.setUtime(System.currentTimeMillis()/1000);
		storeBindOrgDao.add(storeBindOrg);
		return storeDao.add(store);
	}
	
    /**
     * 
     * @see com.zhsj.service.StoreService#getListBy(int, int)
     */
	@Override
	public List<Store> getListBy(int page, int pageSize) throws Exception{
		List<Store> stores = storeDao.getListBy((page-1)*pageSize,pageSize);
		return stores;
	}
	/**
	 * 
	 * @see com.zhsj.service.StoreService#getListByOrgIdAndPage(int, int, long)
	 */
	@Override
	public Map<String, Object> getListByOrgIdAndPage(int page, int pageSize,
			long orgId,int status) throws Exception{
		Org org = orgDao.getOrgById(orgId);
		String orgIds = org.getOrgIds()+","+orgId;
		List<Store> stores = storeDao.getListByOrgIdAndPage(orgIds,
				(page-1)*pageSize,pageSize,status);
		int count = storeDao.getCount(orgIds,status);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", stores);
		map.put("count", count);
		return map;
	}
	/**
	 * 
	 * @see com.zhsj.service.StoreService#getListByStoreNo(int, int, long)
	 */
	@Override
	public Map<String, Object> getListByStoreNo(int page, int pageSize,
			long  storeAccountId,int status) throws Exception {
		StoreBindAccount sba = storeBindAccountDao.getByAccountId(storeAccountId);
		List<Store> stores = storeDao.getListByStoreNoAndPage((page-1)*pageSize,pageSize,sba.getStoreNo(),status);
		int count =storeDao.getCountByStoreNo(sba.getStoreNo(),status);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("list", stores);
		map.put("count", count);
		return map;
	}
	/**
	 * 
	 * @see com.zhsj.service.StoreService#deleteById(long)
	 */
	@Override
	public void deleteById(long id) {
		
		storeDao.deleteById(id);
	}
	/**
	 * 
	 * @see com.zhsj.service.StoreService#getById(long)
	 */
	@Override
	public Store getById(long id) throws Exception {
		return storeDao.getById(id);
	}
	/**
	 * 
	 * @see com.zhsj.service.StoreService#update(com.zhsj.model.Store)
	 */
	@Override
	public int update(Store store) throws Exception {
		store.setUtime(System.currentTimeMillis()/1000);
		return storeDao.update(store);
	}
	

}
