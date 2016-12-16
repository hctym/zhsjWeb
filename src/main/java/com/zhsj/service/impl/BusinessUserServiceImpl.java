package com.zhsj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhsj.dao.BusinessInfoUserDao;
import com.zhsj.dao.BusinessUserDao;
import com.zhsj.model.BusinessInfoUser;
import com.zhsj.model.BusinessUser;
import com.zhsj.service.BusinessUserService;
import com.zhsj.util.Md5;

@Service
public class BusinessUserServiceImpl implements BusinessUserService{

	@Autowired
	private BusinessUserDao businessUserDao;
	@Autowired
	private BusinessInfoUserDao businessInfoUserDao;
	
	/**
	 * 
	 * @see com.zhsj.service.BusinessUserService#addBusinessUser(int, com.zhsj.model.BusinessUser)
	 */
	@Override
	public int addBusinessUser(int businessInfoId, BusinessUser businessUser) throws Exception{
		businessUser.setPassword(Md5.encrypt(businessUser.getPassword()));
		businessUser.setCtime(System.currentTimeMillis());
		businessUser.setUtime(System.currentTimeMillis());
		businessUser.setIsValid(1);
		businessUser.setStatus(1);
		businessUserDao.insert(businessUser);//保存商户用户
		BusinessInfoUser biu = new BusinessInfoUser();
		biu.setBusinessInfoId(businessInfoId);
		biu.setBusinessUserId(businessUser.getId());
		int biuid = businessInfoUserDao.insert(biu);//保存商户信息和商户用户关联关系
		return biuid;
	}

	/**
	 * 
	 * @see com.zhsj.service.BusinessUserService#getBusinessUsersByBusinessInfoId(int)
	 */
	@Override
	public List<BusinessUser> getBusinessUsersByBusinessInfoId(int bid) throws Exception{
		return businessUserDao.getBusinessUsersByBusinessInfoId(bid);
	}

	@Override
	public BusinessUser getUser(String name, String encrypt) {
		// TODO Auto-generated method stub
		return businessUserDao.getUser(name,encrypt);
	}

}
