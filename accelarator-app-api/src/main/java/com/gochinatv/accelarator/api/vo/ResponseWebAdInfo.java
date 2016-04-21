package com.gochinatv.accelarator.api.vo;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModelProperty;


public class ResponseWebAdInfo  extends BaseVo  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "web广告的地址")
	private String adWebUrl;

	public String getAdWebUrl() {
		return adWebUrl;
	}

	public void setAdWebUrl(String adWebUrl) {
		this.adWebUrl = adWebUrl;
	}
}
