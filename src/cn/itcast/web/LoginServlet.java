package cn.itcast.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.Users;
import cn.itcast.exception.MsgException;
import cn.itcast.factory.BasicFacotry;
import cn.itcast.service.UserService;
import cn.itcast.utils.MD5Utils;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserService service = BasicFacotry.getFactory().getInstance(UserService.class);
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//1.得到用户名和密码
		String username = request.getParameter("username");
		String password = MD5Utils.md5(request.getParameter("password"));
		
//		System.out.println(username);
		//2.调用service方法,根据用户名和密码查找用户 
		Users users = service.findUserByNameAndPsw(username, password);
		
		//3.判断用户是否存在
		if(users == null){
			//如果为空,表明用户不存在
			throw new MsgException("用户名或密码不正确");
		}else{
			//如果不为空,表明用户存在,登录成功,返回到主页
			request.getSession().setAttribute("users", users);
			
			//如果勾选了记住用户名这个框,发送一个cookie保存用户名
			if("true".equals(request.getParameter("remname"))){
				//使用cookie的构造方法,构造出 名为rename 的cookie
				Cookie renameC = new Cookie("rename", URLEncoder.encode(users.getUsername(),"utf-8"));//由于用户名可能为中文,所以使用utf-8编码
				renameC.setMaxAge(3600*24*30);
				renameC.setPath("/"+request.getContextPath());
				response.addCookie(renameC);
			}else{
				//如果不想勾选这个框,那么重新生产发送一个cookie,覆盖掉原有的cookie
				Cookie renameC = new Cookie("rename", "");
				renameC.setMaxAge(0);
				renameC.setPath("/"+request.getContextPath());
				response.addCookie(renameC);
			}
			
			//如果勾选了30天内自动登录,发送cookie保存用户的登录信息
			if("true".equals(request.getParameter("autologin"))){
				Cookie autologinC = new Cookie("autologin", URLEncoder.encode(users.getUsername()+":"+users.getPassword(),"utf-8"));
				autologinC.setMaxAge(3600*24*30);
				autologinC.setPath("/"+request.getContextPath());
				response.addCookie(autologinC);
			}
			
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}









