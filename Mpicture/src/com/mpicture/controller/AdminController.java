package com.mpicture.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dwr.service.SendMsg;
import com.mpicture.entity.Admin;
import com.mpicture.entity.PageClass;
import com.mpicture.entity.Product;
import com.mpicture.service.AdminService;
import com.mpicture.service.IndentService;
import com.mpicture.service.ProductService;
import com.mpicture.util.Constant;
import com.mpicture.util.IndentStatusEnum;
/**
 * controller  - admin管理员
 * 
 * @author Administrator
 *
 */

@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger log=Logger.getLogger(AdminController.class);
	/** 管理员 */
	@Autowired
	private AdminService adminService;
	/** 分页类 */
	private static PageClass pageClass=new PageClass();
	/** 商品列表  */
	@Autowired
	private ProductService productService;
	@Autowired
	private IndentService indentService;
	
	/**
	 * 登录前获取表单model
	 * @return
	 */
	@RequestMapping("/turnto")
	public ModelAndView turnto(){
		ModelAndView model=new ModelAndView();//springframework框架包中的一个类
		model.addObject("admin", new Admin());
		model.setViewName("admin/login");
		return model;
	}
	
	/**
	 * 登录
	 * @param admin
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(Admin admin,HttpServletRequest request){
		ModelAndView model=new ModelAndView();
		Admin a=adminService.login(admin);
		if(a==null){
			model.addObject("errorlogin", Constant.ErrorLogin);//将错误的信息传递到对象中
			model.addObject("admin", new Admin());
			model.setViewName("admin/login");
			return model;
		}
		HttpSession session=request.getSession();
		session.setAttribute("admin", a);
		model.setViewName("admin/main");
		return model;
	}
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping("/loginout")
	public ModelAndView loginout(HttpServletRequest request){
		ModelAndView model=new ModelAndView();
		HttpSession session=request.getSession();
		if(session.getAttribute("admin")!=null){
			session.removeAttribute("admin");
		}
		model.addObject("admin", new Admin());
		model.setViewName("admin/login");
		return model;
	}
	/**
	 * 用户列表
	 * @return
	 */
	@RequestMapping("/adminList")
	public ModelAndView adminList(Integer nowpage){
		ModelAndView model=new ModelAndView();
		pageClass.setNowpage(nowpage);
		model.addObject("adminList", adminService.adminList(pageClass));
		model.addObject("pageClass", pageClass);
		model.setViewName("admin/pages/admin-list");
		return model;
	}
	/**
	 * 新增admin前发送模型
	 * @return
	 */
	@RequestMapping("/addAdmin")
	public ModelAndView addAdmin(){
		ModelAndView model=new ModelAndView();
		model.addObject("admin", new Admin());
		model.setViewName("admin/pages/admin-add");
		return model;
	}
	/**
	 * 新增admin
	 * @return
	 */
	@RequestMapping("/adminAdd")
	public ModelAndView adminAdd(Admin admin){
		ModelAndView model=new ModelAndView();
		model.setViewName("admin/pages/admin-add");
		model.addObject("admin", new Admin());
		if(adminService.adminAdd(admin)==1){
			model.addObject("addstatus", Constant.OperationSuccess);
			return model;
		}
		model.addObject("addstatus", Constant.OperationFail);
		return model;
	}
	/**
	 * 修改前返回模型
	 * @return
	 */
	@RequestMapping("/upAdmin")
	public ModelAndView upAdmin(Integer aid){
		ModelAndView model=new ModelAndView();
		model.addObject("admin", adminService.getAdmin(aid));
		model.setViewName("admin/pages/admin-reset");
		return model;
	}
	/**
	 * 删除admin
	 * @return
	 */
	@RequestMapping("/delAdmin")
	public ModelAndView delAdmin(Integer aid){
		ModelAndView model=new ModelAndView();
		model.setViewName("redirect:/admin/adminList?nowpage=1");
		if(adminService.deleteAdmin(aid)==1){
			model.addObject("addstatus", Constant.OperationSuccess);
			return model;
		}
		model.addObject("addstatus", Constant.OperationFail);
		return model;
	}
	/**
	 * 订单列表
	 * @return
	 */
	@RequestMapping("/indentList")
	public ModelAndView indentList(Integer nowpage,Integer status){
		ModelAndView model=new ModelAndView();
		pageClass.setNowpage(nowpage);
		model.addObject("indentList", indentService.listIndentByStatus(pageClass,status));
		model.addObject("pageClass", pageClass);
		if(status!=null){
			model.addObject("status", status);
		}
		//测试
		SendMsg sendMsg=new SendMsg();
		sendMsg.sendMsg(Constant.ToIndentAction);
		model.setViewName("admin/pages/indent-list");
		return model;
	}
	/**
	 * 删除订单
	 * @param tid
	 * @return
	 */
	@RequestMapping("/indentDelete")
	public ModelAndView indentDelete(Integer tid){
		ModelAndView model=new ModelAndView("redirect:/admin/indentList?nowpage=1");
		Integer status=indentService.deleteIndent(tid);
		log.info("~~~~"+status);
		/*if(status==1){
			model.addObject("status", Constant.OperationSuccess);
		}else{
			model.addObject("status", Constant.OperationFail);
		}*/
		return model;
	}
	/**
	 * 修改订单状态
	 * @param tid
	 * @param status
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public ModelAndView updateStatus(Integer tid,Integer status){
		ModelAndView model=new ModelAndView("redirect:/admin/indentList?nowpage=1");
		Integer result=indentService.updateIndentStatus(tid,status);
		return model;
	}
	
	
}
