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
 * 类描述：
 * 类名称：com.zhsj.controller.ModuleController     
 * 创建人：xulinchuang
 * 创建时间：2016年12月13日 上午10:56:43
 */
@RestController
@RequestMapping("module")
public class ModuleController {

	@Autowired
	private ModuleService moduleService;
	
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Object add(Module module){
		try {
			int id = moduleService.add(module);
			return CommonResult.success("添加模块成功", id);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("添加模块失败");
		}
	}
	
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public Object update(Module module){
		try {
			int id = moduleService.update(module);
			return CommonResult.success("更新模块成功", id);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("更新模块失败");
		}
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public Object delete(int id){
		try {
			int ida = moduleService.delete(id);
			return CommonResult.success("删除模块成功", ida);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("删除模块失败");
		}
	}
	/**
	 * 
	 * @Title: getModuleByModuleIdAndUserGroupId
	 * @Description: 通过用户组id  和 父模块id  查询该用户组在父模块下的子模块(二级模块和二级模块下面的三级模块)
	 * @param moduleId
	 * @param userGroupId
	 * @return
	 */
	@RequestMapping(value="getModuleByModuleIdAndUserGroupId",method=RequestMethod.POST)
	public Object getModuleByModuleIdAndUserGroupId(int moduleId,int userGroupId){
		try {
			List<Module> list = moduleService.getChildrenModulesByModuleId(moduleId, userGroupId);
			return CommonResult.success("查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("查询失败");
		}
	}
	/**
	 * 
	 * @Title: getParentModulesByUserGroupId
	 * @Description: 通过用户组id查询 该用户组下的父模块集合(用于显示页面上面的父模块)
	 * @param userGroupId
	 * @return
	 */
	@RequestMapping(value="getParentModulesByUserGroupId",method=RequestMethod.POST)
	public Object getParentModulesByUserGroupId(int userGroupId){
		
		try {
			List<Module> list = moduleService.getParentModulesByUserGroupId(userGroupId);
			return CommonResult.success("查询成功", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return CommonResult.defaultError("查询失败");
		}
	}
	/**
	 * 
	 * @Title: getModules
	 * @Description: 查询所有的模块以及关系(用于显示所有模块。给用户组分配模块)
	 * @return
	 */
	@RequestMapping(value="getModules")
	public Object getModules(){
		try {
			List<Module> list = moduleService.getModules();
			return CommonResult.success("查询模块成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("查询模块失败");
		}
	}
	
	/**
	 * 
	 * @Title: getModulesByUserGroupId
	 * @Description: 通过用户组id查询该用户组已经分配的模块(子模块关联的二级模块和三级模块)
	 * @return
	 */
	@RequestMapping(value="getModulesByUserGroupId")
	public Object getModulesByUserGroupId(int userGroupId){
		try {
			List<Module> list = moduleService.getModulesByUserGroupId(userGroupId);
			return CommonResult.success("查询模块成功",list);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("查询模块失败");
		}
	}
}
