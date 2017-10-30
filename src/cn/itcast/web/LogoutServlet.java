package cn.itcast.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		getSession(false) 等同于 如果当前Session没有就为null；
		if(request.getSession(false) != null){
			// 注销用户，使session失效。
			request.getSession().invalidate();
			
			//手动注销时,删除自动登录cookie
			Cookie autologinC = new Cookie("autologin","");
			autologinC.setMaxAge(0);
			autologinC.setPath("/"+request.getContextPath());
			response.addCookie(autologinC);
		}
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
