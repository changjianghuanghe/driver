package com.jimeng.util;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 随机码工具类
 * Created by wangxin on 2017/9/14.
 */
public class MessyUtil {
    private static Logger log = Logger.getLogger(MessyUtil.class);
    /**
     * 获取UUID随机码
     *
     * @return
     */
    public static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /**
     * Date格式化日期
     * @param date
     * @return
     */
    public static String fmtDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    /**
     * 判断字符串是否为空
     * @return boolean 空则返回true,非空则flase
     */
    public static boolean isEmpty(String input) {
        return null==input || 0==input.length() || 0==input.replaceAll("\\s", "").length();
    }

    /**
     * 字节数组转为字符串
     * @see
     * @see <code>getString(byte[] data, String charset)</code>方法
     */
    public static String getString(byte[] data){
        return getString(data, "UTF-8");
    }

    /**
     * 字节数组转为字符串
     * @see <code>charset</code>字符集,则按照系统默认字符集进行转换
     */
    public static String getString(byte[] data, String charset){
        if(isEmpty(charset)){
            return new String(data);
        }
        try {
            return new String(data, charset);
        } catch (UnsupportedEncodingException e) {
            log.error("将byte数组[" + data + "]转为String时发生异常:系统不支持该字符集[" + charset + "]");
            return new String(data);
        }
    }


    /**
     * 字符串转为字节数组
     * @see
     * @see <code>getBytes(String str, String charset)</code>方法
     */
    public static byte[] getBytes(String data){
        return getBytes(data, "UTF-8");
    }


    /**
     * 字符串转为字节数组
     * @see <code>charset</code>字符集,则按照系统默认字符集进行转换
     */
    public static byte[] getBytes(String data, String charset){
        data = (data==null ? "" : data);
        if(isEmpty(charset)){
            return data.getBytes();
        }
        try {
            return data.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            log.error("将字符串[" + data + "]转为byte[]时发生异常:系统不支持该字符集[" + charset + "]");
            return data.getBytes();
        }
    }
	
	/**
	 * 随机从集合抽取指定数量的元素
	 * @author xingbake
	 */
	public static List<Object> RandomList(List<Object> objList,int size){
		List<Object> newList=new ArrayList<Object>();
		if(size>objList.size()){
			log.info("抽取长度大于集合长度!");
			return objList;
		}else{
			for(int i=0;i<size;i++){
				int index = (int) (Math.random() * objList.size());
				newList.add(objList.get(index));
			}
		}
		return newList;
	}

    /**
     * springMvc上传文件工具类
     * @param path
     * @param file
     * @return
     */
    public static boolean springMvcFileUpload(String path,MultipartFile file){
    	if(file.isEmpty()==true){
    		log.info("传入文件为空！");
    		return false;
    	}
		String fileName = file.getOriginalFilename();
        File uploadFile = new File(path, fileName);
        if(!uploadFile.exists()){
            uploadFile.mkdirs();
        }
        //执行上传
        try {
            file.transferTo(uploadFile);
            log.info("上传成功，上传的路径为:"+uploadFile.getPath());
        } catch (Exception e) {
            log.error("上传文件失败，错误原因:"+e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * @author Mark
     * @Description：将请求参数转换为xml格式的string
     * @param parameters
     *            请求参数
     * @return
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }


}
