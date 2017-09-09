package com.mpicture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mpicture.dao.ProductMapper;
import com.mpicture.entity.PageClass;
import com.mpicture.entity.Product;
import com.mpicture.service.ProductService;

import tk.mybatis.mapper.entity.Example;

/**
 * 产品serverimple层
 * 
 * @author Administrator
 *
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	/** 产品server层*/
	@Autowired
	private ProductMapper productMapper;
	/**
	 * 根据状态获取商品列表
	 */
	@Override
	public List<Product> productList(PageClass pageClass,Integer type) {
		Page page=PageHelper.startPage(pageClass.getNowpage(), pageClass.getPagecount(),true);
		Example ex=new Example(Product.class);
		ex.createCriteria().andEqualTo("type", type);
		List<Product> products=productMapper.selectByExample(ex);
		pageClass.setCount((int)page.getTotal());
		return products;
	}
	@Override
	public Product queryOneByPrimary(Integer pid) {
		return productMapper.selectByPrimaryKey(pid);
	}
	/**
	 * 查询商品列表
	 * 
	 * @return 返回商品集合对象
	 */
	@Override
	public List<Product> commodityList() {
		return productMapper.selectAll();
	}
	/**
	 * 删除商品图片
	 */
	@Override
	public void delproduct(Product product) {
		productMapper.delete(product);
	}
	@Override
	public Integer addProduct(Product product) {
		return productMapper.insert(product);
	}
	@Override
	public List<Product> productSearch(String productname) {
		Example ex=new Example(Product.class);
		ex.createCriteria().andEqualTo("name", productname);
		return productMapper.selectByExample(ex);
	}
	
}
