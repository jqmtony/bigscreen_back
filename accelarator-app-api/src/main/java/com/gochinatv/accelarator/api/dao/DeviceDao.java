package com.gochinatv.accelarator.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gochinatv.accelarator.api.bean.AdInfo;
import com.gochinatv.accelarator.api.bean.Device;
import com.gochinatv.accelarator.api.bean.ImageAdInfo;
import com.gochinatv.accelarator.api.bean.UploadLog;

public interface DeviceDao {

	public void saveDeviceImage(Device device);
	
	public void uploadLog(UploadLog uploadLog);
	public Device queryDeviceByMac(String mac);
	
	public List<ImageAdInfo> queryImageAdInfoList(@Param(value="deviceId")long deviceId) ;
	
	
	public List<AdInfo> getAdInfoByDeviceId(@Param(value="deviceId")long deviceId, @Param(value="type")int type,@Param(value="time")String time);
	

	public List<AdInfo> getAdInfoByPlaceId(@Param(value="placeId")long placeId, @Param(value="type")int type, @Param(value="time") String time);
	

	public List<AdInfo> getAdInfoByCityCode(@Param(value="cityCode")String cityCode, @Param(value="type")int type,  @Param(value="time")String time);
}
