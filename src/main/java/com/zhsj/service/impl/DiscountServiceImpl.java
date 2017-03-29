package com.zhsj.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.zhsj.dao.DiscountDao;
import com.zhsj.dao.DiscountRuleDao;
import com.zhsj.dao.StoreDao;
import com.zhsj.dao.StoreDiscountDao;
import com.zhsj.model.Discount;
import com.zhsj.model.DiscountRule;
import com.zhsj.model.Store;
import com.zhsj.model.StoreDiscount;
import com.zhsj.service.DiscountService;
import com.zhsj.util.CommonResult;
import com.zhsj.util.DateUtil;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountDao discountDao;
	@Autowired
	private StoreDiscountDao storeDiscountDao;
	@Autowired
	private DiscountRuleDao discountRuleDao;
	@Autowired
	private StoreDao storeDao;
	
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
	/**
	 * 
	 * @see com.zhsj.service.DiscountService#addDiscountAndRules(java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	@Transactional
	@Override
	public Object addDiscountAndRules(String name, String startTime,
			String endTime, int type, String rule, String[] storeNos,int aStyle,String payStyle,String sumPlanAmount)
			throws Exception {
		int start = new Long(DateUtil.formatStringUnixTime(startTime, "yyyy-MM-dd HH:mm")).intValue();
        int end = new Long(DateUtil.formatStringUnixTime(endTime, "yyyy-MM-dd HH:mm")).intValue();
		List<String> storenoList = new ArrayList<String>();
		for(String storeno:storeNos){
			if(StringUtils.isEmpty(storeno)){
				continue;
			}
			storenoList.add(storeno);
		}
		for(String storeNo:storenoList){
			Store store = storeDao.getByStoreNo(storeNo);
			if(store == null){
				return CommonResult.build(1, "门店编号有误");
			}
		}
		//
		List<Discount> discountlist = storeDiscountDao.getByParam(storenoList,start,end);
		if(!CollectionUtils.isEmpty(discountlist)){
			return CommonResult.build(1, "优惠时间冲突");//优惠时间有冲突
		}
		
		Discount discount = new Discount();
		discount.setName(name);
		discount.setStartTime(start);
		discount.setEndTime(end);
		discount.setValid(1);
		discount.setaType(aStyle);
		discount.setContent("");
		discount.setType(type);
		discount.setCtime(System.currentTimeMillis()/1000);
		discount.setPlanAmount(BigDecimal.valueOf(Double.valueOf(sumPlanAmount)));
		discount.setPayMethod(payStyle);
		//添加优惠
		discountDao.add(discount);
		
		List<Map<String,String>> list = JSON.parseObject(rule,List.class);
		List<DiscountRule> dRules = new ArrayList<DiscountRule>();
		for(Map<String,String> map:list){
			DiscountRule dRule = new DiscountRule();
			dRule.setDiscountId(discount.getId());
			if(type != 2){
				dRule.setPlanAmount(BigDecimal.valueOf(Double.valueOf(map.get("f0"))));
				dRule.setExpendAmount(BigDecimal.valueOf(Double.valueOf(map.get("f1"))));
				dRule.setDiscount1(BigDecimal.valueOf(Double.valueOf(map.get("f2"))));
				dRule.setCtime(System.currentTimeMillis()/1000);
			}else{
				dRule.setPlanAmount(BigDecimal.valueOf(Double.valueOf(map.get("f0"))));
				dRule.setExpendAmount(BigDecimal.valueOf(Double.valueOf(map.get("f1"))));
				dRule.setDiscount1(BigDecimal.valueOf(Double.valueOf(map.get("f3"))));
				dRule.setDiscount2(BigDecimal.valueOf(Double.valueOf(map.get("f4"))));
				dRule.setCtime(System.currentTimeMillis()/1000);
			}
			dRules.add(dRule);
		}
		//添加优惠规则
		discountRuleDao.addMore(dRules);
		List<Store> storeList = new ArrayList<Store>();
		for(String storeNo:storenoList){
			Store store = storeDao.getByStoreNo(storeNo);
			storeList.add(store);
		}
		//添加优惠和商户绑定
		storeDiscountDao.addMore(storeList,discount.getStartTime(),discount.getEndTime(),discount.getId());
		return CommonResult.success("");
	}
	/**
	 * 
	 * @see com.zhsj.service.DiscountService#getListByParam(int, int, int, int, java.lang.String, int, int)
	 */
	@Override
	public Map<String,Object> getListByParam(int startTime, int endTime,
			int status, int type, String name, int page, int pageSize) throws Exception {
		List<Discount> dicountList = discountDao.getListByParam(startTime, endTime, status,
				type, name, (page-1)*pageSize, pageSize);
		long time = System.currentTimeMillis()/1000;
		for(Discount discount : dicountList){
			if(discount.getStatus() != 2){//2结束
				if(discount.getStartTime() > time){
					discount.setStatus(0);
				}else if(discount.getStartTime() <= time && discount.getEndTime() >= time){
					discount.setStatus(1);
				}else if(discount.getEndTime() < time){
					discount.setStatus(2);
				}
			}
		}
		int count = discountDao.getCountByParam(startTime, endTime, status, type, name);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", dicountList);
		resultMap.put("count", count);
		return resultMap;
	}
	@Override
	public Object del(int discountId) throws Exception{
		 discountDao.updateValidByDiscountId(discountId);
		 storeDiscountDao.upDateValidByDiscountId(discountId);
		 return CommonResult.success("");
	}
	@Override
	public Object update(int discountId) throws Exception{
		discountDao.updateStatusByDiscountId(discountId);
		storeDiscountDao.updateStatusByDiscountId(discountId);
		return CommonResult.success("");
	}

}
