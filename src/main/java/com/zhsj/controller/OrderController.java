package com.zhsj.controller;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhsj.model.Account;
import com.zhsj.model.AccountBindOrg;
import com.zhsj.model.Order;
import com.zhsj.model.StoreAccount;
import com.zhsj.service.OrderService;
import com.zhsj.util.CommonResult;
import com.zhsj.util.SessionThreadLocal;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：订单
 * 类名称：com.zhsj.controller.OrderController     
 * 创建人：xulinchuang
 * 创建时间：2017年1月4日 下午4:55:41
 */
@RestController
@RequestMapping("order")
public class OrderController {
    
	@Autowired
	private OrderService orderService;
	
	/**
	 * 
	 * @Title: add
	 * @Description: 添加订单
	 * @param order
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Object add(Order order){
		try {
			int code = orderService.add(order);
			return CommonResult.success("success", code);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getListByPage
	 * @Description: 分页查询
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="getListByPage")
	public Object getListByPage(int page,int pageSize){
		try {
			Map<String, Object> map = orderService.getListAndCountByPage(page, pageSize);
			return CommonResult.success("success",map);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getListByPageAndParamOrgId
	 * @Description: 通过组织查询 订单
	 * @param page
	 * @param pageSize
	 * @param startTime
	 * @param endTime
	 * @param payType
	 * @param payMethod
	 * @param status
	 * @param startAmount
	 * @param endAmount
	 * @param orderId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getListByPageAndParamOrgId")
	public Object getListByPageAndParamOrgId(int page,int pageSize,
			long startTime,long endTime,int payType,String payMethod,
			int status,BigDecimal startAmount,BigDecimal endAmount,String orderId,HttpServletRequest request){
		try {
			Map<String, Object> sessionMap = SessionThreadLocal.getSession();
			Account account = (Account) sessionMap.get("user");
			AccountBindOrg accountBindOrg = account.getAccountBindOrg();
			Map<String, Object> map = orderService.getOrderListByPageAndParamOrgId(page,pageSize,
					startTime,endTime,payType,payMethod,status,startAmount,endAmount,orderId,accountBindOrg.getOrgId());
			return CommonResult.success("success", map);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getListByPageAndParamStoreNo
	 * @Description: 门店编号 查找订单
	 * @param page
	 * @param pageSize
	 * @param startTime
	 * @param endTime
	 * @param payType
	 * @param payMethod
	 * @param status
	 * @param startAmount
	 * @param endAmount
	 * @param orderId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getListByPageAndParamStoreNo")
	public Object getListByPageAndParamStoreNo(int page,int pageSize,
			long startTime,long endTime,int payType,String payMethod,
			int status,BigDecimal startAmount,BigDecimal endAmount,String orderId,HttpServletRequest request){
		try {
//			StoreAccount storeAccount = (StoreAccount) request.getSession().getAttribute("user");
			Map<String, Object> sessionMap = SessionThreadLocal.getSession();
			StoreAccount storeAccount = (StoreAccount) sessionMap.get("user");
			Map<String, Object> map = orderService.getOrderListByPageAndParamStoreNo(page, pageSize,
					startTime, endTime, payType, payMethod, status, startAmount, endAmount, orderId, storeAccount);
			return CommonResult.success("success", map);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
}
