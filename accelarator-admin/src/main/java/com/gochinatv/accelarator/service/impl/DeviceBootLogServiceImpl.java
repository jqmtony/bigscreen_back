package com.gochinatv.accelarator.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.dao.DeviceBootLogDao;
import com.gochinatv.accelarator.dao.entity.DeviceBootLog;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.response.DeviceBootLogStatics;
import com.gochinatv.accelarator.service.DeviceBootLogService;
import com.gochinatv.accelarator.util.DateUtils;

@Service
public class DeviceBootLogServiceImpl extends BaseServiceImpl<DeviceBootLog> implements DeviceBootLogService {

	@Autowired
	private DeviceBootLogDao deviceBootLogDao;
	
	@Override
	protected BaseDao<DeviceBootLog> getDao() {
		return deviceBootLogDao;
	}
	
	/**
	 * 日期列表  0701 0702 0703
	 * 
	 * 数据 code  开机时长    日期
	 * 	1    10     0701  
	 *  1    20     0702  
	 *  1    10     0703  
	 *  
	 *  2    10     0701  
	 *  2    20     0702  
	 *  2    10     0703  
	 *  
	 *  3    10     0701  
	 *  
	 *  
	 *  最终结果 1 《10,20,10>  2<10,20,10> 3<10,0,10>
	 *  
	 *  先初始化;
	 * K:1 v:[0,0,0]
	 * 2[0,0,0]
	 * 3[0,0,0]
	 */
	@Override
	public List<DeviceBootLogStatics> queryStatPic(DeviceBootLog deviceBootLog){
		
		String startTime = deviceBootLog.getStartTime();
		String endTime = deviceBootLog.getEndTime();
		
		if(StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)
				|| StringUtils.equals("undefined", endTime)|| StringUtils.equals("undefined", startTime)){
			endTime = DateUtils.convert(new Date(), DateUtils.DATE_FORMAT);
			startTime= DateUtils.convert( DateUtils.getNextNextDate(new Date(),-7), DateUtils.DATE_FORMAT);
			
			deviceBootLog.setStartTime(startTime);
			deviceBootLog.setEndTime(endTime);
		}
		
		List<DeviceBootLogStatics> deviceBootLogStaticsLiST = new ArrayList<DeviceBootLogStatics>();
		List<String> codeList = null;
		if(StringUtils.isNotBlank(deviceBootLog.getCode())){
			codeList = new ArrayList<String>();
			String[] codes = deviceBootLog.getCode().split(",");
			for(String code : codes){
				codeList.add(code);
			}
		}
		deviceBootLog.setCodeList(codeList);
		List<DeviceBootLog> list = deviceBootLogDao.queryStatPic( deviceBootLog);
		List<String> dateList = queryDateList(deviceBootLog.getStartTime(), deviceBootLog.getEndTime());
		Map<String, LinkedList<Integer>> map = new HashMap<String, LinkedList<Integer>>();
		
	
		
		//2:初始化map《key为code valuse为已经初始化都为0的linklist》
		for(DeviceBootLog deviceBootLog2 : list){
			String key = deviceBootLog2.getCode();
			if(map.get(key) ==null){
				//1:初始化linklist  开机时长都为0
				LinkedList<Integer> durationList = new LinkedList<Integer>();
				for(int i=0;i<dateList.size();i++){
					durationList.add(0);
				}
				map.put(key, durationList);
			}
		}
		//往linklist填充数据
		for(DeviceBootLog deviceBootLog2 : list){
			String key = deviceBootLog2.getCode();
			LinkedList<Integer> list2 = map.get(key);
			int index = index(deviceBootLog2.getStartTime(),dateList);
			
			list2.remove(index);
			list2.add(index, deviceBootLog2.getDuration());
		}
		
		
		for(Entry<String, LinkedList<Integer>> entry : map.entrySet()){
			
			DeviceBootLogStatics deviceBootLogStatics = new DeviceBootLogStatics();
			deviceBootLogStatics.setDeviceCode(entry.getKey());
			deviceBootLogStatics.setDuration(entry.getValue());
			deviceBootLogStatics.setDateList(dateList);
			
			deviceBootLogStaticsLiST.add(deviceBootLogStatics);
		}
		
		return deviceBootLogStaticsLiST;
	}
	
	
	/**
	 * 当前时间再 时间间隔中的排序
	 * 比如 0701 0702 0703  则0702为1
	 * @param nowTime
	 * @return
	 */
	public int index(String nowTime,List<String> dateList){
		 for(int i =0;i<dateList.size();i++){
			 if( dateList.get(i).equals(nowTime) ){
				return i;
			 }
		 }
		 return 0;
	}
	/**
	 * 计算时间段（比如输入0610到0710则列出所有时间段）
	 * @return
	 */
	private List<String> queryDateList(String startTime,String endTime){
		return DateUtils.queryDateList(startTime, endTime);
	}
}
