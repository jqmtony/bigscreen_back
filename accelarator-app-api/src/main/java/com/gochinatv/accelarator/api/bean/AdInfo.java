package com.gochinatv.accelarator.api.bean;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class AdInfo  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "视频ID")
	private int adVideoId;
	@ApiModelProperty(value = "视频名称")
	private String adVideoName;	
	@ApiModelProperty(value = "视频播放顺序,从0开始计数")
	private int adVideoIndex;
	@ApiModelProperty(value = "视频播放地址")
	private String adVideoUrl;
	
	
	public int getAdVideoId() {
		return adVideoId;
	}
	public void setAdVideoId(int adVideoId) {
		this.adVideoId = adVideoId;
	}
	public String getAdVideoName() {
		return adVideoName;
	}
	public void setAdVideoName(String adVideoName) {
		this.adVideoName = adVideoName;
	}
	public int getAdVideoIndex() {
		return adVideoIndex;
	}
	public void setAdVideoIndex(int adVideoIndex) {
		this.adVideoIndex = adVideoIndex;
	}
	public String getAdVideoUrl() {
		return adVideoUrl;
	}
	public void setAdVideoUrl(String adVideoUrl) {
		this.adVideoUrl = adVideoUrl;
	}
	
}
