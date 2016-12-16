package com.zhsj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhsj.model.UserGroup;
import com.zhsj.service.UserGroupService;
import com.zhsj.util.CommonResult;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：用户组的controller
 * 类名称：com.zhsj.controller.UserGroupController     
 * 创建人：xulinchuang
 * 创建时间：2016年12月8日 上午10:58:43
 */
@RestController
@RequestMapping("userGroup")
public class UsersGroupController {

	@Autowired
	private UserGroupService userGroupService;
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	private Object addUserGroup(UserGroup userGroup){
		try {
			int id = userGroupService.add(userGroup);
			return CommonResult.success("添加成功", id);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("添加失败");
		}
	}
	
	/**
	 * 
	 * @Title: getList
	 * @Description: TODO
	 * @return
	 */
	@RequestMapping(value="getlist",method=RequestMethod.POST)
	public Object getList(){
		try {
			List<UserGroup> list = userGroupService.getList();
			return CommonResult.success("获取用户组成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("获取用户组失败");
		}
	}
	/**
	 * 
	 * @Title: addUserGroupModules
	 * @Description: 添加用户组对应的模块关联
	 * @param userGroupId
	 * @param moduleIds
	 * @return
	 */
	@RequestMapping(value="addUserGroupModules")
	public Object addUserGroupModules(int userGroupId,int[] moduleIds){
		try {
			int count = userGroupService.addUserGroupModule(userGroupId,moduleIds);
			return CommonResult.success("添加模块成功", count);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("添加模块失败");
		}
	}
}
