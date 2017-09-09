package com.mpicture.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.access_token.util.TokenThread;
import com.alibaba.fastjson.JSON;
import com.mpicture.entity.Users;
import com.mpicture.service.UserService;
import com.wechat.upload.WxConfigUploadImage;

@Controller
@Scope("prototype")
@RequestMapping("/index")
public class WeChatUploadController {
	private static final Logger log = Logger.getLogger(WeChatUploadController.class);
	@Autowired
	private UserService userService;
	
	private static String ImageName="";
	/**
	 * 处理微信图片上传
	 * 
	 * @param url
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/fileUpload", produces = "application/json; charset=utf-8")
	public String fileUploadPicture(String url, HttpServletRequest request) throws ParseException, IOException {
		// 注意 URL 一定要动态获取，不能 hardcode
		Map<String, String> ret = WxConfigUploadImage.sign(TokenThread.jsapi_ticket, url);
		for (Map.Entry entry : ret.entrySet()) {
			log.info(entry.getKey() + ", " + entry.getValue());
		}
		return JSON.toJSONString(ret);
	}
	/**
	 * 下载wx服务器图片到本地
	 * @param serverId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/downloadhead", produces = "application/json; charset=utf-8")
	public String downloadhead(String serverId, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("upload/");
		File file=new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		//执行下载，返回下载文件路径
		String filepath=downloadMedia(serverId,path);
		log.info("下载的图片filepath:"+filepath);
		Map<String,String> map=new HashMap<String,String>();
		map.put("filepath", "../upload/"+ImageName);
		return JSON.toJSONString(map);
	}
	/**
	 * 上传用户头像
	 * @param userhead
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatehead", produces = "application/json; charset=utf-8")
	public String updatehead(String userhead,HttpSession session){
		Users user=(Users)session.getAttribute("user");
		user.setHeadportrait(userhead);
		Integer status=userService.updateUser(user);
		/*Map<String,String> map=new HashMap<String,String>();
		if(status.equals(1)){
			map.put("message", "头像修改成功");
		}else{
			map.put("message", "头像修改失败");
		}*/
		return "success";
	}
	
	/**
	   * 根据内容类型判断文件扩展名
	   *
	   * @param contentType 内容类型
	   * @return
	   */
	  public static String getFileexpandedName(String contentType) {
	    String fileEndWitsh = "";
	    if ("image/jpeg".equals(contentType))
	      fileEndWitsh = ".jpg";
	    else if ("audio/mpeg".equals(contentType))
	      fileEndWitsh = ".mp3";
	    else if ("audio/amr".equals(contentType))
	      fileEndWitsh = ".amr";
	    else if ("video/mp4".equals(contentType))
	      fileEndWitsh = ".mp4";
	    else if ("video/mpeg4".equals(contentType))
	      fileEndWitsh = ".mp4";
	    return fileEndWitsh;
	  }
	  /**
	   * 获取媒体文件
	   * @param accessToken 接口访问凭证
	   * @param mediaId 媒体文件id
	   * @param savePath 文件在本地服务器上的存储路径
	   * */
	  public static String downloadMedia(String mediaId, String savePath) {
	    String filePath = null;
	    // 拼接请求地址
	    String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	    requestUrl = requestUrl.replace("ACCESS_TOKEN", TokenThread.accessToken.getAccessToken()).replace("MEDIA_ID", mediaId);
	    try {
	      URL url = new URL(requestUrl);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setDoInput(true);
	      conn.setRequestMethod("GET");

	      /*if (!savePath.endsWith("/")) {
	        savePath += "/";
	      }*/
	      // 根据内容类型获取扩展名
	      String fileExt = getFileexpandedName(conn.getHeaderField("Content-Type"));
	      ImageName=mediaId + fileExt;
	      // 将mediaId作为文件名
	      filePath = savePath + mediaId + fileExt;
	      BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
	      FileOutputStream fos = new FileOutputStream(new File(filePath));
	      byte[] buf = new byte[8096];
	      int size = 0;
	      while ((size = bis.read(buf)) != -1)
	        fos.write(buf, 0, size);
	      fos.close();
	      bis.close();

	      conn.disconnect();
	      String info = String.format("下载媒体文件成功，filePath=" + filePath);
	      log.info(info);
	    } catch (Exception e) {
	      filePath = null;
	      String error = String.format("下载媒体文件失败：%s", e);
	      log.info(error);
	    }
	    return filePath;
	  }
}
