package com.zhsj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhsj.dao.UserGroupDao;
import com.zhsj.dao.UserGroupModuleDao;
import com.zhsj.model.UserGroup;
import com.zhsj.model.UserGroupModule;
import com.zhsj.service.UserGroupService;

@Service
public class UserGroupServiceImpl implements UserGroupService {

	@Autowired
	private UserGroupDao usersGroupDao;
	@Autowired
	private UserGroupModuleDao userGroupModuleDao;
	/**
	 * 
	 * @see com.zhsj.service.UserGroupService#getList()
	 */
	@Override
	public List<UserGroup> getList() throws Exception{
		
		return usersGroupDao.getList();
	}
    /**
     * 
     * @see com.zhsj.service.UserGroupService#addUserGroupModule(int, int[])
     */
	@Override
	@Transactional
	public int addUserGroupModule(int userGroupId,int[] moduleIds) throws Exception {
		int count = 0;
		List<UserGroupModule> ugmModules = userGroupModuleDao.getModulesByUserGroupId(userGroupId);
		if(ugmModules != null){//删除当前用户组关联的所有模块。重新添加关联模块
			List<Integer> ugModuleIds = new ArrayList<Integer>();
			for(UserGroupModule ugm:ugmModules){
//				userGroupModuleDao.delete(ugm.getId());//删掉一条
				ugModuleIds.add(ugm.getId());
			}
			userGroupModuleDao.deleteMultiple(ugModuleIds);//删掉多条
		}
		for(int i = 0,len=moduleIds.length;i<len;i++){
			UserGroupModule ugm = new UserGroupModule();
			ugm.setUserGroupId(userGroupId);
			ugm.setModuleId(moduleIds[i]);
			userGroupModuleDao.add(ugm);
			count++;
		}
		return count;
	}
	/**
	 * 
	 * @see com.zhsj.service.UserGroupService#getModulesByUserGroupId(int)
	 */
	@Override
	public List<UserGroupModule> getModulesByUserGroupId(int userGroupId) {
		List<UserGroupModule> ugModules = userGroupModuleDao.getModulesByUserGroupId(userGroupId);
		return ugModules;
	}
	/**
	 * 
	 * @see com.zhsj.service.UserGroupService#add(com.zhsj.model.UserGroup)
	 */
	@Override
	public int add(UserGroup userGroup) throws Exception{
		userGroup.setIsValid(1);
		userGroup.setCtime(System.currentTimeMillis());
		return usersGroupDao.add(userGroup);
	}

	
}
