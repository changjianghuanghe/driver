package com.mpicture.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mpicture.entity.Indent;
import com.mpicture.entity.Users;
import com.mpicture.service.IndentService;
import com.mpicture.util.IndentStatusEnum;

@Controller
@Scope("prototype")
@RequestMapping("/index")
public class IndentController {
	
	@Autowired
	private IndentService indentService;
	/**
	 * 查询单个订单详情
	 * @param tid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getdetails",produces = "application/json; charset=utf-8")
	public String getdetails(Integer tid){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("indent", indentService.getdetails(tid));
		return JSON.toJSONString(map);
	}
	/**
	 * 用户选择重新作画
	 * @param tid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updatetotal",produces = "application/json; charset=utf-8")
	public String updatetotal(Integer tid){
		Indent indent=indentService.getdetails(tid);
		indent.setUpdatetotal(indent.getUpdatetotal()-1);
		indent.setStatus(IndentStatusEnum.UpdateOrder.getStatus());
		indent.setStatusintr(IndentStatusEnum.UpdateOrder.getDescribe());
		indentService.updateIndentStatus(indent);
		return "success";
	}
	/**
	 * 用户选择满意,完成订单
	 */
	@ResponseBody
	@RequestMapping(value="/complete",produces = "application/json; charset=utf-8")
	public String complete(Integer tid){
		Indent indent=indentService.getdetails(tid);
		indent.setStatus(IndentStatusEnum.MissionSuccess.getStatus());
		indent.setStatusintr(IndentStatusEnum.MissionSuccess.getDescribe());
		indent.setUpdatetotal(0);
		indentService.updateIndentStatus(indent);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("/newIndentList")
	public String newIndentList(HttpSession session,HttpServletRequest request){
		String path = request.getServletContext().getRealPath("history"); 
		File file=new File(path);
		File files[] = file.listFiles();
		List<String> pictures=new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
			pictures.add("../history/"+files[i].getName());
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("indents",pictures);
		return JSON.toJSONString(map);
	}
}
