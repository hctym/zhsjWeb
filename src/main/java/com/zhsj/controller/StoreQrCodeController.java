package com.zhsj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sun.util.logging.resources.logging;

import com.zhsj.model.StoreQrcode;
import com.zhsj.service.QrCodeService;

@RestController
@RequestMapping(value="qrcode")
public class StoreQrCodeController {
	
	private Logger logger = LoggerFactory.getLogger(StoreQrCodeController.class);
	
	@Autowired
	private QrCodeService qrCodeService;

	@RequestMapping(value="create",method = {RequestMethod.POST,RequestMethod.GET})
	public Object createQrCode(StoreQrcode storeQrcode){
		return qrCodeService.CreateQrCode(storeQrcode);
	}
	
	@RequestMapping(value="getList",method = {RequestMethod.POST,RequestMethod.GET})
	public Object getList(int page,int pageSize){
		return qrCodeService.getListByPage(page, pageSize);
	}
	
	@RequestMapping(value="nog",method = {RequestMethod.POST,RequestMethod.GET})
	public Object getListByNog(int[] nogIds){
		logger.info("#StoreQrCodeController.getListByNog #nog={}",nogIds);
		return qrCodeService.getListByNog(nogIds);
	}
}
