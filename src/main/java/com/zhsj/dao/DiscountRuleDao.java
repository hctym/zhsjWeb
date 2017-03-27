package com.zhsj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.DiscountRule;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：优惠添加规则
 * 类名称：com.zhsj.dao.DiscountRuleDao     
 * 创建人：xulinchuang
 * 创建时间：2017年1月6日 上午10:31:15
 */
public interface DiscountRuleDao {
    
	int add(DiscountRule discountRule);
	/**
	 * 
	 * @Title: getListByDiscountId
	 * @Description: 通过优惠id 查询规则
	 * @param discountId
	 * @return
	 */
	List<DiscountRule> getListByDiscountId(int discountId);
	
	/**
	 * 
	 * @Title: addMore
	 * @Description: TODO
	 * @param beans
	 * @return
	 */
	int addMore(@Param("beans")List<DiscountRule> beans);
}
