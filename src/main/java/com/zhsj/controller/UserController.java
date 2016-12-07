package com.zhsj.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhsj.util.CommonResult;



@Controller
@RequestMapping("user")
public class UserController {
    /**
     * 
     * @param name
     * @param password
     * @return 用户登录
     */
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public Object login(String name,String password,HttpSession session,HttpServletResponse response){
		if("admin".equals(name) && "123456".equals(password)){
	        session.setAttribute("user", name);
			Cookie cookie = new Cookie("loginInfo",name+password);
			cookie.setMaxAge(60 * 60);
			cookie.setPath("/");
			response.addCookie(cookie);
			return CommonResult.success("success", "1");
		}
		return CommonResult.defaultError("fail");
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
			if("loginInfo".equals(c.getName())){
				 Cookie cookie = new Cookie("loginInfo","");//
                 cookie.setMaxAge(0);
                 cookie.setPath("/");
                 response.addCookie(cookie);
                 return CommonResult.success("success", "1");
			}
		}
		return CommonResult.defaultError("fail");
	}
}
