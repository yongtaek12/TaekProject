package kr.co.taek.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.taek.beans.BoardInfoBean;
import kr.co.taek.beans.UserBean;
import kr.co.taek.mapper.UserMapper;
import kr.co.taek.service.TopMenuService;

public class TopMenuInterceptor implements HandlerInterceptor{
	
	/*인터셉터는 자동주입을 통해 빈을 주입받지 못하기 떄문 인터셉터를 등록하는 쪽에서 빈을 주입받은 후 생성자로 받아서 작업*/
	@Autowired
	private TopMenuService topMenuService;
	
	@Resource(name = "loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	

	public TopMenuInterceptor(TopMenuService topMenuService, UserBean loginUserBean) {
		this.topMenuService = topMenuService;
		this.loginUserBean = loginUserBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUserBean", loginUserBean);
		
		return true;
	}
}
