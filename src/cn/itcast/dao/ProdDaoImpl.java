package cn.itcast.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.domain.Product;
import cn.itcast.utils.DaoUtils;
import cn.itcast.utils.TransactionManager;

public class ProdDaoImpl implements ProdDao{

	@Override
	public void addProd(Product product) {
		String sql = "insert into products values(?,?,?,?,?,?,?)";
		
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		try {
			runner.update(sql,product.getId(),product.getName(),product.getPrice(),product.getCategory(),product.getPnum(),product.getImgurl(),product.getDescription());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public List<Product> prodList() {
		String sql = "select * from products";
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		try {
			return runner.query(sql, new BeanListHandler<Product>(Product.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public Product findById(String id) {
		String sql = "select * from products where id =?";
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		try {
			return runner.query(sql, new BeanHandler<Product>(Product.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updatePnum(String id, int pnum) throws SQLException {
		String sql = "update products set pnum = ? where id = ?";
		QueryRunner runner = new QueryRunner();
		runner.update(TransactionManager.getConnection(),sql,pnum,id);	
	}

	@Override
	public Product findById2(String product_id) throws SQLException {
		String sql = "select * from products where id =? for update";   //在此处加入 "for update"两个关键字,加入排它锁
		QueryRunner runner = new QueryRunner();
		return runner.query(TransactionManager.getConnection(),sql, new BeanHandler<Product>(Product.class),product_id);
	}	
}










