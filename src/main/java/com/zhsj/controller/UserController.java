package com.zhsj.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhsj.model.BmUser;
import com.zhsj.service.BmUserService;
import com.zhsj.util.AES;
import com.zhsj.util.CommonResult;
import com.zhsj.util.Md5;



@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private BmUserService bmUserService;
    /**
     * 
     * @param name
     * @param password
     * @return 用户登录
     */
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public Object login(String name,String password,int login,HttpSession session,HttpServletResponse response){
		
		BmUser bmUser = bmUserService.getBmUser(name, Md5.encrypt(password));
		if(bmUser != null){
			session.setAttribute("user", bmUser);
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
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="logout",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object logout(HttpServletRequest request,HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		for(Cookie c:cookies){
			if("LI".equals(c.getName())){
				 Cookie cookie = new Cookie("LI","");//
                 cookie.setMaxAge(0);
                 cookie.setPath("/");
                 response.addCookie(cookie);
                 return CommonResult.success("success", "1");
			}
		}
		return CommonResult.defaultError("fail");
	}
}
