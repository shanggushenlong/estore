package cn.itcast.filter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

@WebFilter("/PrivilegeFilter")
public class PrivilegeFilter implements Filter {

	private List<String> admin_list = new ArrayList<String>();
	private List<String> user_list = new ArrayList<String>();
  
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//获取浏览器访问的资源路径
		String uri = req.getRequestURI();
		if(admin_list.contains(uri) || user_list.contains(uri)){
			//表明需要权限
			//需要权限,但是没有登录,则提示先登录在访问
			if(req.getSession() == null || req.getSession().getAttribute("user") == null){
				resp.getWriter().write("当前资源需要权限,请先登录");
				resp.setHeader("Refresh","1;uri="+req.getContextPath()+"/login.jsp");
				return ;
			}else{
				//需要权限,也登录了,就检查权限
				User user = (User) req.getSession().getAttribute("user");
				System.out.println(user.getRoles());
				if(admin_list.contains(uri) && "admin".equals(user.getRoles())){
					chain.doFilter(req, resp);
				}else if(user_list.contains(uri) && "user".equals(user.getRoles())){
					chain.doFilter(req, resp);
				}else{
					throw new RuntimeException("权限不足,请联系管理员!!!");
				}
			}
		}else{
			//表明不需要权限
			chain.doFilter(req, resp);	
		}
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
		//读取文件
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fConfig.getServletContext().getRealPath("WEB-INF/admin.txt")));
			String line = null;
			while((line = reader.readLine()) != null){
				admin_list.add(line);
			}
			
			reader = new BufferedReader(new FileReader(fConfig.getServletContext().getRealPath("WEB-INF/user.txt")));
			line = null;
			while((line = reader.readLine()) != null){
				user_list.add(line);
			}
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
