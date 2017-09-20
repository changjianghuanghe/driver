package com.jimeng.util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.Base64Utils;


/**
 * 安全工具类
 */
public class SafeUtil {
	
	/**
	 * md5加密
	 * @param str
	 * @return
	 */
	public final static String md5(String str){
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(str.getBytes());
		return Base64Utils.encodeToString(messageDigest.digest());
	}
	
	
	/**
	 * sha1
	 * @param str
	 * @return
	 */
	public final static String sha1(String str){
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(str.getBytes());
		return Base64Utils.encodeToString(messageDigest.digest());
	}

	/**
	 * 复合加密
	 * @param str
	 * @return
	 */
	public final static String encode(String str){
		return md5(sha1(md5(str)));
	}
	
}
