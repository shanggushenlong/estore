package cn.itcast.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.Product;
import cn.itcast.service.ProdService;
import cn.itcast.service.ProdServiceImpl;

@WebServlet("/ProdInfoServlet")
public class ProdInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.获取参数
		String id = request.getParameter("id");
		//2.根据图片的id去查找商品的相应的信息
		ProdService service = new ProdServiceImpl();
		Product prod = service.findProdById(id);
		if(prod == null){
			throw new RuntimeException("商品不存在");
		}
		//3.将商品的相关信息存储到requeset域中,在jsp页面中显示出来
		request.setAttribute("prod", prod);
		request.getRequestDispatcher("prodInfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
