package com.gochinatv.accelarator.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gochinatv.accelarator.dao.DacDeviceVideoDao;
import com.gochinatv.accelarator.dao.DeviceLogDao;
import com.gochinatv.accelarator.dao.entity.DacDeviceVideo;
import com.gochinatv.accelarator.dao.entity.DeviceLog;
import com.gochinatv.accelarator.framework.web.base.utils.DateUtils;


@SuppressWarnings("all")
@Component(value = "dataCleanTask")
public class DataCleanTask {
	
	@Autowired
	private DeviceLogDao deviceLogDao;
	
	@Autowired
	private DacDeviceVideoDao dacDeviceVideoDao;
	
	
	private Logger LOGGER = LoggerFactory.getLogger(DataCleanTask.class);
	
	
	/**
	 * 日志数据清洗
	 * @throws Exception 
	 */
	public void logDataClean() throws Exception{
		LOGGER.info("======================开始数据清洗========================");
		List<DeviceLog> list = deviceLogDao.getList();
		List<DacDeviceVideo> ddvList = new ArrayList<DacDeviceVideo>(); 
		for (DeviceLog log : list) {
			
			String msg = log.getMsg();
			int type = log.getType();
			String createTime = DateUtils.formatDateString(log.getCreateTime());
			String mac = log.getMac();
			
			JSONObject object = JSONObject.parseObject(msg);
			String businessTime = "",videoName="";
			int videoId = 0;
			if(type==101){
				businessTime = object.getString("time");
				
				DacDeviceVideo ddv = new DacDeviceVideo();
				ddv.setMac(mac);
				ddv.setCreateTime(createTime);
				ddv.setType(type);
				ddv.setBusinessTime(businessTime);
				ddvList.add(ddv);
				
			}else if(type==102){
			    videoId = object.getIntValue("videoId");
		        videoName = object.getString("videoName");
		        businessTime = object.getString("downloadTime");
		        
		        DacDeviceVideo ddv = new DacDeviceVideo();
				ddv.setMac(mac);
				ddv.setCreateTime(createTime);
				ddv.setType(type);
				ddv.setVideoId(videoId);
				ddv.setVideoName(videoName);
				ddv.setBusinessTime(businessTime);
				ddvList.add(ddv);
				
			}else if(type==103){
			    videoId = object.getIntValue("videoId");
		    	videoName = object.getString("videoName");
		    	
		    	DacDeviceVideo ddv = new DacDeviceVideo();
				ddv.setMac(mac);
				ddv.setCreateTime(createTime);
				ddv.setType(type);
				ddv.setVideoId(videoId);
				ddv.setVideoName(videoName);
				ddvList.add(ddv);
				
			}else if(type==104){
				JSONArray data = object.getJSONArray("deleteData");
				for (Object obj : data) {
					JSONObject delJson = (JSONObject)obj;
				    videoId = delJson.getIntValue("videoId");
			    	videoName = delJson.getString("videoName");
			    	
			    	DacDeviceVideo ddv = new DacDeviceVideo();
					ddv.setMac(mac);
					ddv.setCreateTime(createTime);
					ddv.setType(type);
					ddv.setVideoId(videoId);
					ddv.setVideoName(videoName);
					ddvList.add(ddv);
				}
			}
		}
		dacDeviceVideoDao.saveAll(ddvList);
		deviceLogDao.updateAll(list);
		dacDeviceVideoDao.updateDevice();
		dacDeviceVideoDao.updateAdvertiser();
		LOGGER.info("======================结束数据清洗========================");
	}
	
}
