package com.zhsj.intercepter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author xulinchaung
 * 控制登录cookie拦截
 *
 */
public class LoginCookieInterceptor implements HandlerInterceptor {

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
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

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
			if("loginInfo".equals(c.getName())){
				return true;
			}
		}
		response.getWriter().write("<script>parent.location.href=\""+basePath+"\";</script>");
		return false;
	}

}
