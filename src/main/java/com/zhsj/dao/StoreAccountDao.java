package com.zhsj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.Role;
import com.zhsj.model.StoreAccount;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：门店账户dao
 * 类名称：com.zhsj.dao.StroreAccountDao     
 * 创建人：xulinchuang
 * 创建时间：2016年12月30日 上午11:15:55
 */
public interface StoreAccountDao {

	int add(StoreAccount storeAccount);
    /**
     * 
     * @Title: getByName
     * @Description: TODO
     * @param storeAccountName
     * @return
     */
	StoreAccount getByName(@Param("account")String account);
	/**
	 * 
	 * @Title: getByNameAndMd5PassWord
	 * @Description: 
	 * @param username
	 * @param md5password
	 * @return
	 */
	StoreAccount getByNameAndMd5PassWord(@Param("username")String username,
			@Param("md5password")String md5password);
	/**
	 * 
	 * @Title: getList
	 * @Description:通过商户门店编号查找所有账户
	 * @param storeNo
	 * @param i
	 * @param pageSize
	 * @return
	 */
	List<StoreAccount> getList(@Param("storeNo")String storeNo, 
			@Param("row")int row, @Param("pageSize")int pageSize);
	/**
	 * 
	 * @Title: getRole
	 * @Description: 通过商户门店用户id 查询角色  
	 * @param id
	 * @return
	 */
	Role getRole(@Param("id")int id);
}
