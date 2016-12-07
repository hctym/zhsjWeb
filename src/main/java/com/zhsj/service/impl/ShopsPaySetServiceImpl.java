package com.zhsj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhsj.dao.ShopsPaySetDao;
import com.zhsj.model.ShopsPayset;
import com.zhsj.service.ShopsPaySetService;

@Service
public class ShopsPaySetServiceImpl implements ShopsPaySetService {

	@Autowired
	private ShopsPaySetDao shopsPaySetDao;
	
	/**
	 * 
	 * @see com.zhsj.service.ShopsPaySetService#insert(com.zhsj.model.ShopsPayset)
	 */
	@Override
	public int insert(ShopsPayset shopsPayset) throws Exception {
		return shopsPaySetDao.insert(shopsPayset);
	}
    /**
     * 
     * @see com.zhsj.service.ShopsPaySetService#delete(int)
     */
	@Override
	public int delete(int id) throws Exception {
		return shopsPaySetDao.delete(id);
	}
    /**
     * 
     * @see com.zhsj.service.ShopsPaySetService#getListByBmUserId(int)
     */
	@Override
	public List<ShopsPayset> getListByBmUserId(int bmUserId) {
		return shopsPaySetDao.getListByBmUserId(bmUserId);
	}
    /**
     * 
     * @see com.zhsj.service.ShopsPaySetService#getPaysetById(int)
     */
	@Override
	public ShopsPayset getPaysetById(int id) {
		return shopsPaySetDao.getPaysetById(id);
	}

}
