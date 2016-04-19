package com.gochinatv.accelarator.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gochinatv.accelarator.api.bean.AdInfo;
import com.gochinatv.accelarator.api.bean.Device;
import com.gochinatv.accelarator.api.bean.ImageAdInfo;

public interface DeviceDao {

	public void saveDeviceImage(Device device);
	
	
	public Device queryDeviceByMac(String mac);
	
	public List<ImageAdInfo> queryImageAdInfoList() ;
	
	
	public List<AdInfo> getAdInfoByDeviceId(@Param(value="deviceId")long deviceId, @Param(value="type")int type,@Param(value="time")String time);
	

	public List<AdInfo> getAdInfoByPlaceId(@Param(value="placeId")long placeId, @Param(value="type")int type, @Param(value="time") String time);
	

	public List<AdInfo> getAdInfoByCityCode(@Param(value="cityCode")String cityCode, @Param(value="type")int type,  @Param(value="time")String time);
}
