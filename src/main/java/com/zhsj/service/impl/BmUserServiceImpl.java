package com.zhsj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhsj.dao.BmUserDao;
import com.zhsj.dao.BmUserGroupDao;
import com.zhsj.model.BmUser;
import com.zhsj.model.BmUserGroup;
import com.zhsj.service.BmUserService;
import com.zhsj.util.Md5;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：对用户的操作(crud以及给用户分配角色)
 * 类名称：com.zhsj.service.impl.BmUseServiceImpl     
 * 创建人：xulinchuang
 * 创建时间：2016年12月7日 上午9:39:06
 */
@Service
public class BmUserServiceImpl implements BmUserService {
	
	@Autowired
	private BmUserDao bmUserDao;
	@Autowired
	private BmUserGroupDao bmUserGroupDao;
    /**
     * 
     * @see com.zhsj.service.BmUserService#insert(com.zhsj.model.BmUser)
     */
	@Override
	public int insert(BmUser bmUser) throws Exception {
		bmUser.setIsValid(0);
		bmUser.setStatus(1);
		bmUser.setCtime(System.currentTimeMillis());
		bmUser.setUtime(System.currentTimeMillis());
		bmUser.setPassword(Md5.encrypt(bmUser.getPassword()));
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
	/**
	 * 
	 * @see com.zhsj.service.BmUserService#getBmUser(java.lang.String, java.lang.String)
	 */
	@Override
	public BmUser getBmUser(String account, String password) {
		return bmUserDao.getBmUser(account, password);
	}
	/**
	 * 
	 * @see com.zhsj.service.BmUserService#addOrUpdateUserGroup(com.zhsj.model.BmUserGroup)
	 */
	@Override
	public int addOrUpdateUserGroup(BmUserGroup bmUserGroup) {
//		BmUserGroup bug = bmUserGroupDao.getBmUserGroupByBmUserId(bmUserGroup.getBmUserId());
//		if(bug != null)
		if(bmUserGroup.getId() != 0){
			return bmUserGroupDao.update(bmUserGroup);
		}else{
			return bmUserGroupDao.add(bmUserGroup);
		}
	}
       
}
