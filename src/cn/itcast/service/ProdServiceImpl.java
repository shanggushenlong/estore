package cn.itcast.service;

import java.util.List;

import cn.itcast.dao.ProdDaoImpl;
import cn.itcast.domain.Product;

public class ProdServiceImpl implements ProdService{
	
	
	@Override
	public void addProd(Product product) {
		new ProdDaoImpl().addProd(product);
	}

	@Override
	public List<Product> prodList() {
		return new ProdDaoImpl().prodList();
	}

	@Override
	public Product findProdById(String id) {
		return new ProdDaoImpl().findById(id);
	}

}
