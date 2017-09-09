package wechatpay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.jdom.JDOMException;

import com.mpicture.controller.PayWechatController;

public class WxPayUtil {
	private static Logger log = Logger.getLogger(PayWechatController.class);
	/**
	 * 微信支付
	 * @param body
	 * @param out_trade_no
	 * @param openid
	 * @param money
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map unifiedorder(String body, String out_trade_no, String openid,String money) {
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", ConfigUtil.APPID);
		parameters.put("mch_id", ConfigUtil.MCH_ID);  //商户id
		parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());  //预支付id
		parameters.put("body", body);  //订单类型
		parameters.put("out_trade_no", out_trade_no);  //订单号
		parameters.put("total_fee",money);  //充值金额，以分为单位
		parameters.put("spbill_create_ip", "113.57.246.11");
		parameters.put("notify_url", ConfigUtil.NOTIFY_URL);  //微信支付统一接口的回调action 
		parameters.put("trade_type", "JSAPI");  //交易类型
		parameters.put("openid", openid);	
		String sign = PayCommonUtil.createSign(parameters);
		parameters.put("sign", sign);
		String requestXML = PayCommonUtil.getRequestXml(parameters);
		String result = CommonUtil.httpsRequest(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
		//支付结果xml
		log.info(result.toString());
		Map<String, String> map = new HashMap<String, String>();
		try {
			map = XMLUtil.doXMLParse(result);
			log.info("返回map的值"+map);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// 解析微信返回的信息，以Map形式存储便于取值
		return map;
	}
}
