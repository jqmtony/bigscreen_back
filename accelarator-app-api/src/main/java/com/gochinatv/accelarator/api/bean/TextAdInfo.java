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
	
}
