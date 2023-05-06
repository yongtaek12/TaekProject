package kr.co.taek.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.taek.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor{
	
	@Resource(name = "loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	public CheckLoginInterceptor(UserBean loginUserBean) {
		this.loginUserBean = loginUserBean;
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		if(loginUserBean.isUserLogin() == false) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/not_login");
			return false;
		}
		return true;
	}
}








