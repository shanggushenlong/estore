package cn.itcast.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.apache.commons.beanutils.BeanUtils;

import com.sun.corba.se.spi.protocol.RequestDispatcherRegistry;

import cn.itcast.domain.Order;
import cn.itcast.domain.OrderItem;
import cn.itcast.domain.Product;
import cn.itcast.domain.Users;
import cn.itcast.factory.BasicFacotry;
import cn.itcast.service.OrderService;
import jdk.nashorn.internal.runtime.ListAdapter;


@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 增加订单
		 */		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		Users users =  (Users) request.getSession().getAttribute("users");
		try {
			//1.封装数据	
			Order order = new Order();
//			System.out.println(request.getParameterMap());
			BeanUtils.populate(users, request.getParameterMap());
			//设置订单的ID
			order.setId(UUID.randomUUID().toString());
			//设置支付状态,默认为0,表示未支付
			order.setPaystate(0);
			//设置该订单所属的用户,属于当前登录的用户
			order.setUser_id(users.getId());
			order.setReceiverinfo(request.getParameter("receiverinfo"));   //封装地址
			
			//对于金额与订单项,不能从前台获得,只能有后台自己去计算
			//全部遍历,从购物车中获得
			Map<Product, Integer> cartmap = (Map<Product, Integer>) request.getSession().getAttribute("cartmap");
			double money = 0;
			List<OrderItem> list = new ArrayList<OrderItem>();
			for(Map.Entry<Product, Integer> entry:cartmap.entrySet()){
				money = money + entry.getKey().getPrice()  * entry.getValue();
//				System.out.println(entry.getValue());
				
				OrderItem item = new OrderItem();
				item.setOrder_id(order.getId());
				item.setProduct_id(entry.getKey().getId());
				item.setBuynum(entry.getValue());
				list.add(item);
			}
			order.setMoney(money);
			order.setList(list);
			//2.调用service中的方法增加订单
			OrderService service = BasicFacotry.getFactory().getInstance(OrderService.class);
			service.addOrder(order);
			//3.清空购物车
			cartmap.clear();
			//4.回到主页
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
