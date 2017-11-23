package cn.itcast.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.itcast.domain.Order;
import cn.itcast.domain.OrderItem;
import cn.itcast.utils.DaoUtils;
import cn.itcast.utils.TransactionManager;

public class OrderDaoImpl implements OrderDao{
	
	/**
	 * 直接操作数据库
	 * @throws SQLException 
	 */
//	@Override
//	public void addOrder(Order order) {
//		String sql = "insert into orders values (?,?,?,?,null,?)";
//		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
//		try {
//			runner.update(sql,order.getId(),order.getMoney(),order.getReceiverinfo(),order.getPaystate(),order.getUser_id());
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
	@Override                       //不能传入数据源了,需要在后面手动传入connection,且产生的异常必须要抛出                 
	public void addOrder(Order order) throws SQLException {
		String sql = "insert into orders values (?,?,?,?,null,?)";
		QueryRunner runner = new QueryRunner();     
		runner.update(TransactionManager.getConnection(),sql,order.getId(),order.getMoney(),order.getReceiverinfo(),order.getPaystate(),order.getUser_id());
	}

	
//	@Override
//	public void addOrderItem(OrderItem item) {
//		String sql = "insert into orderitem values (?,?,?)";
//		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
//		try {
//			runner.update(sql, item.getOrder_id(),item.getProduct_id(),item.getBuynum());
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
	@Override
	public void addOrderItem(OrderItem item) throws SQLException {
		String sql = "insert into orderitem values (?,?,?)";
		QueryRunner runner = new QueryRunner();
		runner.update(TransactionManager.getConnection(),sql, item.getOrder_id(),item.getProduct_id(),item.getBuynum());
	
	}


	@Override
	public List<Order> findOrderByUserId(int user_id) {
		String sql = "select * from orders where user_id = ?";
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		try {
			return runner.query(sql, new BeanListHandler<Order>(Order.class),user_id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	@Override
	public List<OrderItem> findOrderItemByOrderId(String id) {
		String sql = "select * from orderitem where order_id = ?";
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		try {
			return runner.query(sql, new BeanListHandler<OrderItem>(OrderItem.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	@Override
	public void delOrderItemByOrderId(String id) {
		String sql = "delete from orderitem where order_id = ?";
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		try {
			runner.update(sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void delOrderByOrderId(String id) {
		String sql = "delete from orders where id = ?";
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		try {
			runner.update(sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}









