package cn.itcast.dao;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.domain.Order;
import cn.itcast.domain.OrderItem;

public interface OrderDao {
	
	/**
	 * 增加订单
	 * @param order
	 * @throws SQLException 
	 */
	void addOrder(Order order) throws SQLException;
	
	/**
	 * 增加订单项
	 * @param item
	 * @throws SQLException 
	 */
	void addOrderItem(OrderItem item) throws SQLException;
	
	/**
	 * 根据用户id查询指定用户的订单
	 * @param user_id
	 * @return
	 */
	List<Order> findOrderByUserId(int user_id);
	
	/**
	 * 根据订单id查询所有订单项
	 * @param id
	 * @return
	 */
	List<OrderItem> findOrderItemByOrderId(String id);

}
