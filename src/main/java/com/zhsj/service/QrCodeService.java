package com.zhsj.service;


import com.zhsj.model.StoreQrcode;

public interface QrCodeService {

	Object CreateQrCode(StoreQrcode storeQrcode);
	
	Object getListByPage(int page,int pageSize);
	
	Object getListByNog(int[] nog);
}
