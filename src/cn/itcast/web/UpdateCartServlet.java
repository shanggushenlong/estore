package cn.itcast.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.Product;
import cn.itcast.service.ProdService;
import cn.itcast.service.ProdServiceImpl;


@WebServlet("/UpdateCartServlet")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//更改购物车页面中商品的数量
		
		//1.获取要改变商品的id和改变的数量
		String id = request.getParameter("id");
		int num = Integer.parseInt(request.getParameter("num"));
		//2.调用service根据id找到商品
		ProdService service = new ProdServiceImpl();
		Product prod = service.findProdById(id);
		//3.获取购物车
		Map<Product, Integer> cartmap = (Map<Product, Integer>) request.getSession().getAttribute("cartmap");
		//4.加入购物车;如果之前没有加入,数量为1,加入了数量+1;
		cartmap.put(prod, num);
		//5.回到购物车页面
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
