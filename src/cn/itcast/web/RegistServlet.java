package cn.itcast.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.domain.Users;
import cn.itcast.exception.MsgException;
import cn.itcast.factory.BasicFacotry;
import cn.itcast.service.UserService;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//使用定义的工厂类
		UserService service = BasicFacotry.getFactory().getInstance(UserService.class);
		
		try {
			//1.首先后台校验验证码
			//通过jsp页面写入的值与后台生成的验证码比较
			String valistr1 = request.getParameter("valistr");
			String valistr2 = (String) request.getSession().getAttribute("valistr");
			if(valistr1== null || valistr2 == null || !valistr1.equals(valistr2)){
				//自定义异常类
				throw new MsgException("验证码不正确");
			}
			//2.将从前台输入的数据封装到User中,进行信息的校验
			Users users = new Users();
			//通过javabean封装
			BeanUtils.populate(users, request.getParameterMap());
			users.checkData();
			
			//3.调用service方法,完成注册
			service.registUser(users);
			
			//4.回到主页
			response.getWriter().write("恭喜你注册成功,请到邮箱激活用户后在登录,3秒后自动跳转回到主页...");
			response.setHeader("Refresh", "3;url="+request.getContextPath()+"/index.jsp");
		} catch (MsgException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
