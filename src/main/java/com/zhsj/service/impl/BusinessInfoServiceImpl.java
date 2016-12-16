package com.zhsj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhsj.dao.BusinessInfoDao;
import com.zhsj.model.BusinessInfo;
import com.zhsj.service.BusinessInfoService;

@Service
public class BusinessInfoServiceImpl implements BusinessInfoService {

	@Autowired
	private BusinessInfoDao businessInfoDao;
	/**
	 * 
	 * @see com.zhsj.service.BusinessInfoService#insert(com.zhsj.model.BusinessInfo)
	 */
	@Override
	public int insert(BusinessInfo businessInfo) throws Exception {
		businessInfo.setCtime(System.currentTimeMillis());
		businessInfo.setUtime(System.currentTimeMillis());
		return businessInfoDao.insert(businessInfo);
	}

	/**
	 * 
	 * @see com.zhsj.service.BusinessInfoService#getList(int, int)
	 */
	@Override
	public List<BusinessInfo> getList(int page, int pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (page-1)*pageSize);
		map.put("end", pageSize);
		List<BusinessInfo> list = businessInfoDao.getBusinessInfoList(map);
		return list;
	}
    /**
     * 
     * @see com.zhsj.service.BusinessInfoService#getBusinessInfoById(int)
     */
	@Override
	public BusinessInfo getBusinessInfoById(int id) throws Exception {
		return businessInfoDao.getBusinessInfoById(id);
	}

}
