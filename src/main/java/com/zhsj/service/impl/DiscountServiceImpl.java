package com.zhsj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhsj.dao.DiscountDao;
import com.zhsj.dao.DiscountRuleDao;
import com.zhsj.dao.StoreDiscountDao;
import com.zhsj.model.Discount;
import com.zhsj.model.DiscountRule;
import com.zhsj.model.StoreDiscount;
import com.zhsj.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountDao discountDao;
	@Autowired
	private StoreDiscountDao storeDiscountDao;
	@Autowired
	private DiscountRuleDao discountRuleDao;
	
	/**
	 * 
	 * @see com.zhsj.service.DiscountService#add(com.zhsj.model.Discount, java.lang.String)
	 */
	@Transactional
	@Override
	public int add(Discount discount, String storeNo) throws Exception {
		discount.setValid(1);
		discount.setCtime(System.currentTimeMillis()/1000);
		discount.setUtime(System.currentTimeMillis()/1000);
		discountDao.add(discount);
		StoreDiscount storeDiscount = new StoreDiscount();
		storeDiscount.setDiscountId(discount.getId());
		storeDiscount.setStoreNo(storeNo);
		storeDiscount.setStartTime(discount.getStartTime());
		storeDiscount.setEndTime(discount.getEndTime());
		storeDiscount.setValid(1);
		storeDiscount.setUtime(System.currentTimeMillis()/1000);
		storeDiscount.setCtime(System.currentTimeMillis()/1000);
		int id = storeDiscountDao.add(storeDiscount);
		return id;
	}
    /**
     * 
     * @see com.zhsj.service.DiscountService#getListByPage(int, int, java.lang.String)
     */
	@Override
	public Map<String, Object> getListByPage(int page, int pageSize,
			String storeNo) throws Exception {
		List<Discount> list = discountDao.getListByPageAndStoreNo((page-1)*pageSize, pageSize, storeNo);
		int count = storeDiscountDao.getCountByStoreNo(storeNo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("count", count);
		return map;
	}
	/**
	 * 
	 * @see com.zhsj.service.DiscountService#addRule(com.zhsj.model.DiscountRule)
	 */
	@Override
	public int addRule(DiscountRule discountRule) throws Exception {
		discountRule.setCtime(System.currentTimeMillis()/1000);
		int code = discountRuleDao.add(discountRule);
		return code;
	}
	/**
	 * 
	 * @see com.zhsj.service.DiscountService#getRuleListByDiscountId(int)
	 */
	@Override
	public List<DiscountRule> getRuleListByDiscountId(int discountId) {
		List<DiscountRule> list = discountRuleDao.getListByDiscountId(discountId);
		return list;
	}

}
