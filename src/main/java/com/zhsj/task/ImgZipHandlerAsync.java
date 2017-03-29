package com.zhsj.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhsj.dao.StoreQrcodeDao;
import com.zhsj.util.ConfigUtil;
import com.zhsj.util.FileUtils;
import com.zhsj.util.ImageUtils;
import com.zhsj.util.Qrcode;
import com.zhsj.util.SpringBeanUtil;
/**
 * 图片异步处理
 */
public class ImgZipHandlerAsync implements Runnable{
    
	private static final Logger logger = LoggerFactory.getLogger(ImgZipHandlerAsync.class);
	private List<String> storeNos;
	private long accountId;
	private int qrCodeId;
	
	
	public ImgZipHandlerAsync(List<String> stores,long accId,int qcId){
		this.storeNos = stores;
		this.accountId = accId;
		this.qrCodeId = qcId;
	}
	
	@Override
	public void run() {
		String random = accountId+"-"+System.currentTimeMillis();
		StoreQrcodeDao storeQrcodeDao = (StoreQrcodeDao) SpringBeanUtil.getBean("storeQrcodeDao");
		try{
			String sourcepath = ConfigUtil.getKey("uploadpath")+ConfigUtil.getKey("zippath")+random;
			for(String storeno:storeNos){
				String qrcodeurl = Qrcode.encode(sourcepath,
						ConfigUtil.getKey("storeQrCodeUrl")+storeno);
//				ImageUtils.logoHandler(qrcodeurl, ConfigUtil.getKey("logo"), qrcodeurl);
				ImageUtils.handler(ConfigUtil.getKey("bgUrl"), qrcodeurl, qrcodeurl,storeno);
			}
			FileUtils.ZipMultiFile(sourcepath, sourcepath);
			int code = storeQrcodeDao.updateByStatus(2,random,qrCodeId);//2已生成
			if(0 == code){
				logger.info("ImgZipHandlerAsync updateStatus 2 fail");
			}
			FileUtils.delete(sourcepath);
		}catch(Exception e){
			logger.info("ImgZipHandlerAsync fail",e);
			int code = storeQrcodeDao.updateByStatus(3,random,qrCodeId);//3生成失败
			if(0 == code){
				logger.info("ImgZipHandlerAsync updateStatus 3 fail");
			}
			e.printStackTrace();
		}
		
	}

}
