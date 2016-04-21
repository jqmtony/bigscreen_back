package com.gochinatv.accelarator.bmapi.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 
 * @author 奇
 * 
 *         2014-12-19
 */

public class FileUpload {

	public static File uploadFile(MultipartFile file, String filePath)
			throws IOException {
		String fileName = file.getOriginalFilename();
		String str = fileName.substring(fileName.lastIndexOf("."));
		String name = fileName.substring(0, fileName.lastIndexOf("."));
		File tempFile = new File(filePath, name + "_" + new Date().getTime()
				+ str);
		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdir();
		}
		if (!tempFile.exists()) {
			tempFile.createNewFile();
		}
		file.transferTo(tempFile);
		return tempFile;
	}
	
	/**
	 * 文件上传，返回结果
	 * 失败，返回失败原因
	 * 成功，返回文件地址
	 * @param request
	 * @return
	 */
	public static Map<String, Object> fileUpload(HttpServletRequest request){
		//文件存放地址
		String filePath = PropertiesUtil.getInstance("/file.properties").getProperty("file.fileUpload.filePath");
		//返回文件访问地址
		String fileUrl = PropertiesUtil.getInstance("/file.properties").getProperty("file.fileUpload.url");
		//文件上传最大值
		String fileMaxSize = PropertiesUtil.getInstance("/file.properties").getProperty("file.fileMaxSize");
		//允许的文件后缀类型
		String fileSuffix = PropertiesUtil.getInstance("/file.properties").getProperty("file.fileSuffix.allow");
		
		String dateStr = DateUtils.convert(new Date(), "yyyyMMdd");
		filePath = filePath + dateStr + "/";
		fileUrl = fileUrl + dateStr+ "/";
		File ff = new File(filePath);
		if(!ff.exists()){
			ff.mkdirs();
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iter = mRequest.getFileNames();
		while(iter.hasNext()){
			MultipartFile file = mRequest.getFile(iter.next());
			if(null != file){
				try {
					//判断文件后缀类型
					String fileNames = file.getOriginalFilename();
					String type = fileNames.substring(fileNames.lastIndexOf(".")+1);
					if(fileSuffix.indexOf(type) != -1){
						//判断文件大小
						long size = file.getSize();
						long maxSize = Integer.parseInt(fileMaxSize)*1024*1024;
						if(size == 0){
							resultMap.put("status", false);
							resultMap.put("msg", "上传失败：文件大小不能为0");
						}else if(size <= maxSize && size > 0){
							//上传文件
							File f = uploadFile(file, filePath);
							String fileName = f.getName();
							fileUrl = fileUrl + fileName;
							resultMap.put("status", true);
							resultMap.put("msg", fileUrl);
						}else{
							resultMap.put("status", false);
							resultMap.put("msg", "上传失败：文件大小不能超过10M");
						}
					}else{
						resultMap.put("status", false);
						resultMap.put("msg", "上传失败：该文件类型不允许上传");
					}
				} catch (IOException e) {
					e.printStackTrace();
					resultMap.put("status", false);
					resultMap.put("msg", "上传失败：上传过程发生错误");
				}
			}else{
				resultMap.put("status", false);
				resultMap.put("msg", "上传失败：未获取到文件内容");
			}
		}
		return resultMap;
	}
}
