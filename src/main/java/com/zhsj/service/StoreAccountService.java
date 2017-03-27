package com.zhsj.service;

import java.util.List;

import com.zhsj.model.Role;
import com.zhsj.model.StoreAccount;
import com.zhsj.model.StoreBindAccount;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：门店账户
 * 类名称：com.zhsj.service.StoreAccountService     
 * 创建人：xulinchuang
 * 创建时间：2016年12月30日 下午3:11:26
 */
public interface StoreAccountService {

	/**
	 * 
	 * @Title: getByName
	 * @Description: 通过门店账户名称 获取账户信息
	 * @param storeAccountName
	 * @return
	 * @throws Exception
	 */
	StoreAccount getByName(String storeAccountName) throws Exception;

	/**
	 * 
	 * @Title: getByNameAndMd5PassWord
	 * @Description: 通过门店用户和加密的密码查询门店
	 * @param username
	 * @param md5password
	 * @return
	 */
	StoreAccount getByNameAndMd5PassWord(String username, String md5password) throws Exception;
    /**
     * 
     * @Title: add
     * @Description: 添加商户门店账户以及关联门店信息
     * @param storeAccount
     * @param storeNo
     * @param roleId
     * @return
     */
	int add(StoreAccount storeAccount, String storeNo, int roleId) throws Exception;
    /**
     * 
     * @Title: getList
     * @Description: TODO
     * @param storeNo
     * @param page
     * @param pageSize
     * @return
     */
	List<StoreAccount> getList(String storeNo, int page, int pageSize) throws Exception;
    /**
     * 
     * @Title: getRole
     * @Description: 通过商户门店用户的id 查询用户的角色
     * @param id
     * @return
     */
	Role getRole(int id) throws Exception; 
	/**
	 * 
	 * @Title: getSbaByAccountId
	 * @Description: 通过accountID  获取store和account的关联信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	StoreBindAccount getSbaByAccountId(long id) throws Exception;
}
