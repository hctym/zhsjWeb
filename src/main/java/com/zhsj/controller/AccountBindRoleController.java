package com.zhsj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhsj.service.AccountBindRoleService;
import com.zhsj.util.CommonResult;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：用户角色的关联
 * 类名称：com.zhsj.controller.AccountBindRoleController     
 * 创建人：xulinchuang
 * 创建时间：2017年1月19日 上午11:04:20
 */
@RestController
@RequestMapping("accountRole")
public class AccountBindRoleController {
  
	@Autowired
	private AccountBindRoleService accountBindRoleService;
	/**
	 * 
	 * @Title: addOrUpdate
	 * @Description: 添加更新
	 * @param accountId
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value="addOrUpdate",method=RequestMethod.POST)
	public Object addOrUpdate(int accountId,int roleIds[]){
		try {
			int count = accountBindRoleService.addOrUpdate(accountId, roleIds);
			return CommonResult.success("success", count);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail"); 
		}
	}
}
