package com.zhsj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhsj.dao.CityCodeDao;
import com.zhsj.model.CityCode;
import com.zhsj.service.CityCodeService;


@Service
public class CityCodeServiceImpl implements CityCodeService {

	@Autowired
	private CityCodeDao cityCodeDao;
	/**
	 * 
	 * @see com.zhsj.service.CityCodeService#getListByCode(java.lang.String)
	 */
	@Override
	public List<CityCode> getListByCode(String code) throws Exception {
		return cityCodeDao.getListByParentCode(code);
	}

}
