package com.zhsj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhsj.dao.AccountBindOrgDao;
import com.zhsj.dao.AccountBindRoleDao;
import com.zhsj.dao.AccountDao;
import com.zhsj.model.Account;
import com.zhsj.model.AccountBindOrg;
import com.zhsj.model.AccountBindRole;
import com.zhsj.model.Role;
import com.zhsj.service.AccountService;
import com.zhsj.util.Md5;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private AccountBindRoleDao accountBindRoleDao;
	@Autowired
	private AccountBindOrgDao accountBindOrgDao;
	
	/**
	 * 
	 * @see com.zhsj.service.AccountService#add(com.zhsj.model.Account)
	 */
	@Transactional
	@Override
	public int add(Account account,int roleId,int orgId) throws Exception {
		account.setPassword(Md5.encrypt(account.getPassword()));//密码加密
		account.setStatus(1);// '账户状态：0.禁用 ,1.启用'
		account.setValid(1);// '1有效,0无效'
		account.setUtime(System.currentTimeMillis()/1000);
		account.setCtime(System.currentTimeMillis()/1000);
	    accountDao.add(account);
		AccountBindRole accountBindRole = new AccountBindRole();
		accountBindRole.setAccountId(account.getId());
		accountBindRole.setRoleId(roleId);
		accountBindRole.setValid(1);
		accountBindRole.setCtime(System.currentTimeMillis()/1000);
		accountBindRole.setUtime(System.currentTimeMillis()/1000);
		accountBindRoleDao.add(accountBindRole);
		AccountBindOrg accountBindOrg = new AccountBindOrg();
		accountBindOrg.setAccountId(account.getId());
		accountBindOrg.setOrgId(orgId);
		accountBindOrg.setValid(1);
		accountBindOrg.setCtime(System.currentTimeMillis()/1000);
		accountBindOrg.setUtime(System.currentTimeMillis()/10000);
		int arcode =  accountBindOrgDao.add(accountBindOrg);
		return arcode;
	}
    /**
     * 
     * @see com.zhsj.service.AccountService#getByNameAndPassword(java.lang.String, java.lang.String)
     */
	@Override
	public int getByNameAndPassword(String accountName, String password) throws Exception{
		Account account  = accountDao.getByName(accountName);
		//如果用户不存在，返回0.否则判断密码；如果密码相等。返回1；不相等，返回2
		return account != null?(password.equals(Md5.encrypt(account.getPassword()))?1:2):0;
	}
	/**
	 * 
	 * @see com.zhsj.service.AccountService#getByName(java.lang.String)
	 */
	@Override
	public Account getByName(String accountName) throws Exception {
		return accountDao.getByName(accountName);
	}
	/**
	 * 
	 * @see com.zhsj.service.AccountService#getByNameAndMd5Password(java.lang.String, java.lang.String)
	 */
	@Override
	public Account getByNameAndMd5Password (String username, String md5password) throws Exception {
		return accountDao.getByNameAndMd5Password(username,md5password);
	}
	/**
	 * 
	 * @see com.zhsj.service.AccountService#getRole(int)
	 */
	@Override
	public Role getRole(int id) throws Exception {
		return accountDao.getRole(id);
	}
	/**
	 * 
	 * @see com.zhsj.service.AccountService#getList(int, int)
	 */
	@Override
	public Map<String, Object> getList(int page, int pageSize,long orgId) throws Exception {
		List<Account> list = accountDao.getListByPage((page-1)*pageSize,pageSize,orgId);
		int count = accountDao.getCount(orgId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("count", count);
		return map;
	}

}
