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

}
