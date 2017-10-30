package cn.itcast.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.itcast.domain.Product;
import cn.itcast.utils.DaoUtils;

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
}
