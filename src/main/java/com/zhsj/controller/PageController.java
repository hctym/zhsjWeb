package com.zhsj.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhsj.model.Account;
import com.zhsj.model.StoreAccount;
import com.zhsj.model.StoreBindAccount;
import com.zhsj.service.AccountService;
import com.zhsj.service.StoreAccountService;
import com.zhsj.util.AES;
import com.zhsj.util.SessionThreadLocal;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：页面控制跳转
 * 类名称：com.zhsj.controller.PageController     
 * 创建人：xulinchuang
 * 创建时间：2016年12月30日 下午5:01:54
 */
@Controller
public class PageController {
    
	@Autowired
	private AccountService accountService;
	@Autowired
	private StoreAccountService storeAccountService;
	/**
	 * 
	 * @Title: index
	 * @Description: 首页
	 * @return
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie:cookies){
			if("thor".equals(cookie.getName())){
				String value = cookie.getValue();
				String[] strs = AES.decrypt(value).split(",");
				String username = strs[0],md5password = strs[1],login = strs[2];
				if("1".equals(login)){
					Account account = accountService.getByNameAndMd5Password(username, md5password);
					if(account != null){
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("user", account);
						map.put("flag", "account");
						SessionThreadLocal.setSession(map);
						try {
							response.sendRedirect("main");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
						
				}else if("2".equals(login)){
					StoreAccount storeAccount = storeAccountService.getByNameAndMd5PassWord(username,md5password);
					if(storeAccount != null){
						Map<String, Object> map = new HashMap<String,Object>();
						map.put("user", storeAccount);
						map.put("flag", "storeAccount");
						SessionThreadLocal.setSession(map);
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
	 * 
	 * @Title: main
	 * @Description: 主页面
	 * @return
	 */
	@RequestMapping("main")
	public String main(){
		return "page/main";
	}
	/**
	 * 
	 * @Title: module
	 * @Description: 展示所有模块
	 * @return
	 */
	@RequestMapping("page/modules")
	public String module(int id,HttpServletRequest request){
		request.setAttribute("id", id);
		return "page/userManager/modules";
	}
	/**
	 * 
	 * @Title: addModule
	 * @Description: 添加模块
	 * @return
	 */
	@RequestMapping("page/addModule")
	public String addModule(){
		return "page/module/add";
	}
	/**
	 * 
	 * @Title: addRole
	 * @Description: 添加角色
	 * @return
	 */
	@RequestMapping("page/addRole")
	public String addRole(){
		return "page/userManager/addRole";
	}
	/**
	 * 
	 * @Title: roleList
	 * @Description: TODO
	 * @return
	 */
	@RequestMapping("page/roleList")
	public String roleList(){
		return "page/userManager/roleList";
	}
	/**
	 * 
	 * @Title: showRoleByAccountId
	 * @Description: 通过用户的id 查看该用户当前的角色
	 * @param id
	 * @return
	 */
	@RequestMapping("page/showRoleByAccountId")
	public ModelAndView showRoleByAccountId(long id){
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.setViewName("page/userManager/showRoleBy");
		return mv;
	}
	/**
	 * 
	 * @Title: addAccount
	 * @Description: 添加账户
	 * @return
	 */
	@RequestMapping("page/addAccount")
	public String addAccount(){
		return "page/userManager/addAccount";
	}
	/**
	 * 
	 * @Title: editAccount
	 * @Description: TODO
	 * @param id
	 * @param mv
	 * @return
	 */
	@RequestMapping("page/editAccount")
	public ModelAndView editAccount(long id){
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.setViewName("page/userManager/editAccount");
		return mv;
	}
	/**
	 * 
	 * @Title: addStore
	 * @Description: 添加门店信息
	 * @return
	 */
	@RequestMapping("page/addStore")
	public String addStore(String parentNo,HttpServletRequest request){
		request.setAttribute("parentNo", parentNo);
		return "page/storeManager/addStore";
	}
	/**
	 * 
	 * @Title: editStore
	 * @Description: 编辑门店
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("page/editStore")
	public String editStore(long id,HttpServletRequest request){
		request.setAttribute("id", id);
		return "page/storeManager/editStore";
	}
	/**
	 * 
	 * @Title: addcStore
	 * @Description: 添加子门店信息
	 * @return
	 */
	@RequestMapping("page/addcStore")
	public String addcStore(){
		return "page/storeManager/addcStore";
	}
	/**
	 * 
	 * @Title: storeList
	 * @Description: 展示门店列表(商户组织)
	 * @return
	 */
	@RequestMapping("page/storeList")
	public String storeList(){
		return "page/storeManager/storeList";
	}
	/**
	 * 
	 * @Title: stores
	 * @Description: 门店列表(storeNo)
	 * @return
	 */
	@RequestMapping("page/stores")
	public String stores(){
		return "page/storeManager/stores";
	}
	/**
	 * 
	 * @Title: addStoreAccout
	 * @Description: 添加门店账户
	 * @return
	 */
	@RequestMapping("page/addStoreAccount")
	public String addStoreAccout(String storeNo,HttpServletRequest request){
		request.setAttribute("storeNo", storeNo);
		return "page/storeManager/addStoreAccount";
	}
	/**
	 * 
	 * @Title: storeAccountList
	 * @Description: 某一个商户门店的账户列表
	 * @param storeNo
	 * @param request
	 * @return
	 */
	@RequestMapping("page/storeAccountList")
	public String storeAccountList(String storeNo,HttpServletRequest request){
		request.setAttribute("storeNo", storeNo);
		return "page/storeManager/storeAccountList";
	}
	/**
	 * 
	 * @Title: storeAddAccount
	 * @Description: 商户添加账户(店长、收银员)  (针对商户门店)
	 * @return
	 */
	@RequestMapping(value="page/store/addAccount")
	public String  storeAddAccount(String storeNo,HttpServletRequest request){
		request.setAttribute("storeNo", storeNo);
		return "page/storeManager/store/addStoreAccount";
	}
	/**
	 * 
	 * @Title: storeAccountList
	 * @Description: 门店账户的列表  (针对商户门店)
	 * @return
	 */
	@RequestMapping(value="page/store/accountlist")
	public String storeAccountList(HttpServletRequest request){
		Map<String,Object> map = SessionThreadLocal.getSession();
		String flag = (String)map.get("flag");
		String storeNo = "";
		if(!"account".equals(flag)){
			StoreAccount storeAccount = (StoreAccount)map.get("user");
			long  accountId  = storeAccount.getId();
			try {
				StoreBindAccount sbaAccount = storeAccountService.getSbaByAccountId(accountId);
				storeNo = sbaAccount.getStoreNo();
				request.setAttribute("storeNo", storeNo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "page/storeManager/store/storeAccountList";
	}
	/**
	 * 
	 * @Title: accountList
	 * @Description: 用户账户列表
	 * @return
	 */
	@RequestMapping("page/accountList")
	public String accountList(){
		return "page/userManager/accountList";
	}
	/**
	 * 
	 * @Title: error
	 * @Description: 页面不存在的跳转
	 * @return
	 */
	@RequestMapping("page/error")
	public String error(){
		return "page/error";
	}
	/**
	 * 
	 * @Title: orderList
	 * @Description: 订单
	 * @return
	 */
	@RequestMapping("page/orderList")
	public String orderList(){
		return "page/order/list";
	}
	/**
	 * 
	 * @Title: discountList
	 * @Description: 优惠列表
	 * @return
	 */
	@RequestMapping("page/discountList")
	public String discountList(){
		return "page/discount/list";
	}
	/**
	 * 
	 * @Title: addDiscount
	 * @Description: 商户门店 添加优惠
	 * @return
	 */
	@RequestMapping("page/addDiscount")
	public String addDiscount(){
		return "page/discount/addDiscount";
	}
	/**
	 * 
	 * @Title: orgList
	 * @Description: 组织架构
	 * @return
	 */
	@RequestMapping("page/orgList")
	public String orgList(){
		return "page/org/list";
	}
	/**
	 * 
	 * @Title: addRule
	 * @Description: 添加规则
	 * @return
	 */
	@RequestMapping("page/addRule")
	public String addRule(int id,HttpServletRequest request){
		request.setAttribute("id", id);
		return "page/discount/addRule";
	}
	/**
	 * 
	 * @Title: ruleList
	 * @Description: 规则列表
	 * @return
	 */
	@RequestMapping("page/ruleList")
	public String ruleList(int id,HttpServletRequest request){
		request.setAttribute("id", id);
		return "page/discount/ruleList";
	}
	/**
	 * 
	 * @Title: qrCode
	 * @Description: 二维码
	 * @return
	 */
	@RequestMapping("page/qrCode")
	public String qrCode(){
		return "page/qrCode/qrCode";
	}
	/**
	 * 
	 * @Title: payStyle
	 * @Description: 支付方式
	 * @return
	 */
	@RequestMapping("page/payStyle")
	public String payStyle(){
		return "page/pay/payStyle";
	}
	/**
	 * 
	 * @Title: addPayInfo
	 * @Description: 添加支付方式
	 * @return
	 */
	@RequestMapping("page/addPayInfo")
	public String addPayInfo(){
		return "page/pay/addPayInfo";
	}
	
	/**
	 * 
	 * @Title: editPayInfo
	 * @Description: 编辑支付方式
	 * @param id
	 * @return
	 */
	@RequestMapping("page/editPayInfo")
	public ModelAndView editPayInfo(int id){
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.setViewName("page/pay/editPayInfo");
		return mv;
	}
	
	/**
	 * 
	 * @Title: minus
	 * @Description: 立减
	 * @return
	 */
	@RequestMapping("page/minus")
	public String minus(){
		return "page/discount_new/minus";
	}
	/**
	 * 
	 * @Title: discount
	 * @Description: 折扣
	 * @return
	 */
	@RequestMapping("page/discount")
	public String discount(){
		return "page/discount_new/discount";
	}
	/**
	 * 
	 * @Title: discounts
	 * @Description: 优惠列表
	 * @return
	 */
	@RequestMapping("page/discounts")
	public  String discounts(){
		return "page/discount_new/list";
	}
	
}
