package cn.itcast.dao;

import java.sql.SQLException;

import cn.itcast.domain.Product;

public interface ProdDao {
	/**
	 * 添加商品
	 * @param product
	 */
	void addProd(Product product);

	/**
	 * 根据id查询商品
	 * @param id
	 * @return
	 */
	Product findById(String id);
	
	/**
	 * 根据id查询商品 带有事物控制
	 * @param product_id
	 * @return
	 * @throws SQLException 
	 */
	Product findById2(String product_id) throws SQLException;
	
	/**
	 * 修改库存数量
	 * @param id 要修改的商品id
	 * @param pnum 要将商品修改为pnum数量
	 * @throws SQLException 
	 */
	void updatePnum(String id, int pnum) throws SQLException;

	

}
