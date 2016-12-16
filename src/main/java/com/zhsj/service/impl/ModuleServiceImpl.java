package com.zhsj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhsj.dao.ModuleDao;
import com.zhsj.model.Module;
import com.zhsj.service.ModuleService;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：模块的具体实现
 * 类名称：com.zhsj.service.impl.ModuleServiceImpl     
 * 创建人：xulinchuang
 * 创建时间：2016年12月13日 上午10:28:34
 */
@Service
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleDao moduleDao;
	/**
	 * 
	 * @see com.zhsj.service.ModuleService#add(com.zhsj.model.Module)
	 */
	@Override
	public int add(Module module) throws Exception {
		module.setCtime(System.currentTimeMillis());
		return moduleDao.add(module);
	}
    /**
     * 
     * @see com.zhsj.service.ModuleService#update(com.zhsj.model.Module)
     */
	@Override
	public int update(Module module) throws Exception {
		return moduleDao.update(module);
	}
    /**
     * 
     * @see com.zhsj.service.ModuleService#delete(int)
     */
	@Override
	public int delete(int id) throws Exception {
		return moduleDao.delete(id);
	}
    /**
     * 
     * @see com.zhsj.service.ModuleService#getParentModulesByUserGroupId(int)
     */
	@Override
	public List<Module> getParentModulesByUserGroupId(int userGroupId) throws Exception{
		List<Module> parentModules = moduleDao.getParentModulesByUserGroupId(userGroupId);
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
     * @see com.zhsj.service.ModuleService#getChildrenModulesByModuleId(int, int)
     */
	@Override
	public List<Module> getChildrenModulesByModuleId(int moduleId,
			int userGroupId) throws Exception{
		List<Module> list = moduleDao.getModulesByParentModuleId(moduleId, userGroupId);
		return list;
	}
	/**
	 * 
	 * @see com.zhsj.service.ModuleService#geModules()
	 */
	@Override
	public List<Module> getModules() throws Exception {
		return moduleDao.getModules();
	}
	@Override
	public List<Module> getModulesByUserGroupId(int userGroupId)
			throws Exception {
		return moduleDao.getModulesByUserGroupId(userGroupId);
	}

}
