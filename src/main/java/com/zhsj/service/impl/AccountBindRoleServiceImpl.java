package com.zhsj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhsj.dao.AccountBindRoleDao;
import com.zhsj.model.AccountBindRole;
import com.zhsj.service.AccountBindRoleService;

@Service
public class AccountBindRoleServiceImpl implements AccountBindRoleService {

	@Autowired
	private AccountBindRoleDao accountBindRoleDao;
	/**
	 * 
	 * @see com.zhsj.service.AccountBindRoleService#addOrUpdate(int, int[])
	 */
	@Transactional
	@Override
	public int addOrUpdate(int accountId, int[] roleIds) throws Exception {
		accountBindRoleDao.deleteAllByAccountId(accountId);
		int count = 0;
		for(int i:roleIds){
			AccountBindRole abRole = new AccountBindRole();
			abRole.setAccountId(accountId);
			abRole.setRoleId(i);
			abRole.setValid(1);
			abRole.setUtime(System.currentTimeMillis()/1000);
			abRole.setCtime(System.currentTimeMillis()/1000);
			accountBindRoleDao.add(abRole);
			count++;
		}
		return count;
	}

}
