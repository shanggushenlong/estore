package cn.itcast.domain;

import java.io.Serializable;
import java.util.Map;

public class OrderInfo implements Serializable{
	private Order order;   //订单信息
	private Users users;   //用户信息
	private Map<Product, Integer> map;  //key为商品,value为数量
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Map<Product, Integer> getMap() {
		return map;
	}
	public void setMap(Map<Product, Integer> map) {
		this.map = map;
	}
	
}
