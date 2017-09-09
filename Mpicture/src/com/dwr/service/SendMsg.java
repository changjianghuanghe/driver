package com.dwr.service;

import java.util.Collection;

import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.WebContext;  
  
import org.directwebremoting.WebContextFactory;  
  
import org.directwebremoting.proxy.dwr.Util;
import org.springframework.stereotype.Component;

import com.mpicture.util.Constant;  

//@SuppressWarnings("deprecation")
public class SendMsg {
    //public String sendMsg(String msg){  
        //得到上下文    
        //WebContext contex = WebContextFactory.get();    
            
        //得到要推送到 的页面  dwr3为项目名称 ， 一定要加上。    
        //Collection<ScriptSession> sessions = contex.getScriptSessionsByPage("Mpicture/admin/top.jsp");
        //不知道该怎么解释这个 ，     
        //Util util = new Util(sessions);  
            
        //下面是创建一个javascript脚本 ， 相当于在页面脚本中添加了一句  show(msg);     
        /*ScriptBuffer sb = new ScriptBuffer();    
        sb.appendScript("show(");    
        sb.appendData(msg);    
        sb.appendScript(")"); */   
            
        //推送    
        //util.addScript(sb);    
    	//return Constant.ToIndentAction;
    //}
    
    public void sendMsg(String userid, String message, String jsFuc) {
        final String userId = userid;
        final String msg = message;
        final String jsFunc = jsFuc;
        Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
            public boolean match(ScriptSession session) {
                if (session.getAttribute("userId")==null){
                    return false;
                }else {
                    return (session.getAttribute("userId")).equals(userId);
                }
            }
        },new Runnable() {
            private ScriptBuffer script = new ScriptBuffer();
            public void run() {
                // 推送消息
                script.appendCall(jsFunc,msg);
                Collection<ScriptSession>sessions = Browser.getTargetSessions();
                for (ScriptSession scriptSession : sessions) {
                    scriptSession.addScript(script);
                }
            }
        });
    }
}
