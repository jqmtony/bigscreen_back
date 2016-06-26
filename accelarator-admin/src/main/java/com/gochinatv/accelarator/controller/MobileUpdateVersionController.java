package com.gochinatv.accelarator.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 * @作者 zhuhh
 * @描述    临时手机升级
 * @创建时间 2016年6月23日 下午2:33:04
 * @修改时间
 */
@Controller
@RequestMapping("/download")
public class MobileUpdateVersionController {
	
	@Value("${APK_PATH}")
	private String APK_PATH;
	
	@Value("${IPA_PATH}")
	private String IPA_PATH;
	
	private Logger LOGGER = LoggerFactory.getLogger(MobileUpdateVersionController.class);
	
	
	/**
	 * 下载app
	 * @param platform
	 * @param app_key
	 * @return
	 */
	@RequestMapping("/app")
  	public void app(HttpServletRequest request,HttpServletResponse response){
		String agent = request.getHeader("User-Agent");
		LOGGER.info("agent:===="+agent);
		String download_url = null;
		try {
			String lower = agent.toLowerCase();
			if(lower.indexOf("iphone") != -1){
				download_url = IPA_PATH;
			}else{
				download_url = APK_PATH;
			}
			response.sendRedirect(download_url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
