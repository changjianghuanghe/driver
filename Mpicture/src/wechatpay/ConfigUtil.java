package wechatpay;
/** 
* 微信支付配置文件 
* @author Mark 
* 
*/
public class ConfigUtil {
	/** 
	* 服务号相关信息 
	*/ 
	public final static String APPID = "wx00fd10c78cc22c51";//服务号的appid 
	public final static String APP_SECRECT = "9375ff7b788f6370e1ed0f6165572a65";//服务号的appSecrect 
	public final static String TOKEN = "mengxiaoni";//服务号的配置token 
	public final static String MCH_ID = "1486727732";//开通微信支付分配的商户号 
	public final static String API_KEY = "iHsuds83874yf7sdyd7wdaDFf9h7T5d3";//商户API密钥 自行去商户平台设置 
	public final static String SIGN_TYPE = "MD5";//签名加密方式 
	//微信支付统一接口的回调action 
	public final static String NOTIFY_URL = "http://acg.myfa.cn/M_picture/index/notify_url"; //用于告知微信服务器 调用成功
	
	/**
	 * 微信基础接口地址
	 */ 
	 public final static String WeChatUser="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	 //获取普通access_token接口(GET)
	 public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	 //获取网页授权access_token
	 public final static String oauth_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	 
	 public final static String OAUTH2_URL ="https://open.weixin.qq.com/connect/oauth2/authorize?"
	 		+ "appid=APPID&redirect_uri=Recharge_URI&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
	 //oauth2授权接口(GET)
	 //public final static String OAUTH2_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	 //刷新access_token接口（GET）
	 public final static String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	// 菜单创建接口（POST）
	 public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	// 菜单查询（GET）
	 public final static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	// 菜单删除（GET）
	public final static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	/**
	 * 微信支付接口地址
	 */
	//微信支付统一接口(POST)
	public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	//微信退款接口(POST)
	public final static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	//订单查询接口(POST)
	public final static String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	//关闭订单接口(POST)
	public final static String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	//退款查询接口(POST)
	public final static String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	//对账单接口(POST)
	public final static String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	//短链接转换接口(POST)
	public final static String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
	//接口调用上报接口(POST)
	public final static String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
	 
}
