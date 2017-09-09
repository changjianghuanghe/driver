package com.mpicture.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.mpicture.entity.Capital;
import com.mpicture.entity.Indent;
import com.mpicture.entity.PageClass;
import com.mpicture.entity.Product;
import com.mpicture.entity.Users;
import com.mpicture.service.CapitalService;
import com.mpicture.service.IndentService;
import com.mpicture.service.ProductService;
import com.mpicture.service.UserService;
import com.mpicture.util.Constant;
import com.mpicture.util.IndentStatusEnum;
import com.mpicture.util.SafeUtil;


/**
 * controller - 用户
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/index")
public class UserController {
	private static Logger log = Logger.getLogger(UserController.class);
	/** 自动装配 用户server层 */
	@Autowired
	private UserService userService;
	/** 地洞装配 订单server层 */
	@Autowired
	private IndentService indentService;
	/** 资金server层 */
	@Autowired
	private CapitalService capitalService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PageClass pageClass;
	
	
	/**
	 * 获取Httpsession的user
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSession", produces = "application/json; charset=utf-8")
	public String getSessionUser(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", (Users)session.getAttribute("user"));
		return JSON.toJSONString(map);
	}

	/**
	 * 根据id查询用户
	 * 
	 * @return
	 */
	@RequestMapping("/getUser")
	public ModelAndView getUser(Integer uid) {
		ModelAndView model = new ModelAndView();
		model.addObject("user", userService.getUserById(uid));
		return model;
	}

	/**
	 * 查询我的所有订单
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listMyIndent", produces = "application/json; charset=utf-8")
	public String listMyIndent(Integer uid, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Users user = (Users) session.getAttribute("user");
		map.put("indent", indentService.listMyIndent(user.getUid()));
		return JSON.toJSONString(map);
	}

	/**
	 * 查询我的已完成订单
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listMyPicture", produces = "application/json; charset=utf-8")
	public String listMyPicture(Integer uid, Integer status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("indent", indentService.listMyPicture(uid, status));
		return JSON.toJSONString(map);
	}

	/**
	 * 获取商品列表
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/productHeadList", produces = "application/json; charset=utf-8")
	public ModelAndView productList(Integer nowpage, Integer type) {
		ModelAndView model=new ModelAndView("index/type");
		pageClass.setNowpage(nowpage);
		model.addObject("products", productService.productList(pageClass, type));
		model.addObject("page", pageClass);
		model.addObject("type", type);
		return model;
	}

	/*@ResponseBody
	@RequestMapping("/fileUploadPicture")
	public String fileUploadPicture(String imgdata, HttpServletRequest request) {
		String img=imgdata.split(",")[1];
		String path = request.getServletContext().getRealPath("upload/");
		String filename=System.currentTimeMillis()+".jpg";
		File f=new File(path);
		if(!f.exists()){
			f.mkdirs();
		}
		Base64Util.GenerateImage(img, path+filename);
		log.info("上传图片路径："+path+filename);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("fileUrl", "upload/" + filename);
		return JSON.toJSONString(map);
	}*/

	/**
	 * 获取资金明细列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cdetailList", produces = "application/json; charset=utf-8")
	public String cdetailList(Integer uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Capital> capitalList = new ArrayList<Capital>();
		capitalList = capitalService.ListCapital(uid);
		map.put("capital", capitalList);
		return JSON.toJSONString(map);
	}

	

	/**
	 * 个人设置修改邮箱
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyEmail", produces = "application/json; charset=utf-8")
	public String modifyEmail(String email, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Users user = (Users) session.getAttribute("user");
		if (user != null) {
			userService.modifyEmail(user.getUid(), email);
			map.put("success", "修改邮箱成功");
		}
		map.put("error", "修改邮箱失败");
		return JSON.toJSONString(map);
	}

	/**
	 * id查询单个商品
	 * 
	 * @param pid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getproduct", produces = "application/json; charset=utf-8")
	public String getproduct(Integer pid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product", productService.queryOneByPrimary(pid));
		return JSON.toJSONString(map);
	}

	/**
	 * 用户下单
	 * 
	 * @return
	 */
	@RequestMapping("/addindent")
	public ModelAndView toindent(Integer pid, HttpSession session) {
		Product product = productService.queryOneByPrimary(pid);
		Users user = (Users) session.getAttribute("user");
		// 新增订单
		Integer primary = indentService.addIndent(new Indent(null, new Date(), null, null, null, user, product,
				IndentStatusEnum.Takeeffect.getStatus(), product.getCover(), IndentStatusEnum.Takeeffect.getDescribe(), 3));
		// 加入资金流动表
		Integer status = capitalService
				.addCapital(new Capital(null, new Date(), user, -product.getPrice(), Constant.Shoppicture));
		log.info("新增订单的id编号：" + primary);
		user.setBalance(user.getBalance() - product.getPrice());
		// 用户余额减少
		userService.updateUser(user);
		ModelAndView model = new ModelAndView("redirect:/index/toheaduploadjsp?tid="+primary);
		return model;
	}
	
	@RequestMapping("/toheaduploadjsp")
	public ModelAndView toheaduploadjsp(Integer tid){
		ModelAndView model = new ModelAndView("index/headupload");
		model.addObject("tid", tid);
		return model;
	}
	
	@RequestMapping("/queryhead")
	public ModelAndView queryhead(ModelAndView model,HttpSession session){
		Users user = (Users) session.getAttribute("user");
		model.addObject("user", user);
		model.setViewName("index/modifaypicture");
		return model;
	}
	
	
	/**
	 * 用户上传订单照片和要求
	 * 
	 * @return
	 */
	@RequestMapping("/updateindent")
	public ModelAndView updateindent(Integer tid, String userphoto, String require_text, String e_mail) {
		ModelAndView model = new ModelAndView("index/type");
		log.info("接收的上传：" + tid + userphoto + require_text + e_mail);
		Indent indent = new Indent();
		indent.setTid(tid);
		indent.setUserphoto(userphoto);
		indent.setRequire(require_text);
		indent.setE_mail(e_mail);
		indent.setStatus(IndentStatusEnum.NotOrders.getStatus());
		indent.setStatusintr(IndentStatusEnum.NotOrders.getDescribe());
		Integer status = indentService.updateIndent(indent);
		if (status == 1) {
			log.info("上传订单照片和要求成功");
		}
		return model;
	}

	@RequestMapping("/settid")
	public ModelAndView settid(Integer tid) {
		ModelAndView model = new ModelAndView("index/headupload");
		model.addObject("tid", tid);
		return model;
	}
	
	/**
	 * 个人设置jsp页面准备数据，到后台获取
	 */
	/*@RequestMapping("/getusers")
	public ModelAndView getusers(HttpSession session) {
		ModelAndView model = new ModelAndView("index/personalset");
		model.addObject("users", (Users)session.getAttribute("user"));
		//model.addObject("users", new Users());//现在没有在session中，因此这里是创建的一个对象
		log.info((Users)session.getAttribute("user"));
		model.addObject("users", (Users)session.getAttribute("user"));
		return model;
	}*/
	
	/**
	 * 个人设置jsp页面修改数据
	 */
	@RequestMapping("/usersUpdate")
	public ModelAndView usersUpdate(String phonenumber,String email,Date birth,
			Integer sex,HttpSession session) {
		log.info("获取的User:"+phonenumber+email+birth+sex);
		Users user=(Users)session.getAttribute("user");
		user.setPhonenumber(phonenumber);
		user.setEmail(email);
		user.setBirth(birth);
		user.setSex(sex);
		userService.updateUser(user);
		ModelAndView model = new ModelAndView("index/type");
		return model;
	}
}
