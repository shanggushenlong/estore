package cn.itcast.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.itcast.dao.OrderDao;
import cn.itcast.dao.ProdDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.Order;
import cn.itcast.domain.OrderInfo;
import cn.itcast.domain.OrderItem;
import cn.itcast.domain.Product;
import cn.itcast.domain.Users;
import cn.itcast.factory.BasicFacotry;
import cn.itcast.utils.TransactionManager;

public class OrderServiceImpl implements OrderService{
	
	private OrderDao order_dao = BasicFacotry.getFactory().getInstance(OrderDao.class);
	private ProdDao prod_dao = BasicFacotry.getFactory().getInstance(ProdDao.class);
	private UserDao user_dao = BasicFacotry.getFactory().getInstance(UserDao.class);
	
	
	@Override
	public void addOrder(Order order) {
		try {
			TransactionManager.startTran(); //开启一个事物
			//1.增加订单
			order_dao.addOrder(order);
			
			for(OrderItem item:order.getList()){
				//2.扣除商品记录
				Product product = prod_dao.findById2(item.getProduct_id());
				if(product.getPnum() < item.getBuynum()){
					//判断库存数量是否充足
					throw new RuntimeException("库存"+product.getId()+":"+product.getName()+"数量不足");     
				}
				prod_dao.updatePnum(product.getId(),product.getPnum()-item.getBuynum());	
				//3.增加订单项记录
				order_dao.addOrderItem(item);
			}
			TransactionManager.commit();  //当订单完成后,提交事物	
		} catch (Exception e) {
			//如果出现异常,事物回滚
			TransactionManager.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			TransactionManager.release();  //资源释放
		}
	}


	@Override
	public List<OrderInfo> findOrderByUserId(int user_id){
		//1.调用order_dao 中的方法,查询指定用户的所有订单
		List<Order> order_list = order_dao.findOrderByUserId(user_id);
		//2.遍历这些订单,将订单相关的信息存入orderInfo对象中,在将OrderInfo存入到一个list集合中
		Users users = user_dao.findById(user_id);
//		System.out.println(users.toString());
		List<OrderInfo> oi_list = new ArrayList<OrderInfo>();
		for(Order order:order_list){
			OrderInfo oi = new OrderInfo();
			oi.setOrder(order);
			oi.setUsers(users);
			Map<Product, Integer> map = new HashMap<Product,Integer>();
			List<OrderItem> list = order_dao.findOrderItemByOrderId(order.getId());
			for(OrderItem item:list){
				Product product = prod_dao.findById(item.getProduct_id());
				map.put(product, item.getBuynum());
			}
			oi.setMap(map);
			oi_list.add(oi);
		}	
		//3.返回
		return oi_list;
	}

}









