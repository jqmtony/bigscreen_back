package com.gochinatv.accelarator.bmapi.bean;

import java.io.Serializable;
import java.util.List;

import com.wordnik.swagger.annotations.ApiModelProperty;

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

	@ApiModelProperty(value = "商铺")
	private Place place;
	@ApiModelProperty(value = "商铺下的设备列表")
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
