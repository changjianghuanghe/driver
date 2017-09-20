package com.jimeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jimeng.entity.User;
import com.jimeng.service.UserService;

@Controller
@Scope("prototype")
@RequestMapping("/index")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(){
		
		return userService.findAll().toString();
	}
	
	@RequestMapping("/update")
	public String update(){
		User user=new User(1,"wangxin","456","18011057133","asd","dasdas",1);
		return userService.addOrUpdate(user).toString();
	}
}
