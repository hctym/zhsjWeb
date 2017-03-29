package com.zhsj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhsj.dao.SequenceDao;
import com.zhsj.dao.StoreQrcodeDao;
import com.zhsj.dao.TBStoreNoDao;
import com.zhsj.model.Account;
import com.zhsj.model.StoreQrcode;
import com.zhsj.model.TBStoreNo;
import com.zhsj.service.QrCodeService;
import com.zhsj.task.ImgZipHandlerAsync;
import com.zhsj.util.AyncTaskUtil;
import com.zhsj.util.CommonResult;
import com.zhsj.util.SessionThreadLocal;
import com.zhsj.util.StoreUtils;

@Service
public class QrCodeServiceImpl implements QrCodeService {
	
	private static final Logger logger = LoggerFactory.getLogger(QrCodeServiceImpl.class);
	@Autowired
	private StoreQrcodeDao storeQrcodeDao;
	@Autowired
	private SequenceDao sequenceDao;
	@Autowired
	private TBStoreNoDao tbStoreNoDao;
	@Autowired
	private AyncTaskUtil ayncTaskUtil;
	
	
	@Override
	public Object CreateQrCode(StoreQrcode storeQrcode) {
		try {
			Account account = SessionThreadLocal.getAccount();
			storeQrcode.setAccountId(account.getId());
			int code = storeQrcodeDao.insertOne(storeQrcode);
			if(0 == code){
				logger.info("#QrCodeServiceImpl.CreateQrCode insert fail#storeQrcode = {}",storeQrcode);
			}
			int count = storeQrcode.getCount();
			List<TBStoreNo> storeNos = new ArrayList<TBStoreNo>();//保存store_no lists
			List<String> nos = new ArrayList<String>();//存放 storeNo
			for(int i = 0;i < count;i ++){
				String storeno = StoreUtils.getStoreNO(sequenceDao.getCode());
				TBStoreNo tbNo = new TBStoreNo();
				tbNo.setStoreNo(storeno);
				nos.add(storeno);
				tbNo.setQrCodeId(storeQrcode.getId());
				storeNos.add(tbNo);
			}
			int storeCode = tbStoreNoDao.insertAll(storeNos);
			if(0 == storeCode){
				logger.info("#QrCodeServiceImpl.CreateQrCode insert tbStoreNoDao fail #storeNos = {}",storeNos);
			}
			ImgZipHandlerAsync imgZip = new ImgZipHandlerAsync(nos, account.getId(), storeQrcode.getId());
			ayncTaskUtil.commitAyncTask(imgZip);
			return CommonResult.success("Success");
		} catch (Exception e) {
			logger.error("#QrCodeServiceImpl.CreateQrCode fail",e);
			return CommonResult.defaultError("error");
		}
	}
	

	@Override
	public Object getListByPage(int page, int pageSize) {
		logger.info("#QrCodeServiceImpl.getListByPage #page = {},pageSize = {}",page,pageSize);
		try{
			List<StoreQrcode> storeQrcodes = storeQrcodeDao.getListByPage((page-1)*pageSize, pageSize);
			int count = storeQrcodeDao.getCount();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", storeQrcodes);
			map.put("count", count);
			return CommonResult.success("Success", map);
		}catch(Exception e){
			logger.error("#QrCodeServiceImpl.getListByPage #page={},#pageSize={}",page,pageSize,e);
			return CommonResult.defaultError("error");
		}
	}


	@Override
	public Object getListByNog(int[] nogIds) {
		logger.info("#QrCodeServiceImpl.getListByNog #nogIds = {}",nogIds);
		try{
		    List<StoreQrcode> storeQrcodes = storeQrcodeDao.getListByNogs(nogIds);
		    return CommonResult.success("Success", storeQrcodes);
		}catch(Exception e){
			logger.error("#QrCodeServiceImpl.getListByNog #nogIds = {}",nogIds,e);
			return CommonResult.defaultError("error");
		}
	}
	
	

}
