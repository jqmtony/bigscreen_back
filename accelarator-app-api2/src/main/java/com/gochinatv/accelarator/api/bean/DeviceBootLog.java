package com.gochinatv.accelarator.api.bean;

import java.io.Serializable;
import java.util.Date;

public class DeviceBootLog  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long id;
	private long deviceId;//设备id
	private String code;//设备编码
	private Date bootTime;//开机时间
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getBootTime() {
		return bootTime;
	}
	public void setBootTime(Date bootTime) {
		this.bootTime = bootTime;
	}
	
	
}
