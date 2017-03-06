package com.zhsj.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhsj.model.Discount;
import com.zhsj.model.DiscountRule;
import com.zhsj.service.DiscountService;
import com.zhsj.util.CommonResult;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：优惠
 * 类名称：com.zhsj.controller.DiscountController     
 * 创建人：xulinchuang
 * 创建时间：2017年1月5日 上午10:16:57
 */
@RestController
@RequestMapping("discount")
public class DiscountController {

	@Autowired
	private DiscountService discountService;
	
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Object add(Discount discount,String storeNo){
		try {
			int code = discountService.add(discount, storeNo);
			return CommonResult.success("success", code);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getListByPageAndStoreNo
	 * @Description: 通过storeNo 查询优惠 分页
	 * @param page
	 * @param pageSize
	 * @param storeNo
	 * @return
	 */
	@RequestMapping(value="getListByPage")
	public Object getListByPageAndStoreNo(int page,int pageSize,String storeNo){
		try {
			Map<String, Object> map = discountService.getListByPage(page,pageSize,storeNo);
			return CommonResult.success("success", map);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: addRule
	 * @Description: 为优惠添加规则
	 * @param discountRule
	 * @return
	 */
	@RequestMapping(value="addRule",method=RequestMethod.POST)
	public Object addRule(DiscountRule discountRule){
		try {
			int code = discountService.addRule(discountRule);
			return CommonResult.success("successs", code);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getRuleListByDiscountId
	 * @Description: 通过优惠id 查询规则
	 * @param discountId
	 * @return
	 */
	@RequestMapping(value="getRuleListByDiscountId")
	public Object getRuleListByDiscountId(int discountId){
		try {
			List<DiscountRule> list = discountService.getRuleListByDiscountId(discountId);
			return CommonResult.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	
	
	@RequestMapping(value="addDiscountAndRules")
	public Object addDiscountAndRules(String name,String startTime,String endTime,
			int type,String rules,String[] storeNos,int aStyle,String payStyle,String sumPlanAmount){
		try {
			return discountService.addDiscountAndRules(name, startTime, endTime, type, rules, storeNos,aStyle,payStyle,sumPlanAmount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CommonResult.defaultError("系统错误");
	}
	
	@RequestMapping(value="getListByParam")
	public Object getListByParam(int startTime,int endTime,int status,int type,String name,int page,int pageSize){
		
		try {
			Map<String, Object> map = discountService.getListByParam(startTime,endTime,status,type,name,page,pageSize);
			return CommonResult.success("", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CommonResult.defaultError("系统错误");
	}
	
	@RequestMapping(value="del")
	public Object del(int discountId){
		try {
			return discountService.del(discountId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="update")
	public Object update(int discountId){
		try {
			return discountService.update(discountId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
