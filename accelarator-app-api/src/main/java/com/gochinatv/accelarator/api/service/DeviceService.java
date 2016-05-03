package com.gochinatv.accelarator.api.service;

import java.util.List;

import com.gochinatv.accelarator.api.bean.AdInfo;
import com.gochinatv.accelarator.api.bean.Device;
import com.gochinatv.accelarator.api.bean.UploadLog;
import com.gochinatv.accelarator.api.vo.ResponseDeviceInfo;
import com.gochinatv.accelarator.api.vo.ResponseImageAdInfo;

public interface DeviceService {

	public void  saveDeviceImage(Device device);
	

	public void  uploadLog(UploadLog uploadLog);
	
	
	public ResponseImageAdInfo queryImageAdInfoList(String mac);
	
	public ResponseDeviceInfo getDeviceInfo(String mac)  throws Exception ;
	
	/**
	 * 返回time时间对于mac的 视频广告列表信息
	 * @param mac
	 * @param time
	 * @return
	 */
	public List<AdInfo> getAdInfo(String mac,String time);

}
