package com.zhsj.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：页面控制
 * 类名称：com.zhsj.controller.PageController     
 * 创建人：xulinchuang
 * 创建时间：2016年12月7日 下午5:43:55
 */
@Controller
public class PageController {

	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String test(){
		return "page/test";
	}
	
	/**
	 * 
	 * @param request
	 * @return 默认首页
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index(HttpServletRequest request,HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie:cookies){
			if("loginInfo".equals(cookie.getName())){
				System.out.println(cookie.getValue());
				try {
					response.sendRedirect("main");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "index2";
	}
	
	/**
	 * 登录以后的默认主页面
	 * @return
	 */
	@RequestMapping(value="main",method=RequestMethod.GET)
	public String main(){
		return "page/main";
	}
	/**
	 * 
	 * @Title: waterSummary
	 * @Description: 流水汇总
	 * @return
	 */
	@RequestMapping("page/userManager")
	public String waterSummary(){
		return "page/userManager/center";
	}
	/**
	 * 
	 * @Title: addbmUser
	 * @Description: TODO
	 * @return
	 */
	@RequestMapping("page/addBmUser")
	public String addbmUser(){
		return "page/userManager/content/addBmUser";
	}
	/**
	 * 
	 * @Title: bmUserList
	 * @Description: TODO
	 * @return
	 */
	@RequestMapping("page/bmUserList")
	public String bmUserList(){
		return "page/userManager/content/bmUserList";
	}
}
