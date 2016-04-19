package com.gochinatv.accelarator.api.vo;

import java.util.List;

import com.gochinatv.accelarator.api.bean.AdInfo;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 *视频列表
 */
public class ResponseAdInfo  extends BaseVo {
	
	@ApiModelProperty(value = "当前视频广告列表")
	private List<AdInfo> current;
	@ApiModelProperty(value = "预备播放的广告列表")
	private List<AdInfo> next;

	public List<AdInfo> getCurrent() {
		return current;
	}

	public void setCurrent(List<AdInfo> current) {
		this.current = current;
	}

	public List<AdInfo> getNext() {
		return next;
	}

	public void setNext(List<AdInfo> next) {
		this.next = next;
	}
	
	
}
