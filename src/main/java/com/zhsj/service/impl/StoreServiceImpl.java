package com.zhsj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhsj.dao.CityCodeDao;
import com.zhsj.dao.OrgDao;
import com.zhsj.dao.SequenceDao;
import com.zhsj.dao.StoreBindAccountDao;
import com.zhsj.dao.StoreBindOrgDao;
import com.zhsj.dao.StoreDao;
import com.zhsj.dao.StorePayInfoDao;
import com.zhsj.model.CityCode;
import com.zhsj.model.Org;
import com.zhsj.model.Store;
import com.zhsj.model.StoreAccount;
import com.zhsj.model.StoreBindAccount;
import com.zhsj.model.StoreBindOrg;
import com.zhsj.model.StorePayInfo;
import com.zhsj.service.StoreService;
import com.zhsj.util.SessionThreadLocal;
import com.zhsj.util.StoreUtils;

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
	@Autowired
	private CityCodeDao cityCodeDao;
	@Autowired
	private StorePayInfoDao storePayInfoDao;
	@Autowired
	private SequenceDao sequenceDao;
	/**
	 * 
	 * @see com.zhsj.service.StoreService#add(com.zhsj.model.Store)
	 */
	@Override
	public int add(Store store,long orgId) throws Exception{
		Org org = orgDao.getOrgById(orgId);
		store.setOrgIds(org.getOrgIds()+","+orgId);//
		store.setStoreNo(StoreUtils.getStoreNO(sequenceDao.getCode()));
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
		store.setStoreNo(StoreUtils.getStoreNO(sequenceDao.getCode()));
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
		Store store = storeDao.getById(id);
		CityCode cityCode = cityCodeDao.getBycode(String.valueOf(store.getCityCode()));
		store.setCc(cityCode);
		return store;
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
	/**
	 * 
	 * @see com.zhsj.service.StoreService#addPayInfo(com.zhsj.model.StorePayInfo)
	 */
	@Override
	public int addPayInfo(StorePayInfo storePayInfo) throws Exception {
		Map<String, Object> map = SessionThreadLocal.getSession();
		StoreAccount storeAccount = (StoreAccount) map.get("user");
		StoreBindAccount storeBindAccount = storeBindAccountDao.getByAccountId(storeAccount.getId());
		List<StorePayInfo> list = storePayInfoDao.getListByStoreNo(storeBindAccount.getStoreNo());
		
		if(list.size() > 0){//只能添加一个支付方式
			return 2;
		}
		storePayInfo.setStoreNo(storeBindAccount.getStoreNo());
		storePayInfo.setStatus(1);
		storePayInfo.setValid(1);
		storePayInfo.setUtime(System.currentTimeMillis()/1000);
		storePayInfo.setCtime(System.currentTimeMillis()/1000);
	    int code = storePayInfoDao.add(storePayInfo);
		return code;
	}
	/**
	 * 
	 * @see com.zhsj.service.StoreService#getPayInfoListByStoreNo(java.lang.String)
	 */
	@Override
	public List<StorePayInfo> getPayInfoListByStoreNo()
			throws Exception {
		Map<String, Object> map = SessionThreadLocal.getSession();
		StoreAccount storeAccount = (StoreAccount) map.get("user");
		StoreBindAccount storeBindAccount = storeBindAccountDao.getByAccountId(storeAccount.getId());
		List<StorePayInfo> list = storePayInfoDao.getListByStoreNo(storeBindAccount.getStoreNo());
		return list;
	}
	/**
	 * 
	 * @see com.zhsj.service.StoreService#updatePayInfo(com.zhsj.model.StorePayInfo)
	 */
	@Override
	public int updatePayInfo(StorePayInfo storePayInfo) throws Exception {
		storePayInfo.setUtime(System.currentTimeMillis()/1000);
		return storePayInfoDao.update(storePayInfo);
	}
	/**
	 * 
	 * @see com.zhsj.service.StoreService#getPayInfoByPayInfoId(int)
	 */
	@Override
	public StorePayInfo getPayInfoByPayInfoId(int id) throws Exception {
		return storePayInfoDao.getById(id);
	}

}
