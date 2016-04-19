package com.gochinatv.accelarator.api.vo;

import com.wordnik.swagger.annotations.ApiModelProperty;


public class ResponseWebAdInfo  extends BaseVo {
	
	@ApiModelProperty(value = "web广告的地址")
	private String adWebUrl;

	public String getAdWebUrl() {
		return adWebUrl;
	}

	public void setAdWebUrl(String adWebUrl) {
		this.adWebUrl = adWebUrl;
	}
}
