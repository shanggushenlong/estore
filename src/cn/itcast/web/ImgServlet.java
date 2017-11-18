package cn.itcast.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.jrockit.jfr.RequestableEvent;

import cn.itcast.domain.Product;
import cn.itcast.service.ProdService;
import cn.itcast.service.ProdServiceImpl;

@WebServlet("/ImgServlet")
public class ImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdService service = new ProdServiceImpl();
		String id = request.getParameter("id");
		Product prod = service.findProdById(id);
		if("s".equals(request.getParameter("size"))){
			String url = prod.getImgurl();
			//重新编写一个新的url
			url = url.substring(0,url.lastIndexOf("."))+"_s"+url.substring(url.lastIndexOf("."));
			request.getRequestDispatcher(url).forward(request, response);
		}else{
			//转发到img的地址
			request.getRequestDispatcher(prod.getImgurl()).forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
