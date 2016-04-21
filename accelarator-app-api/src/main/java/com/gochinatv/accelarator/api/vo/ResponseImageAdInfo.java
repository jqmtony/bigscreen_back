package com.gochinatv.accelarator.api.vo;

import java.io.Serializable;
import java.util.List;

import com.gochinatv.accelarator.api.bean.ImageAdInfo;
import com.wordnik.swagger.annotations.ApiModelProperty;


public class ResponseImageAdInfo  extends BaseVo  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	@ApiModelProperty(value = "图片轮播时间间隔，以秒为单位")
	private int adImgInterval;//图片轮播时间间隔，以秒为单位
	
	@ApiModelProperty(value = "图片数据列表")
	private List<ImageAdInfo> data;

	public int getAdImgInterval() {
		return adImgInterval;
	}

	public void setAdImgInterval(int adImgInterval) {
		this.adImgInterval = adImgInterval;
	}

	public List<ImageAdInfo> getData() {
		return data;
	}

	public void setData(List<ImageAdInfo> data) {
		this.data = data;
	}
	

}
