package com.zhsj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhsj.dao.ModuleDao;
import com.zhsj.model.Module;
import com.zhsj.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService{

	@Autowired
	private ModuleDao moduleDao;
	/**
	 * 
	 * @see com.zhsj.service.ModuleService#insert(com.zhsj.model.Module)
	 */
	@Override
	public int insert(Module module) throws Exception {
		module.setValid(1);
		module.setCtime(System.currentTimeMillis()/1000);
		return moduleDao.add(module);
	}
    /**
     * 
     * @see com.zhsj.service.ModuleService#getModules()
     */
	@Override
	public List<Module> getModules() throws Exception {
		return moduleDao.getModules();
	}
	/**
	 * 
	 * @see com.zhsj.service.ModuleService#getModulesByParentModuleIdAndRoleId(int, int)
	 */
	@Override
	public List<Module> getModulesByParentModuleIdAndRoleId(int parentModuleId,
			int roleId) throws Exception {
		// TODO Auto-generated method stub
		return moduleDao.getModulesByParentModuleIdAndRoleId(parentModuleId, roleId);
	}

}
