package com.access_token.util;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mpicture.entity.Indent;
import com.mpicture.entity.Users;
import com.mpicture.service.IndentService;
import com.mpicture.service.UserService;
import com.mpicture.util.IndentStatusEnum;
/**
 * 使用类部类加载Thread进入spring管理
 * @author admin
 *
 */
@Component
public class PollingTime{
	private static final Logger log=Logger.getLogger(PollingTime.class);
	
	@Autowired
	private IndentService indentService;
	@Autowired
	private UserService userService;
	public static Users user;
	
	public void execute() {
        new Thread(new Worker()).start();
    }
	
	private class Worker implements Runnable{
		public void run() {
			while (true) {
				try {
					if(user!=null){
						log.info("PollingTime的user："+user);
						log.info("PollingTime的indentService："+indentService);
						List<Indent> indents=indentService.listMyPicture(user.getUid(), IndentStatusEnum.NotOrders.getStatus());					
						log.info("查询的所有未生效订单："+indents);
						for (Indent indent : indents) {
							if(judge12Time(indent.getSystime())){
								log.info("当前时间差状态"+judge12Time(indent.getSystime()));
								indent.setStatus(IndentStatusEnum.ReturnOrder.getStatus());
								indent.setStatusintr(IndentStatusEnum.ReturnOrder.getDescribe());
								indentService.updateIndentStatus(indent);
								user.setBalance(user.getBalance()+indent.getProduct_id().getPrice());
								userService.updateUser(user);
							}
						}
						List<Indent> ins=indentService.listMyPicture(user.getUid(), IndentStatusEnum.Orders.getStatus());
						for (Indent indent : ins) {
							if(judge24Time(indent.getSystime())){
								indent.setStatus(IndentStatusEnum.ReturnOrder.getStatus());
								indent.setStatusintr(IndentStatusEnum.ReturnOrder.getDescribe());
								indentService.updateIndentStatus(indent);
								user.setBalance(user.getBalance()+indent.getProduct_id().getPrice());
								userService.updateUser(user);
							}
						}
						Thread.sleep(600 * 1000);	//10分钟轮询一次
					}else{
						throw new Exception("获得的uid为空");
					}
				} catch (Exception e) {
					e.printStackTrace();
					try{
	                    Thread.sleep(1000*10); //发生异常休眠1秒
	                }catch (Exception e1){
	 
	                }
				}
				
			}
		}
	}
	
	
	
	//判断未接单时间大于12小时
	public boolean judge12Time(Date indenttime){
		long s=new Date().getTime()-indenttime.getTime();
		int minutes=(int) (s/1000/60);
		if(minutes>720){
			return true;
		}
		return false;
	}
	//判断已接单时间大于24小时
	public boolean judge24Time(Date indenttime){
		long s=new Date().getTime()-indenttime.getTime();
		int minutes=(int) (s/1000/60);
		if(minutes>1440){
			return true;
		}
		return false;
	}
}
