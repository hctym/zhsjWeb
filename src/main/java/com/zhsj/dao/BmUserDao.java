package com.zhsj.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.BmUser;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：
 * 类名称：com.zhsj.dao.BmUserDao     
 * 创建人：xulinchuang
 * 创建时间：2016年12月7日 上午9:39:47
 */
public interface BmUserDao {
    /**
     * 
     * @Title: getBmUserByName
     * @Description: 根据账户查询用户
     * @param account
     * @return
     */
	BmUser getBmUserByName(@Param("account")String account);
	
	BmUser getBmUser(@Param("account")String account,@Param("password")String password);
	
	BmUser getBmUserById(@Param("id")int id);
	
	List<BmUser> getBmUserList(Map<String,Object> map);
	
	int insert(BmUser bmUser);
	
	int update(BmUser bmUser);
	
	int delete(@Param("id")int id);
	
}
