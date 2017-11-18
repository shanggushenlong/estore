package cn.itcast.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.OrderInfo;
import cn.itcast.domain.Users;
import cn.itcast.factory.BasicFacotry;
import cn.itcast.service.OrderService;
import sun.security.krb5.SCDynamicStoreConfig;


@WebServlet("/OrderListServlet")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService service = BasicFacotry.getFactory().getInstance(OrderService.class);
		//1.获取当前用户
		Users users = (Users) request.getSession().getAttribute("users");
//		System.out.println(users.toString());
		//2.调用service查询当前用户的所有的订单信息(根据用户的id查询)
		List<OrderInfo> list = service.findOrderByUserId(users.getId());
		//3.存入request域带到orderList.jsp页面显示
		request.setAttribute("list", list);
		request.getRequestDispatcher("orderList.jsp").forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
