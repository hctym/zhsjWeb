package com.zhsj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhsj.model.Account;
import com.zhsj.model.Role;
import com.zhsj.service.AccountService;
import com.zhsj.util.AES;
import com.zhsj.util.CommonResult;
import com.zhsj.util.Md5;
import com.zhsj.util.SessionThreadLocal;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：账户(总部管理员和代理用户)
 * 类名称：com.zhsj.controller.AccountController     
 * 创建人：xulinchuang
 * 创建时间：2016年12月30日 上午11:44:53
 */
@RestController
@RequestMapping("account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	/**
	 * 
	 * @Title: add
	 * @Description:添加账户
	 * @param account
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Object add(Account account,int roleId,int orgId){
		try {
			int id = accountService.add(account,roleId,orgId);
			return CommonResult.success("success", id);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: login
	 * @Description: 登录
	 * @param accountName
	 * @param password
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public Object login(String name,String password,String login,
			HttpSession session,HttpServletResponse response) {
		try {
			Account account = accountService.getByName(name);
			int code = 0;//用户不存在
			if(account != null){
				if(account.getPassword().equals(Md5.encrypt(password))){
					code = 1;//登录成功
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("user", account);
					map.put("flag", "account");
					SessionThreadLocal.setSession(map);
					Cookie cookie = new Cookie("thor",AES.encrypt(name+","+Md5.encrypt(password)+","+login));
					cookie.setMaxAge(60 * 60 * 24);
					cookie.setPath("/");
					response.addCookie(cookie);
				}else{
					code = 2;//密码错误
				}
			}
			return CommonResult.success("success", code);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	
	/**
	 * 
	 * @Title: logout
	 * @Description: 退出登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="logout",method=RequestMethod.POST)
	public Object logout(HttpServletRequest request,HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		for(Cookie c:cookies){
			if("thor".equals(c.getName())){
				 Cookie cookie = new Cookie("thor","");//
                 cookie.setMaxAge(0);
                 cookie.setPath("/");
                 response.addCookie(cookie);
                 return CommonResult.success("success", "1");
			}
		}
		return CommonResult.defaultError("fail");
	}
	/**
	 * 
	 * @Title: getRole
	 * @Description: 获取用户的角色
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getRole",method=RequestMethod.POST)
	public Object getRole(int id){
		try {
			Role role = accountService.getRole(id);
			return CommonResult.success("success",role);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getList
	 * @Description: 查询账户
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="getList")
	public Object getList(int page,int pageSize,long orgId){
		try {
			Map<String, Object> map  = accountService.getList(page,pageSize,orgId);
			return CommonResult.success("success", map);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getById
	 * @Description: 通过id查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getById")
	public Object getById(long id){
		try {
			Account account = accountService.getById(id);
			return CommonResult.success("success", account);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: update
	 * @Description: 更新
	 * @param account
	 * @return
	 */
	@RequestMapping(value="update")
	public Object update(Account account){
		try {
			accountService.update(account);
			return CommonResult.success("success");
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail"); 
		}
	}
	/**
	 * 
	 * @Title: getRoleIdsByAccountId
	 * @Description: 通过用户id  查询角色
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value="getRoleIdsByAccountId")
	public Object getRoleIdsByAccountId(int accountId){
		try {
			List<Integer> list = accountService.getRoleIdsByAccountId(accountId);
			return CommonResult.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	
	/**
	 * 
	 * @Title: isRegisterByAccount
	 * @Description: 验证用户账号的唯一性
	 * @param account
	 * @return
	 */
	@RequestMapping(value="isRegisterByAccount")
	public Object isRegisterByAccount(String account){
		try {
			//code 1:异常  0：已被注册了 2：可以注册
			boolean isReg = accountService.isRegisterByAccount(account);
			return CommonResult.build(isReg?0:2, "success");
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
}
