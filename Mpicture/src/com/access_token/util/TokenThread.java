package com.access_token.util;

import java.io.IOException;
import java.text.ParseException;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TokenThread implements Runnable{
	private static final Logger log=Logger.getLogger(TokenThread.class);
	
	public static String appId = "wx00fd10c78cc22c51";
	 
    public static String appSecret= "9375ff7b788f6370e1ed0f6165572a65";
    //注意是静态的
    public static AccessToken accessToken = null;
    
    public static String jsapi_ticket=null;
 
    public void run(){
        while (true){
            try{
                accessToken = this.getAccessToken();
                if(null!=accessToken){
                    //System.out.println(accessToken.getAccessToken());
                	jsapi_ticket=getTicket();
                    Thread.sleep(7000 * 1000); //获取到access_token和jsapi_ticket 休眠7000秒
 
                }else{
                    Thread.sleep(1000*3); //获取的access_token为空 休眠3秒
                }
            }catch(Exception e){
                System.out.println("发生异常："+e.getMessage());
                e.printStackTrace();
                try{
                    Thread.sleep(1000*10); //发生异常休眠1秒
                }catch (Exception e1){
 
                }
            }
        }
    }
 
 
    /**
     * 获取access_token
     * @return
     */
    private AccessToken getAccessToken(){
        NetWorkHelper netHelper = new NetWorkHelper();
        String Url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",this.appId,this.appSecret);
        String result = netHelper.getHttpsResponse(Url,"");
        System.out.println(result);
        //response.getWriter().println(result);
        JSONObject json = JSON.parseObject(result);
        AccessToken token = new AccessToken();
        token.setAccessToken(json.getString("access_token"));
        token.setExpiresin(json.getInteger("expires_in"));
        return token;
    }
    
    public final static String sign_ticket_create_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
    //获取签名需要的jsapi_ticket
	public static String getTicket() throws ParseException, IOException {
		JSONObject jsonObject = new JSONObject();
		String ticket = null;
		String url = sign_ticket_create_url.replace("ACCESS_TOKEN", accessToken.getAccessToken());
		try {
			jsonObject = wechatpay.CommonUtil.httpsRequestToJsonObject(url, "GET", null);
			ticket = jsonObject.getString("ticket");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	};
}
