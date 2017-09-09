package com.mpicture.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.InputSource;

import com.access_token.util.PollingTime;
import com.access_token.util.TokenThread;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mpicture.entity.Capital;
import com.mpicture.entity.Users;
import com.mpicture.service.CapitalService;
import com.mpicture.service.UserService;
import com.mpicture.util.Constant;
import com.mpicture.util.SafeUtil;

import wechatpay.CommonUtil;
import wechatpay.ConfigUtil;
import wechatpay.PayCommonUtil;
import wechatpay.WxPayUtil;

/**
 * controller - 微信支付
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/index")
public class PayWechatController {
	private static Logger log = Logger.getLogger(PayWechatController.class);
	
	public static Double quota=0.0;
	@Autowired
	private CapitalService capitalService;
	@Autowired
	private UserService userService;
	@Autowired
	PollingTime pollingTime;
	
	
	@RequestMapping(value="/login.do",produces="application/json; charset=utf-8")
	public ModelAndView login(String code,HttpServletRequest request){
		ModelAndView model=new ModelAndView("redirect:/index/toindex");
		String openId=getopenid(code);
		log.info("换取的openid"+openId);
		String oauth_url = ConfigUtil.WeChatUser.replace("ACCESS_TOKEN", TokenThread.accessToken.getAccessToken())
				.replace("OPENID",openId);
		JSONObject jsonObject = wechatpay.CommonUtil.httpsRequestToJsonObject(oauth_url, "POST", null);
		log.info("用户微信昵称："+jsonObject.getString("nickname"));
		log.info("用户微信头像:"+jsonObject.getString("headimgurl"));
		if(jsonObject.getString("subscribe").equals("0")){
			model.addObject("action", "请关注微信公众号   萌小你   后再次访问！");
			model.setViewName("index/action");
			return model;
		}
		HttpSession session=request.getSession();
		String headportrait=jsonObject.getString("headimgurl");
		String username=jsonObject.getString("nickname");
		Integer sex=Integer.parseInt(jsonObject.getString("sex"));
		Users user=userService.queryOne(openId);
		if(user!=null){
			user.setHeadportrait(headportrait);
			user.setUsername(username);
			user.setSex(sex);
			userService.updateUser(user);
			session.setAttribute("user", user);
			//PollingTime.user=user;
		}else{
			userService.addUser(new Users(null,username,openId,headportrait,0.0,null,null,sex,null));
			Users u=userService.queryOne(openId);
			session.setAttribute("user", u);
			//PollingTime.user=u;
		}
		//pollingTime.execute();
		return model;
	}
	
	@RequestMapping("/toindex")
	public String toindex(){
		return "redirect:/index/productHeadList?nowpage=1&type=1";
	}

	/**
	 * 微信Token验证
	 * @param signature
	 *            微信加密签名
	 * @param timestamp
	 *            时间戳
	 * @param nonce
	 *            随机数
	 * @param echostr
	 *            随机字符串
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	@RequestMapping("/getToken")
	public void getToken(String signature, String timestamp, String nonce, String echostr, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 将token、timestamp、nonce三个参数进行字典序排序
			System.out.println("signature:" + signature);
			System.out.println("timestamp:" + timestamp);
			System.out.println("nonce:" + nonce);
			System.out.println("echostr:" + echostr);
			System.out.println("TOKEN:" + ConfigUtil.TOKEN);
			String[] params = new String[] { ConfigUtil.TOKEN, timestamp, nonce };
			Arrays.sort(params);
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			String clearText = params[0] + params[1] + params[2];
			String sign = new String(SafeUtil.getSha1(clearText));
			// 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
			if (signature.equals(sign)) {
				response.getWriter().print(echostr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*@RequestMapping("/getaccesstoken")
	public String getAccess_Token() {
		log.info("当前access_token为：" + TokenThread.accessToken);
		return "";
	}*/
	/**
	 * 获取openId
	 * @param code
	 * @return
	 */
	public String getopenid(String code){
		String oauth_url = ConfigUtil.oauth_url.replace("APPID", ConfigUtil.APPID)
				.replace("SECRET", ConfigUtil.APP_SECRECT).replace("CODE", String.valueOf(code));
		JSONObject jsonObject = wechatpay.CommonUtil.httpsRequestToJsonObject(oauth_url, "POST", null);
		return jsonObject.getString("openid");
	}
	
	/**
	 * @param code 微信响应的状态码，用于换取openid
	 * @param money 用户充值的金额
	 * @param session
	 * @return
	 */
	@RequestMapping("/recharge")
	public ModelAndView getcode(String code,double money,HttpSession session) {
		ModelAndView model=new ModelAndView();
		String openId=getopenid(code);
		//如果获取到openid，执行支付功能
		if(openId!=null){
			log.info("openId:" + openId);
			session.setAttribute("openId", openId);
			quota=money;
			String totalmoney=(int)(money*100)+"";
			log.info("用户充值金额:"+totalmoney);
			// openid可通过微信高级接口oath2.0网页授权接口获取到用户信息,此接口本文中就不提供了，如果有需要，请留言。
			// orderNo是你的商品订单号，自行生成的随机订单号，但是要保证随机性，不能有重复订单号，14位数字
			String orderNo=System.currentTimeMillis()+"1";
			model.addObject("orderNo", orderNo);
			//时间戳
			String timeStamp = PayCommonUtil.create_timestamp();
			//支付随机码
			String nonceStr = PayCommonUtil.create_nonce_str();
			model.addObject("appId", ConfigUtil.APPID);
			model.addObject("timeStamp", timeStamp);
			model.addObject("nonceStr", nonceStr);
			model.addObject("openid", openId);
			//生成支付id
			Map<String,String> hashmap= WxPayUtil.unifiedorder(Constant.Recharge, orderNo, openId,totalmoney);
			String prepayId=hashmap.get("prepay_id").toString();

			SortedMap<Object, Object> signParams = new TreeMap<Object, Object>();
			signParams.put("appId", ConfigUtil.APPID);
			signParams.put("nonceStr", nonceStr);
			signParams.put("package", "prepay_id=" + prepayId);
			signParams.put("timeStamp", timeStamp);
			signParams.put("signType", "MD5");
			// 生成支付签名，要采用URLENCODER的原始值进行SHA1算法！
			String sign = PayCommonUtil.createSign(signParams);
			model.addObject("paySign", sign);
			model.addObject("packageValue", "prepay_id=" + prepayId);
			model.setViewName("index/pay");
			return model;
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/returnStatus",produces="application/json; charset=utf-8")
	public String returnStatus(Integer status,HttpSession session){
		Users user=(Users)session.getAttribute("user");
		log.info("登录用户："+user+"金额"+quota);
		switch (status) {
		case 1:
			int returnnum=capitalService.addCapital(new Capital(null,new Date(),user,quota,Constant.Recharge));
			user.setBalance(user.getBalance()+quota);
			userService.updateUser(user);
			break;
		default:
			break;
		}
		return "success";
	}
	
	/**
	 * 支付回调action
	 * @return
	 */
	@RequestMapping("/notify_url")
	public void notify_url(HttpServletRequest request, HttpServletResponse response){
		String out_trade_no=null;
	    String return_code =null;
	    try {
	        InputStream inStream = request.getInputStream();
	        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
	        byte[] buffer = new byte[1024];
	        int len = 0;
	        while ((len = inStream.read(buffer)) != -1) {
	            outSteam.write(buffer, 0, len);
	        }
	        outSteam.close();
	        inStream.close();
	        String resultStr  = new String(outSteam.toByteArray(),"utf-8");
	        log.info("支付成功的回调："+resultStr);
	        Map<String, Object> resultMap = parseXmlToList(resultStr);
	        String result_code = (String) resultMap.get("result_code");
	        String is_subscribe = (String) resultMap.get("is_subscribe");
	        String transaction_id = (String) resultMap.get("transaction_id");
	        String sign = (String) resultMap.get("sign");
	        String time_end = (String) resultMap.get("time_end");
	        String bank_type = (String) resultMap.get("bank_type");

	        out_trade_no = (String) resultMap.get("out_trade_no");
	        return_code = (String) resultMap.get("return_code");

	        request.setAttribute("out_trade_no", out_trade_no);
	        //通知微信.异步确认成功.必写.不然微信会一直通知后台.八次之后就认为交易失败了.
	        String s = "<xml><return_code>SUCCESS</return_code></xml>";
	        response.getWriter().write(s);
	    }  catch (Exception e) {
	        log.error("微信回调接口出现错误：",e);
	        try {
	        	String s = "<xml><return_code>FAIL</return_code></xml>";
	            response.getWriter().write(s);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    } 
	    if(return_code.equals("SUCCESS")){
	        //支付成功的业务逻辑
	    }else{
	        //支付失败的业务逻辑
	    }
	}
	
	 /**
     * description: 解析微信通知xml
     * @param xml
     * @return
     * @author ex_yangxiaoyi
     * @see
     */
    @SuppressWarnings({ "unused", "rawtypes", "unchecked" })
    private static Map parseXmlToList(String xml) {
        Map retMap = new HashMap();
        try {
            StringReader read = new StringReader(xml);
            // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
            InputSource source = new InputSource(read);
            // 创建一个新的SAXBuilder
            SAXBuilder sb = new SAXBuilder();
            // 通过输入源构造一个Document
            Document doc = (Document) sb.build(source);
            Element root = doc.getRootElement();// 指向根节点
            List<Element> es = root.getChildren();
            if (es != null && es.size() != 0) {
                for (Element element : es) {
                    retMap.put(element.getName(), element.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retMap;
    }
	
}
