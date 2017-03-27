package com.zhsj.service;

import java.util.List;
import java.util.Map;

import com.zhsj.model.Discount;
import com.zhsj.model.DiscountRule;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：优惠接口
 * 类名称：com.zhsj.service.DiscountService     
 * 创建人：xulinchuang
 * 创建时间：2017年1月5日 上午11:59:09
 */
public interface DiscountService {
    /**
     * 
     * @Title: add
     * @Description:通过优惠 以及  关联优惠和商户门店编号的关联
     * @param discount
     * @param storeNo
     * @return
     * @throws Exception
     */
	int add(Discount discount,String storeNo) throws Exception;

	/**
	 * 
	 * @Title: getListByPage
	 * @Description: 通过商户门店编号。查询该商户的优惠列表 分页
	 * @param page
	 * @param pageSize
	 * @param storeNo
	 * @return
	 */
	Map<String, Object> getListByPage(int page, int pageSize, String storeNo) throws Exception;

	/**
	 * 
	 * @Title: addRule
	 * @Description: 为优惠添加规则
	 * @param discountRule
	 * @return
	 */
	int addRule(DiscountRule discountRule) throws Exception;
    /**
     * 
     * @Title: getRuleListByDiscountId
     * @Description: 通过优惠id查询优惠的规则
     * @param discountId
     * @return
     */
	List<DiscountRule> getRuleListByDiscountId(int discountId) throws Exception;
	
	/**
	 * 
	 * @Title: addDiscountAndRules
	 * @Description: 添加优惠规则
	 * @param name
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @param rule
	 * @param storeNos  
	 * @param aStyle
	 * @param payStyle 
	 * @return
	 * @throws Exception
	 */
	Object addDiscountAndRules(String name,String startTime,String endTime,int type,
			String rule,String[] storeNos,int aStyle,String payStyle,String sumPlanAmount) throws Exception;
    
	Map<String,Object> getListByParam(int startTime, int endTime, int status, int type,
			String name, int page, int pageSize) throws Exception;

	Object del(int discountId) throws Exception;

	Object update(int discountId) throws Exception;
	
}
