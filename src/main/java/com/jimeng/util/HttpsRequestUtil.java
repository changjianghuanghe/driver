package com.jimeng.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

/**
 * 发送https请求工具类
 * Created by wangxin on 2017/9/14.
 */
public class HttpsRequestUtil {
    private static Logger log = Logger.getLogger(HttpsRequestUtil.class);

    public static JSONObject httpsRequestToJsonObject(String requestUrl, String requestMethod) {
        StringBuffer buffer = httpRequestSend(requestUrl, requestMethod);
        JSONObject jsonObject = JSONObject.parseObject(buffer.toString());
        return jsonObject;
    }

    /**
     * @param hsUrl         请求的https链接
     * @param requestMethod 请求方式，默认get
     * @return
     */
    public static StringBuffer httpRequestSend(String hsUrl, String requestMethod) {
        URL url;
        InputStream is = null;
        StringBuffer sb = null;
        try {
            url = new URL(hsUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            TrustManager[] tm = {xtm};

            //SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tm, null);
            con.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });

            con.setSSLSocketFactory(ctx.getSocketFactory());
            con.setDoInput(true); //允许输入流，即允许下载

            //在android中必须将此项设置为false
            con.setDoOutput(true); //允许输出流，即允许上传
            con.setUseCaches(false); //不使用缓冲
            if (null != requestMethod && !requestMethod.equals("")) {
                con.setRequestMethod(requestMethod); //使用指定的方式
            } else {
                con.setRequestMethod("GET"); //使用get请求
            }
            is = con.getInputStream();   //获取输入流，此时才真正建立链接
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);
            sb = new StringBuffer();
            String inputLine = "";
            while ((inputLine = bufferReader.readLine()) != null) {
                sb.append(inputLine);
            }
            log.info("请求到的内容：" + sb.toString());
            Certificate[] certs = con.getServerCertificates();

            int certNum = 1;

            for (Certificate cert : certs) {
                X509Certificate xcert = (X509Certificate) cert;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }

    static X509TrustManager xtm = new X509TrustManager() {
        public X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }

        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
            // TODO Auto-generated method stub

        }

        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
            // TODO Auto-generated method stub
        	
        }
    };
}
