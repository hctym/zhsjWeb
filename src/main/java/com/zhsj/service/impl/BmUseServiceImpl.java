package com.zhsj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.serializer.MapSerializer;
import com.zhsj.dao.BmUserDao;
import com.zhsj.model.BmUser;
import com.zhsj.service.BmUserService;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：
 * 类名称：com.zhsj.service.impl.BmUseServiceImpl     
 * 创建人：xulinchuang
 * 创建时间：2016年12月7日 上午9:39:06
 */
@Service
public class BmUseServiceImpl implements BmUserService {
	
	@Autowired
	private BmUserDao bmUserDao;
    /**
     * 
     * @see com.zhsj.service.BmUserService#insert(com.zhsj.model.BmUser)
     */
	@Override
	public int insert(BmUser bmUser) throws Exception {
		bmUser.setIsValid(0);
		bmUser.setCtime(System.currentTimeMillis());
		bmUser.setUtime(System.currentTimeMillis());
		return bmUserDao.insert(bmUser);
	}
    /**
     * 
     * @see com.zhsj.service.BmUserService#getList(int, int)
     */
	@Override
	public List<BmUser> getList(int page, int pageSize) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", (page-1)*pageSize);
		map.put("end", pageSize);
		List<BmUser> list = bmUserDao.getBmUserList(map);
		return list;
	}
    /**
     * 
     * @see com.zhsj.service.BmUserService#getBmUserById(int)
     */
	@Override
	public BmUser getBmUserById(int id) throws Exception{
		return bmUserDao.getBmUserById(id);
	}
       
}
