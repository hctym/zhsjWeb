package com.zhsj.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhsj.model.Store;
import com.zhsj.model.StoreAccount;
import com.zhsj.model.StorePayInfo;
import com.zhsj.service.StoreService;
import com.zhsj.util.CommonResult;
import com.zhsj.util.SessionThreadLocal;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：商户门店信息
 * 类名称：com.zhsj.controller.StoreController     
 * 创建人：xulinchuang
 * 创建时间：2017年1月3日 上午10:06:54
 */
@RestController
@RequestMapping("store")
public class StoreController {

	@Autowired
	private StoreService storeService;
	/**
	 * 
	 * @Title: add
	 * @Description: 添加商户门店信息
	 * @param store
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Object add(Store store,long orgId){
		try {
			int code = storeService.add(store,orgId);
			return CommonResult.success("success",code);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");	
		}
	}
	
	/**
	 * 
	 * @Title: getById
	 * @Description: TODO
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getById")
	public Object getById(long id){
		try {
			Store store = storeService.getById(id);
			return CommonResult.success("success", store);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: update
	 * @Description: 更新
	 * @param store
	 * @return
	 */
	@RequestMapping(value="update")
	public Object update(Store store){
		try {
			storeService.update(store);
			return CommonResult.success("success");
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: add
	 * @Description: 添加商户门店信息
	 * @param store
	 * @return
	 */
	@RequestMapping(value="addc",method=RequestMethod.POST)
	public Object addc(Store store,HttpServletRequest request){
		try {
//			StoreAccount storeAccount = (StoreAccount) request.getSession().getAttribute("user");
			Map<String, Object> map = SessionThreadLocal.getSession();
			StoreAccount storeAccount = (StoreAccount) map.get("user");
			int code = storeService.addc(store,storeAccount.getId());
			return CommonResult.success("success",code);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");	
		}
	}
	
	/**
	 * 
	 * @Title: getList
	 * @Description: 查询门店列表
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="getList")
	public Object getList(int page,int pageSize){
		try {
			List<Store> list = storeService.getListBy(page,pageSize);
			return CommonResult.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getListByOrgId
	 * @Description: 通过组织id 查询该用户能看到的storelist  分页
	 * @param page
	 * @param pageSize
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value="getListByOrgId")
	public Object getListByOrgId(int page,int pageSize,long orgId,int status){
		try {
			Map<String, Object> map = storeService.getListByOrgIdAndPage(page,pageSize,orgId,status);
			return CommonResult.success("success", map);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getListByStoreNo
	 * @Description: 根据门店编号查找 门店以及子门店
	 * @param page
	 * @param pageSize
	 * @param status
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getListByStoreNo")
	public Object getListByStoreNo(int page,int pageSize,int status,
			HttpServletRequest request){
		try {
//			StoreAccount storeAccount = (StoreAccount) request.getSession().getAttribute("user");
			Map<String, Object> sessionMap = SessionThreadLocal.getSession();
			StoreAccount storeAccount = (StoreAccount) sessionMap.get("user");
			Map<String, Object> map = storeService.getListByStoreNo(page,pageSize,storeAccount.getId(),status);
			return CommonResult.success("success", map);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	
	/**
	 * 
	 * @Title: addPayInfo
	 * @Description: 添加支付方式
	 * @param storePayInfo
	 * @return
	 */
	@RequestMapping(value="addPayInfo",method=RequestMethod.POST)
	public Object addPayInfo(StorePayInfo storePayInfo){
		try {
			int code = storeService.addPayInfo(storePayInfo);
			return CommonResult.success("success", code);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getPayInfoListByStoreNo
	 * @Description: 获取支付方式list通过StoreNo
	 * @return
	 */
	@RequestMapping(value="getPayInfoListByStoreNo")
	public Object getPayInfoListByStoreNo(){
		try {
			List<StorePayInfo> list = storeService.getPayInfoListByStoreNo();
			return CommonResult.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: updateStorePayInfo
	 * @Description: 更新支付方式
	 * @param storePayInfo
	 * @return
	 */
	@RequestMapping(value="updateStorePayInfo",method = RequestMethod.POST)
	public Object updateStorePayInfo(StorePayInfo storePayInfo){
		try {
			int code = storeService.updatePayInfo(storePayInfo);
			return CommonResult.success("success", code);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getPayInfoByPayInfoId
	 * @Description: 通过支付方式的id 查询支付方式 用于展示  编辑
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getPayInfoById")
	public Object getPayInfoByPayInfoId(int id){
		try {
			StorePayInfo storePayInfo = storeService.getPayInfoByPayInfoId(id);
			return CommonResult.success("success", storePayInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
}
