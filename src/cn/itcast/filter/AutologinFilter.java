package cn.itcast.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

import com.sun.corba.se.impl.encoding.IDLJavaSerializationInputStream;

import cn.itcast.domain.Users;
import cn.itcast.factory.BasicFacotry;
import cn.itcast.service.UserService;

@WebFilter("/AutologinFilter")
public class AutologinFilter implements Filter {

 
	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//1.只有登录才能自动登录
		if(req.getSession(false) == null || req.getSession().getAttribute("users") == null){
			//2.只有带了自动登录cookie的用户才能自动登录
			Cookie cs[] = req.getCookies();
			Cookie findC = null;
			if(cs!=null){
				for(Cookie c:cs){
					if("autologin".equals(c.getName())){
						findC = c;
						break;
					}
				}
			}
			if(findC != null){
				//3.只用自动登录cookie中的用户名和密码都正确才能自动登录
				String value = URLDecoder.decode(findC.getValue(),"utf-8");
				String username = value.split(":")[0];
				String password = value.split(":")[1];
				UserService service = BasicFacotry.getFactory().getInstance(UserService.class);
				Users users = service.findUserByNameAndPsw(username, password);
				if(users != null){
					req.getSession().setAttribute("users", users);
				}
			}
		}
		
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
