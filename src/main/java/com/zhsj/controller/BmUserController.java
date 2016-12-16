package com.zhsj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhsj.model.BmUser;
import com.zhsj.model.BmUserGroup;
import com.zhsj.service.BmUserService;
import com.zhsj.util.CommonResult;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：bmUser控制操作
 * 类名称：com.zhsj.controller.BmUserController     
 * 创建人：xulinchuang
 * 创建时间：2016年12月7日 上午9:47:34
 */
@RestController
@RequestMapping("bmuser")
public class BmUserController {
	
    @Autowired
	private BmUserService bmUserService;
    /**
     * 
     * @Title: addBmUser
     * @Description: TODO
     * @param bmUser
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addBmUser(BmUser bmUser){
    	
		try {
			int id = bmUserService.insert(bmUser);
			return CommonResult.success("添加用户成功", id); 
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("系统错误");
		}
    }
    /**
     * 
     * @Title: getList
     * @Description: 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    public Object getList(int page,int pageSize){
    	try {
			List<BmUser> list = bmUserService.getList(page, pageSize);
			return CommonResult.success("查询分页bmUser成功",list);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("系统错误");
		}
    }
    /**
     * 
     * @Title: getBmUser
     * @Description: TODO
     * @param id
     * @return
     */
    @RequestMapping(value = "getbmuser/{id}")
    public Object getBmUser(@PathVariable("id")int id){
    	try {
			BmUser bmUser = bmUserService.getBmUserById(id);
			return CommonResult.success("查询成功", bmUser);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("系统错误");
		}
    }
    
    /**
     * 
     * @Title: addUserGroup
     * @Description: 给 用户添加用户组(给用户分配角色)
     * @return
     */
    public Object addOrUpdateUserGroup(BmUserGroup bmUserGroup){
    	bmUserService.addOrUpdateUserGroup(bmUserGroup);
    	return null;
    }
}
