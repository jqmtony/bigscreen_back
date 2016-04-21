package com.gochinatv.accelarator.api.vo;

import java.io.Serializable;
import java.util.List;

import com.gochinatv.accelarator.api.bean.Layout;
import com.gochinatv.accelarator.api.bean.ScreenShot;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 *广告体接口响应参数
 */
public class ResponseDeviceInfo  extends BaseVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "广告展现形式1:4个广告位", required = true)
    private int adStruct;

	@ApiModelProperty(value = "接口轮询时间,单位毫秒", required = true)
	private int pollInterval;
	
	@ApiModelProperty(value = "截屏参数对象")
	private ScreenShot screenShot;
	
	@ApiModelProperty(value = "布局结构对象")
	private List<Layout> layout;
	
	
	public int getPollInterval() {
		return pollInterval;
	}

	public void setPollInterval(int pollInterval) {
		this.pollInterval = pollInterval;
	}

	public int getAdStruct() {
		return adStruct;
	}

	public void setAdStruct(int adStruct) {
		this.adStruct = adStruct;
	}

	public ScreenShot getScreenShot() {
		return screenShot;
	}

	public void setScreenShot(ScreenShot screenShot) {
		this.screenShot = screenShot;
	}

	public List<Layout> getLayout() {
		return layout;
	}

	public void setLayout(List<Layout> layout) {
		this.layout = layout;
	}
}
