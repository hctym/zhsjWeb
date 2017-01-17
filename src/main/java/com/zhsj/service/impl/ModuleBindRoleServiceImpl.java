package com.zhsj.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhsj.dao.ModuleBindRoleDao;
import com.zhsj.model.ModuleBindRole;
import com.zhsj.service.ModuleBindRoleService;

@Service
public class ModuleBindRoleServiceImpl implements ModuleBindRoleService{

	@Autowired
	private ModuleBindRoleDao moduleBindRoleDao;
	
	/**
	 * 
	 * @see com.zhsj.service.ModuleBindRoleService#addAndUpdate(int, int[])
	 */
	@Transactional
	@Override
	public int addAndUpdate(int roleId, int modules[]) throws Exception {
		moduleBindRoleDao.deleteAllByRoleId(roleId);
		int count=0;
		for(int moduleId:modules){
			ModuleBindRole moduleBindRole = new ModuleBindRole();
			moduleBindRole.setModuleId(moduleId);
			moduleBindRole.setRoleId(roleId);
			moduleBindRole.setValid(1);
			moduleBindRole.setCtime(System.currentTimeMillis()/1000);
			moduleBindRole.setUtime(System.currentTimeMillis()/1000);
			moduleBindRoleDao.add(moduleBindRole);
			count++;
		}
		return count;
	}

}
