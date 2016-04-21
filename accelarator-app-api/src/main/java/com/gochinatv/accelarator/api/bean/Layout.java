package com.gochinatv.accelarator.api.bean;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class Layout  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "广告类型 1 video 2 image 3 text  ")
	private int adType;
	@ApiModelProperty(value = "广告的宽")
	private String adWidth;
	@ApiModelProperty(value = "广告的高的集合")
	private String adHeight;
	@ApiModelProperty(value = "x轴的比例位置集合")
	private String adTop;
	@ApiModelProperty(value = "y周的比例位置集合")
	private String adLeft;
	
	public int getAdType() {
		return adType;
	}
	public void setAdType(int adType) {
		this.adType = adType;
	}
	public String getAdWidth() {
		return adWidth;
	}
	public void setAdWidth(String adWidth) {
		this.adWidth = adWidth;
	}
	public String getAdHeight() {
		return adHeight;
	}
	public void setAdHeight(String adHeight) {
		this.adHeight = adHeight;
	}
	public String getAdTop() {
		return adTop;
	}
	public void setAdTop(String adTop) {
		this.adTop = adTop;
	}
	public String getAdLeft() {
		return adLeft;
	}
	public void setAdLeft(String adLeft) {
		this.adLeft = adLeft;
	}
	
}
