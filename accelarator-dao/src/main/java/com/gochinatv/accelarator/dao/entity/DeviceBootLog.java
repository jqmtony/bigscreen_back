package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DeviceBootLog  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long id;
	private long deviceId;//设备id
	private String code;//设备编码
	private Date bootTime;//开机时间
	
	private String mac;//mac地址
	private String cname;//商铺编号
	private String countryCode;//国家code
	private String areaCode;//地区code
	private String cityCode;//城市code
	private Date createTime;
	private String startTime;
	private String endTime;
	private String versionNum;// 版本号
	private String versionName;// 版本名
	
	private int duration;
	private List<String> codeList;
	
	public List<String> getCodeList() {
		return codeList;
	}
	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
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
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

}
