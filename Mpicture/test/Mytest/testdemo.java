package Mytest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mpicture.service.IndentService;

import wechatpay.ConfigUtil;
import wechatpay.MD5Util;
import wechatpay.PayCommonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:resources/spring.xml"})
public class testdemo {
	@Autowired
	IndentService indentService;
	
	@Test
	public void testOne(){
		System.out.println(indentService.listMyIndent(1));
	}
	
	public static void main(String[] args) {
		/*SortedMap<Object, Object> signParams = new TreeMap<Object, Object>();
		signParams.put("appId", ConfigUtil.APPID);
		signParams.put("nonceStr", "ddfgdfvbdbdsdfsd");
		signParams.put("package", "prepay_id=" + "zf123456789");
		signParams.put("timeStamp", System.currentTimeMillis());
		signParams.put("signType", "MD5");
		
		String sign = createSign(signParams);
		System.out.println(sign);*/
		File f=new File("D:/fie.txt");
		System.out.println(f.exists());
	}
	
	public static String createSign(SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + ConfigUtil.API_KEY);
		System.out.println(sb.toString());
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		return sign;
	}
}
