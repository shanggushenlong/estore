package cn.itcast.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.domain.Order;
import cn.itcast.domain.OrderInfo;

public interface OrderService {
	/**
	 * 增加订单
	 * @param order
	 */
	void addOrder(Order order);
	
	/**
	 * 根据用户user_id,查找相关订单信息
	 * @param user_id
	 * @return
	 * @throws  
	 */
	List<OrderInfo> findOrderByUserId(int user_id);
	
	/**
	 * 根据订单的id删除订单
	 * @param id
	 */
	void delOrderById(String id);
	
	/**
	 * 根据订单id查询相应的订单
	 * @param id 订单id
	 * @return
	 */
	Order findOrderById(String id);
	
	/**
	 * 修改支付状态
	 * @param id 订单id 
	 * @param paystate 支付状态
	 */
	void updateState(String id, int paystate);

}
