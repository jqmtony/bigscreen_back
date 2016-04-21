package com.gochinatv.accelarator.api.bean;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class ImageAdInfo  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "图片ID")
	private int adImgId;
	@ApiModelProperty(value = "图片播放顺序")
	private int adImgIndex;
	@ApiModelProperty(value = "图片url")
	private String adImgUrl;
	@ApiModelProperty(value = "菜单名字")
	private String adImgName;
	@ApiModelProperty(value = "菜单价格")
	private String adImgPrice;
	@ApiModelProperty(value = "refreshTime")
	private int refreshTime;
	
	@ApiModelProperty(value = "英文名称")
	private String adImagEName;
	
	private int deviceId;
	private int businessId;
	private int placeId;
	
	
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	public String getAdImagEName() {
		return adImagEName;
	}
	public void setAdImagEName(String adImagEName) {
		this.adImagEName = adImagEName;
	}
	public int getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(int refreshTime) {
		this.refreshTime = refreshTime;
	}
	public int getAdImgId() {
		return adImgId;
	}
	public void setAdImgId(int adImgId) {
		this.adImgId = adImgId;
	}
	public int getAdImgIndex() {
		return adImgIndex;
	}
	public void setAdImgIndex(int adImgIndex) {
		this.adImgIndex = adImgIndex;
	}
	public String getAdImgUrl() {
		return adImgUrl;
	}
	public void setAdImgUrl(String adImgUrl) {
		this.adImgUrl = adImgUrl;
	}
	public String getAdImgName() {
		return adImgName;
	}
	public void setAdImgName(String adImgName) {
		this.adImgName = adImgName;
	}
	public String getAdImgPrice() {
		return adImgPrice;
	}
	public void setAdImgPrice(String adImgPrice) {
		this.adImgPrice = adImgPrice;
	}
	
}
