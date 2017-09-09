package com.mpicture.service;

import java.util.List;

import com.mpicture.entity.PageClass;
import com.mpicture.entity.Product;

public interface ProductService {
	/**
	 * 分页查询商品列表
	 * @param pageClass
	 * @param type
	 * @return
	 */
	List<Product> productList(PageClass pageClass,Integer type);
	/**
	 * id查询Product
	 * @param pid
	 * @return
	 */
	Product queryOneByPrimary(Integer pid);
	/**
	 * 查询商品集合对象
	 * @return
	 */
	List<Product> commodityList();
	/**
	 * 删除商品图片
	 * @param product
	 */
	void delproduct(Product product);
	/**
	 * 新增商品
	 * @param product
	 * @return
	 */
	Integer addProduct(Product product);
	/**
	 * 根据name查询商品
	 * @param productname
	 * @return
	 */
	List<Product> productSearch(String productname);
}
