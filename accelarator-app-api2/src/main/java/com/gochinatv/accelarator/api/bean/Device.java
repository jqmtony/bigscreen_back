package com.gochinatv.accelarator.api.bean;

import java.io.Serializable;
import java.util.Date;

public class Device  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long id;
	private String mac;//mac地址
	private String imageUrl;//截图 图片URL
	private int placeId;//商铺id
	private String cityCode;//城市code
	private int screenNum;//该设备屏幕数
	private int duration;
	private Date createTime;
	private String versionNum;// 版本号
	private String versionName;// 版本名
	private Date bootTime;//开机时间
	private int status;// 状态
	
	private int screenShotInterval;
	
	private int type;//商铺type
	private int refreshTime;
	
	private String name;
	
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public Date getBootTime() {
		return bootTime;
	}

	public void setBootTime(Date bootTime) {
		this.bootTime = bootTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(int refreshTime) {
		this.refreshTime = refreshTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getScreenShotInterval() {
		return screenShotInterval;
	}

	public void setScreenShotInterval(int screenShotInterval) {
		this.screenShotInterval = screenShotInterval;
	}

	public int getScreenNum() {
		return screenNum;
	}

	public void setScreenNum(int screenNum) {
		this.screenNum = screenNum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

}
