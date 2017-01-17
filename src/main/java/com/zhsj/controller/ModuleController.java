package com.zhsj.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhsj.model.Module;
import com.zhsj.service.ModuleService;
import com.zhsj.util.CommonResult;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：模块表
 * 类名称：com.zhsj.controller.ModuleController     
 * 创建人：xulinchuang
 * 创建时间：2016年12月29日 上午10:56:54
 */
@RestController
@RequestMapping("module")
public class ModuleController {

	@Autowired
	private ModuleService moduleService;
	/**
	 * 
	 * @Title: add
	 * @Description: 添加模块
	 * @param module
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Object add(Module module){
		try {
			int id = moduleService.insert(module);
			return CommonResult.success("success",id);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getModules
	 * @Description: 获取所有模块(用于管理员分配角色)
	 * @return
	 */
	@RequestMapping(value="getModules",method=RequestMethod.GET)
	public Object getModules(){
		try {
			List<Module> modules = moduleService.getModules();
			return CommonResult.success("success", modules);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	
	/**
	 * 
	 * @Title: getModulesByParentModuleIdAndRoleId
	 * @Description: 通过父模块ID和角色ID。获取子模块
	 * @param parentModuleId
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="getModulesByParentModuleIdAndRoleId",method=RequestMethod.POST)
	public Object getModulesByParentModuleIdAndRoleId(int moduleId,int roleId){
		try {
			List<Module> modules = moduleService.getModulesByParentModuleIdAndRoleId(moduleId,roleId);
			return CommonResult.success("success",modules);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
		
	}
	
}
