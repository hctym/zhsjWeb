package com.zhsj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhsj.dao.RoleDao;
import com.zhsj.model.Module;
import com.zhsj.model.Role;
import com.zhsj.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	/**
	 * 
	 * @see com.zhsj.service.RoleService#insert(com.zhsj.model.Role)
	 */
	@Override
	public int insert(Role role) throws Exception {
		role.setValid(1);
		role.setUtime(System.currentTimeMillis()/1000);
		role.setCtime(System.currentTimeMillis()/1000);
		return roleDao.add(role);
	}

	/**
	 * 
	 * @see com.zhsj.service.RoleService#getParentModulesByRoleId(int)
	 */
	@Override
	public List<Module> getParentModulesByRoleId(int roleId) throws Exception {
		List<Module> parentModules = roleDao.getParentModulesByRoleId(roleId);
		Map<Integer, Module> map = new HashMap<Integer, Module>();
		for(Module m:parentModules){
			map.put(m.getId(), m);
		}
		List<Module> modules = new ArrayList<Module>();
		Iterator<Integer> it = map.keySet().iterator();  
		while (it.hasNext()) {  
	           modules.add(map.get(it.next()));  
	    }  
		return modules;
	}

	/**
	 * 
	 * @see com.zhsj.service.RoleService#getRoles()
	 */
	@Override
	public List<Role> getRoles(int page,int pageSize) {
		return roleDao.getRoles((page-1)*pageSize,pageSize);
	}

	/**
	 * 
	 * @see com.zhsj.service.RoleService#getAllModuleByRoleId(int)
	 */
	@Override
	public List<Module> getAllModuleByRoleId(int roleId) throws Exception {
		return roleDao.getAllModuleByRoleId(roleId);
	}

	/**
	 * 
	 * @see com.zhsj.service.RoleService#deleteById(int)
	 */
	@Override
	public int deleteById(int id) throws Exception {
		return roleDao.deleteById(id);
	}
    /**
     * 
     * @see com.zhsj.service.RoleService#update(com.zhsj.model.Role)
     */
	@Override
	public void update(Role role) throws Exception {
		 role.setUtime(System.currentTimeMillis()/1000);
		 roleDao.update(role);
	}

}
