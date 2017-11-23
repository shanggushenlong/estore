package cn.itcast.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.Order;
import cn.itcast.factory.BasicFacotry;
import cn.itcast.service.OrderService;


@WebServlet("/DelOrderServlet")
public class DelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * 删除订单
     */
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService service = BasicFacotry.getFactory().getInstance(OrderService.class);
		//1.获取订单的id
		String id = request.getParameter("id");
		//2.调用service中的方法,根据订单的id,删除订单
		service.delOrderById(id);
		//3.等待3秒后,回到订单列表页面
		response.setHeader("Refresh", "1;url="+request.getContextPath()+"/OrderListServlet");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}

}
