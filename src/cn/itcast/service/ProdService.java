package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.Product;

public interface ProdService {
	/**
	 * 添加商品
	 * @param product
	 */
	void addProd(Product product);
	/**
	 * 显示商品列表
	 * @return
	 */
	List<Product> prodList();
	
	/**
	 * 根据图片的id查询相应的图片
	 * @param id
	 * @return
	 */
	Product findProdById(String id);
	

}
