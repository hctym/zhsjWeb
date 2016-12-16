package com.zhsj.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhsj.model.BmUser;
import com.zhsj.model.BusinessUser;
import com.zhsj.service.BmUserService;
import com.zhsj.service.BusinessUserService;
import com.zhsj.util.AES;


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

	@Autowired
	private BmUserService bmUserService;
	@Autowired
	private BusinessUserService businessUserService;
	/**
	 * 
	 * @Title: test
	 * @Description: TODO
	 * @return
	 */
	@RequestMapping(value="/page/error",method=RequestMethod.GET)
	public String test(){
		return "page/error";
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
			if("LI".equals(cookie.getName())){
				String value = cookie.getValue();
				String[] strs = AES.decrypt(value).split(",");
				String username = strs[0],md5password = strs[1],login = strs[2];
				if("1".equals(login)){
					BmUser bmUser = bmUserService.getBmUser(username, md5password);
					if(bmUser != null){
						request.getSession().setAttribute("user", bmUser);
						try {
							response.sendRedirect("main");
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				}else if("2".equals(login)){
					BusinessUser user = businessUserService.getUser(username, md5password);
					if(user != null){
						request.getSession().setAttribute("user", user);
						try {
							response.sendRedirect("main");
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				}else{
					return "index2";
				}
				break;
			}
		}
		return "index2";
	}
	
	/**
	 * 管理员登录以后的默认主页面
	 * @return
	 */
	@RequestMapping(value="main",method=RequestMethod.GET)
	public String main(){
		return "page/main";
	}
	/**
	 * 
	 * @Title: shopIndex
	 * @Description: 商家登录默认首页
	 * @return
	 */
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String shopIndex(){
		return "page/shop/index";
	}
	/**
	 * 
	 * @Title: shopInfo
	 * @Description: 商家信息
	 * @return
	 */
	@RequestMapping(value="page/shopInfo")
	public String shopInfo(int id,HttpServletRequest request){
		request.setAttribute("id", id);
		return "page/shop/shopInfo";
	}
	/**
	 * 
	 * @Title: shopUsers
	 * @Description: 查询该商家下面的用户
	 * @return
	 */
	@RequestMapping(value="page/shopUsers")
	public String shopUsers(int id,HttpServletRequest request){
		request.setAttribute("id", id);
		return "page/shop/shopUsers";
	}
	/**
	 * 
	 * @Title: addShopUser
	 * @Description: TODO
	 * @param type  类型 添加1店长、2员工
	 * @param id  门店id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="page/addShopUser")
	public String addShopUser(int type,int id,HttpServletRequest request){
		request.setAttribute("type", type);
		request.setAttribute("id", id);
		return "page/shop/addShopUser";
	}
	/**
	 * 
	 * @Title: userManager
	 * @Description: 用户管理
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
	/**
	 * 
	 * @Title: addUserGroup
	 * @Description: 添加用户组
	 * @return
	 */
	@RequestMapping("page/addUserGroup")
	public String  addUserGroup(){
		return "page/userManager/content/addUserGroup";
	}
	/**
	 * 
	 * @Title: getUserGroups
	 * @Description: 获取用户组列表
	 * @return
	 */
	@RequestMapping("page/userGroupList")
	public String getUserGroups(){
		return "page/userManager/content/userGroupList";
	}
	/**
	 * 
	 * @Title: modules
	 * @Description: 给用户组分配模块
	 * @return
	 */
	@RequestMapping("page/modules")
	public String modules(int id,HttpServletRequest request){
		request.setAttribute("id", id);
		return "page/userManager/content/modules";
	}
	/**
	 * 
	 * @Title: shopManager
	 * @Description: 门店管理
	 * @return
	 */
	@RequestMapping("page/shopManager")
	public String shopManager(){
		return "page/shopManager/center";
	}
	/**
	 * 
	 * @Title: addShop
	 * @Description: 添加门店
	 * @return
	 */
	@RequestMapping("page/addShop")
	public String addShop(){
		return "page/shopManager/content/addShop";
	}
	/**
	 * 
	 * @Title: shopList
	 * @Description: 门店list
	 * @return
	 */
	@RequestMapping("page/shopList")
	public String shopList(){
		return "page/shopManager/content/shopList";
	}
	/**
	 * 
	 * @Title: shopPaySet
	 * @Description: 收款设置
	 * @return
	 */
	@RequestMapping("page/shopPaySet")
	public String shopPaySet(){
		return "page/shopPaySet/center";
	}
	/**
	 * 
	 * @Title: wechatPay
	 * @Description: 微信支付
	 * @return
	 */
	@RequestMapping("page/wechatPay")
	public  String wechatPay(){
		return "page/shopPaySet/content/wechatPay";
	}
	/**
	 * 
	 * @Title: aliPay
	 * @Description: 支付宝
	 * @return
	 */
	@RequestMapping("page/aliPay")
	public String aliPay(){
		return "page/shopPaySet/content/aliPay";
	}
}
