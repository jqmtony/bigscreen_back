package com.gochinatv.accelarator.api.vo;

import java.io.Serializable;
import java.util.List;

import com.gochinatv.accelarator.api.bean.TextAdInfo;
import com.gochinatv.accelarator.api.bean.TwoAdInfo;
import com.wordnik.swagger.annotations.ApiModelProperty;


public class ResponseTwoAdInfo  extends BaseVo  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "文字轮播时间间隔")
	private int adTextInterval;//轮播时间间隔，以秒为单位
	@ApiModelProperty(value = "数据列表")
	private List<TwoAdInfo> data;

	public int getAdTextInterval() {
		return adTextInterval;
	}

	public void setAdTextInterval(int adTextInterval) {
		this.adTextInterval = adTextInterval;
	}

	public List<TwoAdInfo> getData() {
		return data;
	}

	public void setData(List<TwoAdInfo> data) {
		this.data = data;
	}

}
