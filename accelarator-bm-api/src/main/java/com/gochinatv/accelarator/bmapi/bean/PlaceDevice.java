package com.gochinatv.accelarator.bmapi.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 店铺设备列表
 * @author limr
 *
 */
public class PlaceDevice extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Place place;
	private List<Device> deviceList;
	
	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public List<Device> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}

}
