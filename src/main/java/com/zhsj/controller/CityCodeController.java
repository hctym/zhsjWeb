package com.zhsj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhsj.model.CityCode;
import com.zhsj.service.CityCodeService;
import com.zhsj.util.CommonResult;

@RestController
@RequestMapping("city")
public class CityCodeController {

	@Autowired
	private CityCodeService cityCodeService;
	
	@RequestMapping(value="getListByCode")
	public Object getListByCode(String code){
		try {
			List<CityCode> cityCodes = cityCodeService.getListByCode(code);
			return CommonResult.success("success", cityCodes);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
}
