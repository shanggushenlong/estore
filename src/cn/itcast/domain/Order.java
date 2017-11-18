package cn.itcast.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.sun.scenario.effect.impl.prism.PrImage;

/**
 * 对应于mysql数据库中的每一个表格,都有一个javabean
 * @author wcq
 *
 */
public class Order implements Serializable{
	
	private String id;
	private double money;
	private String receiverinfo;
	private int paystate;
	private Timestamp ordertime;
	private int user_id;
	private List<OrderItem> list;   //表明订单里面多出一个订单项;方便结算
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getReceiverinfo() {
		return receiverinfo;
	}
	public void setReceiverinfo(String receiverinfo) {
		this.receiverinfo = receiverinfo;
	}
	public int getPaystate() {
		return paystate;
	}
	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}
	public Timestamp getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public List<OrderItem> getList() {
		return list;
	}
	public void setList(List<OrderItem> list) {
		this.list = list;
	}
	
}









