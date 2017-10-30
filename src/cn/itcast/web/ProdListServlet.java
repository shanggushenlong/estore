package cn.itcast.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.itcast.domain.Product;
import cn.itcast.service.ProdService;
import cn.itcast.service.ProdServiceImpl;


@WebServlet("/ProdListServlet")
public class ProdListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdService service = new ProdServiceImpl();
		
		//1.调用service中的方法,查询所有的商品
		List<Product> list = service.prodList();
		//2.存入request域中,在jsp页面中展示出来
		request.setAttribute("list", list);
		request.getRequestDispatcher("/prodList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
