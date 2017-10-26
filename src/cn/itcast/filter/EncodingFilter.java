package cn.itcast.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


@WebFilter("/EncodingFilter")
public class EncodingFilter implements Filter {

    //设置变量
	private FilterConfig config = null;
	private ServletContext context = null;
	private String encode = null;
    
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//1.响应乱码,从web.xml文件中读取
		response.setContentType("text/html,charset="+encode);
		//2.使用装饰类,改造和获取请求参数有关的方法,从而解决请求乱码	
		chain.doFilter(new MyHttpServletRequest((HttpServletRequest) request), response);
	}
	
	
	public class MyHttpServletRequest extends HttpServletRequestWrapper{
		
		private HttpServletRequest request = null;
		private boolean flag = true;
		
		public MyHttpServletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		@Override
		public Map<String, String[]> getParameterMap() {
			try {
				if("POST".equals(request.getMethod())){
					request.setCharacterEncoding(encode);
					return request.getParameterMap();
				}else if("GET".equals(request.getMethod())){
					Map<String,String[]> map = request.getParameterMap();
					if(flag){
						for(Map.Entry<String , String[]> entry:map.entrySet()){
							String vs[] = entry.getValue();
							for(int i=0;i<vs.length;i++){
								vs[i] = new String(vs[i].getBytes("iso8859-1"),encode);
							}
						}
						flag = false;
					}
					return map;
				}else{
					return request.getParameterMap();
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		@Override
		public String[] getParameterValues(String name) {
			// TODO Auto-generated method stub
			return super.getParameterValues(name);
		}
		@Override
		public String getParameter(String name) {
			// TODO Auto-generated method stub
			return super.getParameter(name);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
		this.context = fConfig.getServletContext();
		this.encode = context.getInitParameter("encode");   //从配置文件中读取encode的值
	}

}
