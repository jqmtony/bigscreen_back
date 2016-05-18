package com.gochinatv.accelarator.api.bean;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class TextAdInfo  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "文字id")
	private int adTextId;
	@ApiModelProperty(value = "文字广告排序")
	private int adTextIndex;//	播放顺序
	@ApiModelProperty(value = "文字广告内容")
	private String adTextStr;//url
	@ApiModelProperty(value = "轮播时间间隔，以毫秒为单位")
	private int adTextInterval;//轮播时间间隔，以秒为单位
	
	public int getAdTextId() {
		return adTextId;
	}
	public void setAdTextId(int adTextId) {
		this.adTextId = adTextId;
	}
	public int getAdTextIndex() {
		return adTextIndex;
	}
	public void setAdTextIndex(int adTextIndex) {
		this.adTextIndex = adTextIndex;
	}
	public String getAdTextStr() {
		return adTextStr;
	}
	public void setAdTextStr(String adTextStr) {
		this.adTextStr = adTextStr;
	}
	public int getAdTextInterval() {
		return adTextInterval;
	}
	public void setAdTextInterval(int adTextInterval) {
		this.adTextInterval = adTextInterval;
	}
	
}
