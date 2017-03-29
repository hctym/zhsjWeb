package com.zhsj.util;

import java.util.Map;

import com.zhsj.model.Account;
import com.zhsj.model.StoreAccount;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：session的threadLocal
 * 类名称：com.zhsj.util.SessionThreadLocal     
 * 创建人：xulinchuang
 * 创建时间：2017年1月18日 上午10:13:49
 */
public class SessionThreadLocal {

	private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();
	/**
	 * 
	 * @Title: setSession
	 * @Description: 存放
	 * @param map
	 */
	public static void setSession(Map<String, Object> map){
		threadLocal.set(map);
	}
	/**
	 * 
	 * @Title: getSession
	 * @Description: 获取
	 * @return
	 */
	public static Map<String, Object> getSession(){
		return threadLocal.get();
	}
	//账户
	public static Account getAccount(){
		Map<String, Object> map  = getSession();
		Account account = null;
		if("account".equals(map.get("flag"))){
			account = (Account) map.get("user");
		}
		return account;
	}
	//商户号
	public static StoreAccount getStoreAccount(){
		Map<String, Object> map = getSession();
		StoreAccount storeAccount = null;
		if("storeAccount".equals(map.get("flag"))){
			storeAccount = (StoreAccount) map.get("user");
		}
		return storeAccount;
	}
}
