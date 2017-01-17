package com.zhsj.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhsj.model.Role;
import com.zhsj.model.StoreAccount;
import com.zhsj.model.StoreBindAccount;
import com.zhsj.service.StoreAccountService;
import com.zhsj.util.AES;
import com.zhsj.util.CommonResult;
import com.zhsj.util.Md5;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：门店账户
 * 类名称：com.zhsj.controller.StoreAccountController     
 * 创建人：xulinchuang
 * 创建时间：2017年1月3日 上午9:12:43
 */
@RestController
@RequestMapping("storeAccount")
public class StoreAccountController {

	@Autowired
	private StoreAccountService storeAccountService;
	
	/**
	 * 
	 * @Title: login
	 * @Description: 登录
	 * @param name
	 * @param password
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public Object login(String name,String password,String login,
			HttpSession session,HttpServletResponse response){
		try {
			StoreAccount storeAccount = storeAccountService.getByName(name);
			int code = 0;
			if(storeAccount != null){
				if(storeAccount.getPassword().equals(Md5.encrypt(password))){
					code = 1;
					session.setAttribute("user", storeAccount);
					session.setAttribute("flag", "storeAccount");
					Cookie cookie = new Cookie("thor",AES.encrypt(name+","+Md5.encrypt(password)+","+login));
					cookie.setMaxAge(60 * 60 * 24);
					cookie.setPath("/");
					response.addCookie(cookie);
				}else{
					code = 2;
				}
			}
			return CommonResult.success("success", code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: add
	 * @Description: 添加商户门店账户以及门店和账户的绑定
	 * @param storeAccount
	 * @param storeNo
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Object add(StoreAccount storeAccount,String storeNo,int roleId){
		try {
			int code = storeAccountService.add(storeAccount,storeNo,roleId);
			return CommonResult.success("success",code);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
		
	}
	/**
	 * 
	 * @Title: getList
	 * @Description: 根据商户门店编号查找门店账号
	 * @param storeNo
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="getList")
	public Object getList(String storeNo,int page,int pageSize){
		try {
			List<StoreAccount> list = storeAccountService.getList(storeNo,page,pageSize);
			return CommonResult.success("success", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return CommonResult.defaultError("fail"); 
		}
	}
	/**
	 * 
	 * @Title: getRole
	 * @Description: 通过商户门店用户的id  查询角色
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getRole")
	public Object getRole(int id){
		try {
			Role role = storeAccountService.getRole(id);
			return CommonResult.success("success", role);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @Title: getStoreNoByAccountId
	 * @Description: 通过当前用户登录的 accountID  获取关联的商户门店编号
	 * @param id
	 * @return
	 */
	@RequestMapping("getStoreNoByAccountId")
	public Object getStoreNoByAccountId(long id){
		try {
			StoreBindAccount storeBindAccount = storeAccountService.getSbaByAccountId(id);
			return CommonResult.success("success", storeBindAccount);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
		
	}
}
