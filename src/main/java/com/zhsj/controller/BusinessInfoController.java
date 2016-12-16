package com.zhsj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhsj.model.BusinessInfo;
import com.zhsj.service.BusinessInfoService;
import com.zhsj.util.CommonResult;

@RestController
@RequestMapping("businessInfo")
public class BusinessInfoController {

	@Autowired
	private BusinessInfoService businessInfoService;
	/**
	 * 
	 * @Title: getBusinessInfoById
	 * @Description:通过主键id获取商家信息详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getById",method=RequestMethod.POST)
	public Object getBusinessInfoById(int id){
		try {
			BusinessInfo bi = businessInfoService.getBusinessInfoById(id);
			return CommonResult.success("查询成功", bi);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("查询失败");
		}
	}
}
