package com.zhsj.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhsj.model.Account;
import com.zhsj.model.AccountBindRole;
import com.zhsj.model.Module;
import com.zhsj.model.Role;
import com.zhsj.model.StoreAccount;
import com.zhsj.model.StoreAccountBindRole;
import com.zhsj.service.RoleService;
import com.zhsj.util.CommonResult;
import com.zhsj.util.SessionThreadLocal;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：角色
 * 类名称：com.zhsj.controller.RoleController     
 * 创建人：xulinchuang
 * 创建时间：2016年12月30日 下午6:51:44
 */
@RestController
@RequestMapping("role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	/**
	 * 
	 * @Title: add
	 * @Description: 添加
	 * @param role
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Object add(Role role){
		try {
			int id = roleService.insert(role);
			return CommonResult.success("success", id);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: delete
	 * @Description: 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deleteById",method=RequestMethod.POST)
	public Object delete(int id){
		try {
			roleService.deleteById(id);
			return CommonResult.success("success");
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: update
	 * @Description: 更新
	 * @param role
	 * @return
	 */
	@RequestMapping("update")
	public Object update(Role role){
		try {
			roleService.update(role);
			return CommonResult.success("success");
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
		
	}
	/**
	 * 
	 * @Title: getRoles
	 * @Description:获取有效的角色列表
	 * @return
	 */
	@RequestMapping(value="getList",method=RequestMethod.POST)
	public Object getRoles(int page,int pageSize){
		try {
			List<Role> roles = roleService.getRoles(page,pageSize);
			return CommonResult.success("success", roles);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
		
	}
	/**
	 * 
	 * @Title: getRolesByType
	 * @Description: 通过类型获取该类型所有可用的角色
	 * @param type
	 * @return
	 */
	@RequestMapping(value="getListByType",method={RequestMethod.GET,RequestMethod.POST})
	public Object getRolesByType(int type){
		   try {
			List<Role> roles = roleService.getListByType(type);
			return CommonResult.success("", roles);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return CommonResult.defaultError("");	
		}
	       
	}
	/**
	 * 
	 * @Title: getModulesByRoleId
	 * @Description: 通过角色id查询父模块
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="getParentModulesByRoleId",method=RequestMethod.POST)
	public Object getParentModulesByRoleId(int roleId){
		try {
			List<Module> modules = roleService.getParentModulesByRoleId(roleId);
			return CommonResult.success("success", modules);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getAllModuleByRoleId
	 * @Description: 通过角色获取关联的模块以及父模块(用于展示当前角色用于的模块)
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="getAllModuleByRoleId")
	public Object getAllModuleByRoleId(int roleId){
		try {
			List<Module> modules = roleService.getAllModuleByRoleId(roleId);
			return CommonResult.success("success", modules);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getParentModuleByAccount
	 * @Description: 通过账户获取该用户的额所有父模块
	 * @return
	 */
	@RequestMapping(value="getParentModuleByAccount")
	public Object getParentModuleByAccount(){
		Map<String, Object> map = SessionThreadLocal.getSession();
		String flag = (String)map.get("flag");
		List<Integer> roleIds = new ArrayList<Integer>();
		if("account".equals(flag)){
			Account account = (Account) map.get("user");
			List<AccountBindRole> accountBindRoles = account.getAccountBindRoles();
			for(AccountBindRole abr:accountBindRoles){
				roleIds.add(abr.getRoleId());
			}
		}else if("storeAccount".equals(flag)){
			StoreAccount storeAccount = (StoreAccount) map.get("user");
			List<StoreAccountBindRole> storeAccountBindRoles  = storeAccount.getStoreAccountBindRoles();
			for(StoreAccountBindRole sabr:storeAccountBindRoles){
				roleIds.add(sabr.getRoleId());
			}
		}
		try {
			List<Module> modules = roleService.getParentModulesByRoleIds(roleIds);
			return CommonResult.success("success", modules);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getModulesByPmIdandAccount
	 * @Description: TODO
	 * @param moduleId
	 * @return
	 */
	@RequestMapping(value="getModulesByPmIdandAccount")
	public Object getModulesByPmIdandAccount(int moduleId){
		Map<String, Object> map = SessionThreadLocal.getSession();
		String flag = (String)map.get("flag");
		List<Integer> roleIds = new ArrayList<Integer>();
		if("account".equals(flag)){
			Account account = (Account) map.get("user");
			List<AccountBindRole> accountBindRoles = account.getAccountBindRoles();
			for(AccountBindRole abr:accountBindRoles){
				roleIds.add(abr.getRoleId());
			}
		}else if("storeAccount".equals(flag)){
			StoreAccount storeAccount = (StoreAccount) map.get("user");
			List<StoreAccountBindRole> storeAccountBindRoles  = storeAccount.getStoreAccountBindRoles();
			for(StoreAccountBindRole sabr:storeAccountBindRoles){
				roleIds.add(sabr.getRoleId());
			}
		}
		try {
			List<Module> modules = roleService.getModulesByPmIdAndRoleIds(moduleId,roleIds);
			return CommonResult.success("success", modules);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
}
