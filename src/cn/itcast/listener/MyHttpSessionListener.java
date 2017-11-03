package cn.itcast.listener;

import java.util.LinkedHashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.omg.CORBA.ARG_OUT;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.itcast.domain.Product;


@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

    public MyHttpSessionListener() {
    }


    public void sessionCreated(HttpSessionEvent arg0)  { 
    	//在session创建的时候,存入一个cartmap
        arg0.getSession().setAttribute("cartmap", new LinkedHashMap<Product,Integer>());
    }

    public void sessionDestroyed(HttpSessionEvent arg0)  { 
      //在session销毁的时候,将cartmap移除
    	arg0.getSession().removeAttribute("cartmap");
    }
	
}
