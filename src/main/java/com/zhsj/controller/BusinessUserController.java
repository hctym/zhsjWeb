package com.zhsj.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhsj.model.BusinessUser;
import com.zhsj.service.BusinessUserService;
import com.zhsj.util.AES;
import com.zhsj.util.CommonResult;
import com.zhsj.util.Md5;

@RestController
@RequestMapping("businessUser")
public class BusinessUserController {

	@Autowired
	private BusinessUserService businessUserService;
	
	
	@RequestMapping(value="login",method=RequestMethod.POST)
    public Object login(String name,String password,int login,HttpSession session,HttpServletResponse response){
		BusinessUser User = businessUserService.getUser(name, Md5.encrypt(password));
		if(User != null){
			session.setAttribute("user", User);
			Cookie cookie = new Cookie("LI",AES.encrypt(name+","+Md5.encrypt(password)+","+login));
			cookie.setMaxAge(60 * 60);
			cookie.setPath("/");
			response.addCookie(cookie);
			return CommonResult.success("success", "1");
		}else{
			return CommonResult.defaultError("fail");
		}
		
	}
	/**
	 * 
	 * @Title: addBusinessUser
	 * @Description: 给商家 添加商家用户(店长或者员工)
	 * @param businessUser
	 * @param businessInfoId
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Object addBusinessUser(BusinessUser businessUser,int businessInfoId){
		try {
			int id = businessUserService.addBusinessUser(businessInfoId, businessUser);
			return CommonResult.success("添加门店用户成功", id);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("添加门店用户失败");
		}
	}
	/**
	 * 
	 * @Title: getbuserBybinfoId
	 * @Description: 通过商家信息id 获取该商家的用户(店长和员工)
	 * @param businessInfoId
	 * @return
	 */
	@RequestMapping(value="getbuserBybinfoId")
	public Object getbuserBybinfoId(int businessInfoId){
		try {
			List<BusinessUser> list = businessUserService.getBusinessUsersByBusinessInfoId(businessInfoId);
			return CommonResult.success("查询商家用户成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("查询商家用户失败");
		}
	}
}
