package com.zhsj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhsj.service.ModuleBindRoleService;
import com.zhsj.util.CommonResult;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：角色模块的关联
 * 类名称：com.zhsj.controller.ModuleBindRoleController     
 * 创建人：xulinchuang
 * 创建时间：2016年12月30日 上午11:07:28
 */
@RestController
@RequestMapping("moduleRole")
public class ModuleBindRoleController {

	@Autowired
	private ModuleBindRoleService moduleBindRoleService;
	/**
	 * 
	 * @Title: add
	 * @Description: 给角色添加模块
	 * @param roleId
	 * @param moduleId
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Object add(int roleId,int moduleIds[]){
		try {
			int code = moduleBindRoleService.addAndUpdate(roleId, moduleIds);
			return CommonResult.success("success", code);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
}
