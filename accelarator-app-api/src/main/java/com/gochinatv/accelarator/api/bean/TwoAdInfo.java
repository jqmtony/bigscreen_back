package com.gochinatv.accelarator.api.bean;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class TwoAdInfo  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "文字id")
	private int adImageId;
	@ApiModelProperty(value = "文字广告排序")
	private int adImageIndex;//	播放顺序
	@ApiModelProperty(value = "文字广告内容")
	private String adImageTitle;//url
	@ApiModelProperty(value = "文字广告内容")
	private String adImageUrl;//url
	@ApiModelProperty(value = "轮播时间间隔，以毫秒为单位")
	private int adImageInterval;//轮播时间间隔，以秒为单位
	
	
	public int getAdImageId() {
		return adImageId;
	}
	public void setAdImageId(int adImageId) {
		this.adImageId = adImageId;
	}
	public int getAdImageIndex() {
		return adImageIndex;
	}
	public void setAdImageIndex(int adImageIndex) {
		this.adImageIndex = adImageIndex;
	}
	public String getAdImageTitle() {
		return adImageTitle;
	}
	public void setAdImageTitle(String adImageTitle) {
		this.adImageTitle = adImageTitle;
	}
	public String getAdImageUrl() {
		return adImageUrl;
	}
	public void setAdImageUrl(String adImageUrl) {
		this.adImageUrl = adImageUrl;
	}
	public int getAdImageInterval() {
		return adImageInterval;
	}
	public void setAdImageInterval(int adImageInterval) {
		this.adImageInterval = adImageInterval;
	}
	
}
