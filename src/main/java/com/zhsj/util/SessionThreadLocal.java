package com.zhsj.util;

import java.util.Map;
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
}
