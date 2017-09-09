package com.mpicture.controller;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.mpicture.entity.PageClass;
import com.mpicture.entity.Product;
import com.mpicture.service.ProductService;

/**
 * controller - 产品 
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class ProductController {
	private static Logger log=Logger.getLogger(ProductController.class);
	/** 自动装配 产品server层*/
	@Autowired
	private ProductService productService;
	/** 自动装配 分页类 */
	@Autowired
	private PageClass pageClass;
	/**
	 * 获取商品列表
	 * @return
	 */
	@RequestMapping("/productList")
	public ModelAndView productList(Integer nowpage){
		ModelAndView model=new ModelAndView();
		pageClass.setNowpage(nowpage);
		model.addObject("products", productService.productList(pageClass,null));
		model.setViewName("admin/pages/product-list");
		return model;
	}
	
	/**
	 * 新增商品前发送模型
	 * @return
	 */
	@RequestMapping("/addProduct")
	public ModelAndView addProduct(){
		ModelAndView model=new ModelAndView();
		model.addObject("product", new Product());
		model.setViewName("admin/pages/product-add");
		return model;
	}
	/**
	 * 新增商品
	 * @return
	 */
	/*@RequestMapping("/productAdd")
	public ModelAndView productAdd(MultipartFile photo,Product product,HttpServletRequest request){
		ModelAndView model=new ModelAndView();
        String path = request.getServletContext().getRealPath("upload");  
        log.info(product);
        log.info(photo.length);
        String fileName = photo.getOriginalFilename();   
      
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        System.out.println(targetFile.getPath());
        
        try {  
        	photo.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        model.addObject("fileUrl", request.getContextPath()+"/upload/"+fileName);  
        model.setViewName("admin/pages/product-add");
		return model;
	}*/
	
	/**
	 * 查看商品列表
	 * @return
	 */
	@RequestMapping("/commodityList")
	public ModelAndView indentList(){
		ModelAndView model=new ModelAndView();
		List<Product> productList = new ArrayList<Product>();//存放商品对象
		productList = productService.commodityList();//后台查询商品集合对象
		model.addObject("products", productList);//添加属性
		model.setViewName("/admin/pages/product-list");//定位到页面上，在页面上取值
		return model;
	}
	
	
	@RequestMapping("/productAdd")
	public ModelAndView productAdd(HttpServletRequest request,MultipartFile cover,String name,
			Double price,Integer type,MultipartFile bighead){
		ModelAndView model=new ModelAndView("redirect:/admin/commodityList");
		String path = request.getServletContext().getRealPath("productImage"); 
		String fileName = cover.getOriginalFilename(); 
		String headname = bighead.getOriginalFilename();
        File targetFile = new File(path, fileName); 
        File headnameFile = new File(path, headname);
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        } 
        if(!headnameFile.exists()){
        	headnameFile.mkdirs();
        }
        try {  
        	cover.transferTo(targetFile);  
        	bighead.transferTo(headnameFile);
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Product product=new Product(null,name,"../productImage/"+fileName,price,type,"../productImage/"+headname);
        Integer status=productService.addProduct(product);
		return model;
	}
	
	
	/**
	 * 删除商品
	 */
	@RequestMapping("/delProduct")
	public ModelAndView delProduct(Integer pid){
		ModelAndView model=new ModelAndView();
		Product product=new Product();
		product.setPid(pid);
		productService.delproduct(product);
		model = new ModelAndView("redirect:/admin/commodityList");
		return model;
	}
	
	@RequestMapping("/productSearch")
	public ModelAndView productSearch(String productname){
		ModelAndView model=new ModelAndView();
		model.addObject("products", productService.productSearch(productname));//添加属性
		model.setViewName("/admin/pages/product-list");//定位到页面上，在页面上取值
		return model;
	}
	
}
