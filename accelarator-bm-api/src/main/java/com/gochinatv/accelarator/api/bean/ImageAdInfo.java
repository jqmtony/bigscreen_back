package com.gochinatv.accelarator.api.bean;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class ImageAdInfo {

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
