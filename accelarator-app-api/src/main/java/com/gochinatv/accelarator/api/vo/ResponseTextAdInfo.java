package com.gochinatv.accelarator.api.vo;

import java.util.List;

import com.gochinatv.accelarator.api.bean.TextAdInfo;
import com.wordnik.swagger.annotations.ApiModelProperty;


public class ResponseTextAdInfo  extends BaseVo {
	
	@ApiModelProperty(value = "文字轮播时间间隔")
	private int adTextInterval;//轮播时间间隔，以秒为单位
	@ApiModelProperty(value = "数据列表")
	private List<TextAdInfo> data;

	public int getAdTextInterval() {
		return adTextInterval;
	}

	public void setAdTextInterval(int adTextInterval) {
		this.adTextInterval = adTextInterval;
	}

	public List<TextAdInfo> getData() {
		return data;
	}

	public void setData(List<TextAdInfo> data) {
		this.data = data;
	}

}
