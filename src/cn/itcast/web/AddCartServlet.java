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

@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取要加入购物车的商品的id
		String id = request.getParameter("id");
		//2.调用service根据id找到商品
		ProdService service = new ProdServiceImpl();
		Product prod = service.findProdById(id);
		//3.获取购物车
		Map<Product, Integer> cartmap = (Map<Product, Integer>) request.getSession().getAttribute("cartmap");
		//4.加入购物车;如果之前没有加入,数量为1,加入了数量+1;
		cartmap.put(prod, cartmap.containsKey(prod) ? cartmap.get(prod)+1 : 1);
		//5.回到购物车页面
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
