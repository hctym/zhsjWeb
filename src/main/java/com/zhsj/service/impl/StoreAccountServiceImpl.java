package com.zhsj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhsj.dao.StoreAccountBindRoleDao;
import com.zhsj.dao.StoreAccountDao;
import com.zhsj.dao.StoreBindAccountDao;
import com.zhsj.model.Role;
import com.zhsj.model.StoreAccount;
import com.zhsj.model.StoreAccountBindRole;
import com.zhsj.model.StoreBindAccount;
import com.zhsj.service.StoreAccountService;
import com.zhsj.util.Md5;

@Service
public class StoreAccountServiceImpl implements StoreAccountService {

	@Autowired
	private StoreAccountDao storeAccountDao;
	@Autowired
	private StoreBindAccountDao storeBindAccountDao;
	@Autowired
	private StoreAccountBindRoleDao storeAccountBindRoleDao;
	
	/**
	 * 
	 * @see com.zhsj.service.StoreAccountService#getByName(java.lang.String)
	 */
	@Override
	public StoreAccount getByName(String storeAccountName) throws Exception {
		StoreAccount storeAccount = storeAccountDao.getByName(storeAccountName);
		if(storeAccount != null){
			List<StoreAccountBindRole> list = storeAccountBindRoleDao.getListByAccountId(storeAccount.getId());
			storeAccount.setStoreAccountBindRoles(list);
		}
		return storeAccount;
	}

	/**
	 * 
	 * @see com.zhsj.service.StoreAccountService#getByNameAndMd5PassWord(java.lang.String, java.lang.String)
	 */
	@Override
	public StoreAccount getByNameAndMd5PassWord(String username,
			String md5password) {
		StoreAccount storeAccount = storeAccountDao.getByNameAndMd5PassWord(username,md5password);
		List<StoreAccountBindRole> list = storeAccountBindRoleDao.getListByAccountId(storeAccount.getId());
		storeAccount.setStoreAccountBindRoles(list);
		return storeAccount;
	}

	/**
	 * 
	 * @see com.zhsj.service.StoreAccountService#add(com.zhsj.model.StoreAccount, java.lang.String)
	 */
	@Transactional
	@Override
	public int add(StoreAccount storeAccount, String storeNo,int roleId) throws Exception {
		storeAccount.setPassword(Md5.encrypt(storeAccount.getPassword()));
		storeAccount.setStatus(1);
		storeAccount.setValid(1);
		storeAccount.setCtime(System.currentTimeMillis()/1000);
		storeAccount.setUtime(System.currentTimeMillis()/1000);
		storeAccountDao.add(storeAccount);
		StoreBindAccount sba = new StoreBindAccount();
		sba.setStoreNo(storeNo);
		sba.setStoreAccountId(storeAccount.getId());
		sba.setStatus(1);//0 无效 1 有效 
		sba.setCtime(System.currentTimeMillis()/1000);
		sba.setUtime(System.currentTimeMillis()/1000);
		storeBindAccountDao.add(sba);
		StoreAccountBindRole storeAccountBindRole = new StoreAccountBindRole();
		storeAccountBindRole.setAccountId(storeAccount.getId());
		storeAccountBindRole.setRoleId(roleId);
		storeAccountBindRole.setValid(1);
		storeAccountBindRole.setCtime(System.currentTimeMillis()/1000);
		storeAccountBindRole.setUtime(System.currentTimeMillis()/1000);
		int code = storeAccountBindRoleDao.add(storeAccountBindRole);
		return code;
	}
    /**
     * 
     * @see com.zhsj.service.StoreAccountService#getList(java.lang.String, int, int)
     */
	@Override
	public List<StoreAccount> getList(String storeNo, int page, int pageSize)
			throws Exception {
		return storeAccountDao.getList(storeNo,(page-1) * pageSize,pageSize);
	}
   /**
    * 
    * @see com.zhsj.service.StoreAccountService#getRole(int)
    */
	@Override
	public Role getRole(int id) throws Exception {
		// TODO Auto-generated method stub
		return storeAccountDao.getRole(id);
	}
    /**
     * 
     * @see com.zhsj.service.StoreAccountService#getSbaByAccountId(long)
     */
	@Override
	public StoreBindAccount getSbaByAccountId(long id) throws Exception {
		return storeBindAccountDao.getByAccountId(id);
	}

}
