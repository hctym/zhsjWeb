package com.zhsj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhsj.dao.ModuleBindRoleDao;
import com.zhsj.dao.ModuleDao;
import com.zhsj.dao.RoleDao;
import com.zhsj.model.Module;
import com.zhsj.model.ModuleBindRole;
import com.zhsj.model.Role;
import com.zhsj.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ModuleBindRoleDao moduleBindRoleDao;
	@Autowired
	private ModuleDao moduleDao;
	
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
    /**
     * 
     * @see com.zhsj.service.RoleService#getParentModulesByRoleIds(java.util.List)
     */
	@Override
	public List<Module> getParentModulesByRoleIds(List<Integer> roleIds) throws Exception{
		List<ModuleBindRole> cModules = moduleBindRoleDao.getModuleByRoleIds(roleIds.toArray());
		Set<Integer> cmIdset = new HashSet<Integer>();
		for(ModuleBindRole bmr:cModules){
			cmIdset.add(bmr.getModuleId());//子模块去重之后的  模块id集合
		}
		List<Module> modules = moduleDao.getModulesByCms(cmIdset.toArray());//通过子模块id的集合查找 模块集合
		Set<Integer> pmIdSet = new HashSet<Integer>();
		for(Module m:modules){
			pmIdSet.add(m.getParentId());
		}
		List<Module> pModules = moduleDao.getModulesByCms(pmIdSet.toArray());
		return pModules;
	}
    /**
     * 
     * @see com.zhsj.service.RoleService#getModulesByPmIdAndRoleIds(int, java.util.List)
     */
	@Override
	public List<Module> getModulesByPmIdAndRoleIds(int moduleId,
			List<Integer> roleIds) throws Exception{
		List<Module> cmModules = moduleDao.getModulesByParentMonduleId(moduleId);
		List<ModuleBindRole> cModules = moduleBindRoleDao.getModuleByRoleIds(roleIds.toArray());
		Set<Integer> cmIdset = new HashSet<Integer>();
		for(ModuleBindRole bmr:cModules){
			cmIdset.add(bmr.getModuleId());//子模块去重之后的  模块id集合
		}
		List<Module> cList = new ArrayList<Module>();
		for(Module m:cmModules){
			for(Integer i:cmIdset){
				if(m.getId() == i){
					cList.add(m);
				}
			}
		}
		return cList;
	}

	/**
	 * 
	 * @see com.zhsj.service.RoleService#getListByType(int)
	 */
	@Override
	public List<Role> getListByType(int type) throws Exception {
		List<Role> roles = roleDao.getListByType(type);
		return roles;
	}

}
