package com.zhsj.intercepter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhsj.model.Account;
import com.zhsj.model.StoreAccount;
import com.zhsj.service.AccountService;
import com.zhsj.service.StoreAccountService;
import com.zhsj.util.AES;
import com.zhsj.util.SessionThreadLocal;



/**
 * 
 * @author xulinchaung
 * 控制登录cookie拦截
 *
 */
public class LoginCookieInterceptor implements HandlerInterceptor {

    @Autowired
	private AccountService accountService;
	@Autowired
	private StoreAccountService storeAccountService;
	
	private String[] paths;
	
	
	public String[] getPaths() {
		return paths;
	}

	public void setPaths(String[] paths) {
		this.paths = paths;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		Cookie[] cookies = request.getCookies();
		AntPathMatcher apm = new AntPathMatcher();
		String requestPath = request.getServletPath();
		for(String str:paths){
			if(apm.match(str,requestPath)){
				return true;
			}
		}
		for(Cookie c:cookies){
			if("thor".equals(c.getName())){
				String value = c.getValue();
				String[] strs = AES.decrypt(value).split(",");
				String username = strs[0],md5password = strs[1];
				if("1".equals(strs[2]) ){
					Account account = accountService.getByNameAndMd5Password(username, md5password);
					if(account != null){
						Map<String, Object> map = new HashMap<String,Object>();
						map.put("user", account);
						map.put("flag", "account");
						SessionThreadLocal.setSession(map);
						return true;
					}
				}else if("2".equals(strs[2])){
					StoreAccount storeAccount = storeAccountService.getByNameAndMd5PassWord(username,md5password);
					if(storeAccount != null){
						Map<String, Object> map = new HashMap<String,Object>();
						map.put("user", storeAccount);
						map.put("flag", "storeAccount");
						SessionThreadLocal.setSession(map);
						return true;
					}
				}else{
					return false;
				}
				break;
			}
		}
		response.getWriter().write("<script>parent.location.href=\""+basePath+"\";</script>");
		return false;
	}

}
