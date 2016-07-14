package com.gochinatv.accelarator.response;

import java.util.LinkedList;
import java.util.List;

public class DeviceBootLogStatics {
	
	private String deviceCode;
	private List<Integer> duration;
	private List<String> dateList;//日期列表
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public List<Integer> getDuration() {
		return duration;
	}
	public void setDuration(LinkedList<Integer> duration) {
		this.duration = duration;
	}
	public List<String> getDateList() {
		return dateList;
	}
	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}
	
	

}
