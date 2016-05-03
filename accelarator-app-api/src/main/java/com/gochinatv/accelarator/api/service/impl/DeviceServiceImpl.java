package com.gochinatv.accelarator.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.api.bean.AdInfo;
import com.gochinatv.accelarator.api.bean.Device;
import com.gochinatv.accelarator.api.bean.ImageAdInfo;
import com.gochinatv.accelarator.api.bean.Layout;
import com.gochinatv.accelarator.api.bean.ScreenShot;
import com.gochinatv.accelarator.api.bean.UploadLog;
import com.gochinatv.accelarator.api.dao.DeviceDao;
import com.gochinatv.accelarator.api.service.DeviceService;
import com.gochinatv.accelarator.api.util.AccelaratorConfig;
import com.gochinatv.accelarator.api.vo.ResponseDeviceInfo;
import com.gochinatv.accelarator.api.vo.ResponseImageAdInfo;

@Service
public class DeviceServiceImpl  implements DeviceService{


	private static Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);
	
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Override
	public void saveDeviceImage(Device device) {
		deviceDao.saveDeviceImage(device);		
	}

	@Override
	public void  uploadLog(UploadLog uploadLog){
		deviceDao.uploadLog(uploadLog);		
	}
	
	@Override
	public ResponseImageAdInfo queryImageAdInfoList(String mac) {
		ResponseImageAdInfo responseImageAdInfo  = new ResponseImageAdInfo();
		Device device = queryDeviceByMac(mac);
		responseImageAdInfo.setAdImgInterval(device.getRefreshTime());
		List<ImageAdInfo> data =deviceDao.queryImageAdInfoList(device.getId());
		responseImageAdInfo.setData(data);
		return responseImageAdInfo;
	}

	@Override
	public ResponseDeviceInfo getDeviceInfo(String mac) throws Exception {
		ResponseDeviceInfo responseDeviceInfo = new ResponseDeviceInfo();
		
		Device device = queryDeviceByMac(mac);
		
		responseDeviceInfo.setAdStruct(device.getScreenNum());
		responseDeviceInfo.setPollInterval(AccelaratorConfig.DEVICE_DEFAULT_PLLLINTERVAL);
		ScreenShot screenShot = getScreenShot();
		if(device.getScreenShotInterval()>0){
			screenShot.setScreenShotInterval(device.getScreenShotInterval());
		}
		responseDeviceInfo.setScreenShot(screenShot);
		List<Layout> layoutList = getLayOutList(device);
		responseDeviceInfo.setLayout(layoutList);		
		
		return responseDeviceInfo;
	}

	private Device queryDeviceByMac(String mac) {
		Device device = null;
		try {
			 device = deviceDao.queryDeviceByMac(mac);
		} catch (Exception e) {
			logger.error("===mac:"+mac+" queryDeviceByMac error "+e.getMessage());
			device = deviceDao.queryDeviceByMac(AccelaratorConfig.DEVICE_DEFAULT_MAC);
		}
		if(device ==null){
			logger.error("===mac:"+mac+" has no device");
			device = deviceDao.queryDeviceByMac(AccelaratorConfig.DEVICE_DEFAULT_MAC);
		}
		return device;
	}

	private ScreenShot getScreenShot() {
		ScreenShot screenShot = new ScreenShot();
		screenShot.setScreenShotImgH(AccelaratorConfig.DEVICE_SCREEN_SHOTIMGH);
		screenShot.setScreenShotImgW(AccelaratorConfig.DEVICE_SCREEN_SHOTIMGW);
		screenShot.setScreenShotInterval(AccelaratorConfig.DEVICE_SCREEN_INTERVAL);
		return screenShot;
	}

	/**
	 * 根据 mac time 找到设备对于adlist
	       如果没有 往上一级查找
	 */
	@Override
	public List<AdInfo> getAdInfo(String mac, String time) {
		List<AdInfo> adList = new ArrayList<AdInfo>();
		

		Device device = queryDeviceByMac(mac);
		
		/*//根据设备id 查出广告列表
		adList = deviceDao.getAdInfoByDeviceId(device.getId(),device.getType(), time);
		
		if(adList==null || adList.size()==0){
			//根据商铺id 查出广告列表
			adList = deviceDao.getAdInfoByPlaceId(device.getPlaceId(),device.getType(), time);
			logger.info("===getAdInfoByPlaceId===mac:"+mac+" adList.size:"+adList.size());
		}*/
		if(adList==null || adList.size()==0){
			//根据城市id 查出广告列表
			adList = deviceDao.getAdInfoByCityCode(device.getCityCode(),device.getType(), time);
			logger.info("===getAdInfoByCityCode===mac:"+mac+" adList.size:"+adList.size());
		}
		
		//使用默认广告列表
		if(adList==null || adList.size()==0){
			device = deviceDao.queryDeviceByMac(AccelaratorConfig.DEVICE_DEFAULT_MAC);
			adList = deviceDao.getAdInfoByCityCode(device.getCityCode(),device.getType(),AccelaratorConfig.DEVICE_DEFAULT_TIME);
			logger.info("===getDefaultAdList===mac:"+mac+" adList.size:"+adList.size());
		}
		return adList;
	}

	/**
	 *  获取设备layout布局
	 * @param device
	 * @return
	 */
	private List<Layout> getLayOutList(Device device) {
		List<Layout> layoutList = new ArrayList<Layout>();
		
		if(device.getScreenNum() == AccelaratorConfig.DEVICE_ONE_SCREEN){
			Layout layout = new Layout();
			layout.setAdType(AccelaratorConfig.DEVICE_SCREEN1_ADTYPE);
			layout.setAdHeight(AccelaratorConfig.DEVICE_SCREEN1_ADHEIGHT);
			layout.setAdLeft(AccelaratorConfig.DEVICE_SCREEN1_ADLEFT);
			layout.setAdTop(AccelaratorConfig.DEVICE_SCREEN1_ADTOP);
			layout.setAdWidth(AccelaratorConfig.DEVICE_SCREEN1_ADWIDTH);
			layoutList.add(layout);
		}else/* if(device.getScreenNum() == AccelaratorConfig.DEVICE_FOUR_SCREEN)*/{
			
			Layout layout1 = new Layout();
			layout1.setAdType(AccelaratorConfig.DEVICE_SCREEN1_ADTYPE);
			layout1.setAdHeight(AccelaratorConfig.DEVICE_SCREEN1_ADHEIGHT);
			layout1.setAdLeft(AccelaratorConfig.DEVICE_SCREEN1_ADLEFT);
			layout1.setAdTop(AccelaratorConfig.DEVICE_SCREEN1_ADTOP);
			layout1.setAdWidth(AccelaratorConfig.DEVICE_SCREEN1_ADWIDTH);
			layoutList.add(layout1);
			
			Layout layout2 = new Layout();
			layout2.setAdType(AccelaratorConfig.DEVICE_SCREEN2_ADTYPE);
			layout2.setAdHeight(AccelaratorConfig.DEVICE_SCREEN2_ADHEIGHT);
			layout2.setAdLeft(AccelaratorConfig.DEVICE_SCREEN2_ADLEFT);
			layout2.setAdTop(AccelaratorConfig.DEVICE_SCREEN2_ADTOP);
			layout2.setAdWidth(AccelaratorConfig.DEVICE_SCREEN2_ADWIDTH);
			layoutList.add(layout2);
			
			
			Layout layout3 = new Layout();
			layout3.setAdType(AccelaratorConfig.DEVICE_SCREEN3_ADTYPE);
			layout3.setAdHeight(AccelaratorConfig.DEVICE_SCREEN3_ADHEIGHT);
			layout3.setAdLeft(AccelaratorConfig.DEVICE_SCREEN3_ADLEFT);
			layout3.setAdTop(AccelaratorConfig.DEVICE_SCREEN3_ADTOP);
			layout3.setAdWidth(AccelaratorConfig.DEVICE_SCREEN3_ADWIDTH);
			layoutList.add(layout3);
			
			Layout layout4 = new Layout();
			layout4.setAdType(AccelaratorConfig.DEVICE_SCREEN4_ADTYPE);
			layout4.setAdHeight(AccelaratorConfig.DEVICE_SCREEN4_ADHEIGHT);
			layout4.setAdLeft(AccelaratorConfig.DEVICE_SCREEN4_ADLEFT);
			layout4.setAdTop(AccelaratorConfig.DEVICE_SCREEN4_ADTOP);
			layout4.setAdWidth(AccelaratorConfig.DEVICE_SCREEN4_ADWIDTH);
			layoutList.add(layout4);
		}
		return layoutList;
	}
}
