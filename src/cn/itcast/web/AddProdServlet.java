package cn.itcast.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.domain.Product;
import cn.itcast.factory.BasicFacotry;
import cn.itcast.service.ProdService;
import cn.itcast.service.ProdServiceImpl;
import cn.itcast.utils.PicUtils;

@WebServlet("/AddProdServlet")
public class AddProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 商品添加
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		try {
			String upload = "/upload";   //设置图片上传到服务器保存的地址
			String temp = "/temp";       //设置文件缓冲区
			
			Map<String, String> map = new HashMap<String,String>();
			
			//1.上传商品图片到服务器
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024*100);   
			//设置临时文件缓冲区
			factory.setRepository(new File(this.getServletContext().getRealPath(temp)));
			 
			//将工厂参数传入
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			
			if(!ServletFileUpload.isMultipartContent(request)){
				throw new RuntimeException("请使用正确的表单进行提交");
			}
			
			fileUpload.setFileSizeMax(1024*1024*10); //设置单个文件上传的大小
			fileUpload.setSizeMax(1024*1024*20);     //设置整个表单上传的大小
			fileUpload.setHeaderEncoding("utf-8");   //设置文件上传的编码
			
			//得到一个关于上传的集合
			List<FileItem> list = fileUpload.parseRequest(request);
			//遍历集合
			for(FileItem item : list){
				//判断是否为一个普通字段
				if(item.isFormField()){
					String name = item.getFieldName();   //得到字段名称
					String value = item.getString(this.getServletContext().getInitParameter("encode"));
					map.put(name, value);
				}else{
					//如果是一个文件上传,而不是一个字段
					String realname = item.getName();
					String uuidname = UUID.randomUUID().toString()+"_"+realname;
					String hash = Integer.toHexString(uuidname.hashCode());
					
					for(char c:hash.toCharArray()){
						upload = upload+"/"+c;
					}
					new File(this.getServletContext().getRealPath(upload)).mkdirs();
					//将imgurl封装到javabean中
					map.put("imgurl", upload+"/"+uuidname);
					
					InputStream in = item.getInputStream();
					OutputStream out = new FileOutputStream(this.getServletContext().getRealPath(upload)+"/"+uuidname);
					
					//文件复制读写
					byte[] bs = new byte[1024];
					int i=0;
					while((i=in.read(bs))!= -1){
						out.write(bs, 0, i);
					}
					
					in.close();
					out.close();
					
					//-------生成缩略图,将图片等比例缩小,减轻jsp页面加载的压力
					PicUtils picUtils = new PicUtils(this.getServletContext().getRealPath(upload)+"/"+uuidname);
					picUtils.resizeByHeight(120);
					
					item.delete();  //删除临时文件
				}
			}
			
			//2.调用service方法,增加商品
			ProdService service = new ProdServiceImpl();
			Product product = new Product();
			product.setId(UUID.randomUUID().toString());
			BeanUtils.populate(product, map);
			service.addProd(product);
			
			//3.回到主页
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} catch (FileUploadException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
