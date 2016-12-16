package com.zhsj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhsj.model.BusinessInfo;
import com.zhsj.service.BusinessInfoService;
import com.zhsj.util.CommonResult;

@Controller
@RequestMapping("shop")
public class ShopController {

	@Autowired
	private BusinessInfoService businessInfoService;
	/**
	 * 
	 * @Title: addShop
	 * @Description: TODO
	 * @param bInfo
	 * @return
	 */
	@RequestMapping(value = "add",method = RequestMethod.POST)
	@ResponseBody
	public Object addShop(BusinessInfo bInfo){
		try {
			int id  = businessInfoService.insert(bInfo);
			return CommonResult.success("添加门店成功", id);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("添加门店失败");
		}
	}
	/**
	 * 
	 * @Title: getList
	 * @Description: 获取门店的列表
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "getList")
	@ResponseBody
	public Object getList(int page,int pageSize){
		try {
			List<BusinessInfo> list = businessInfoService.getList(page, pageSize);
			return CommonResult.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("获取门店列表失败");
		}
	}
}
