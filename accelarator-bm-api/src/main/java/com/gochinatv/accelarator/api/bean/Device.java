package com.gochinatv.accelarator.api.bean;

public class Device {

	private long id;
	private String mac;//mac地址
	private String imageUrl;//截图 图片URL
	private int placeId;//商铺id
	private String cityCode;//城市code
	private int screenNum;//该设备屏幕数
	
	private String createTime;
	
	private int screenShotInterval;
	
	private int type;//商铺type
	
	
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
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