package com.gochinatv.accelarator.api.vo;

import java.util.List;

import com.gochinatv.accelarator.api.bean.Layout;
import com.gochinatv.accelarator.api.bean.ScreenShot;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 *广告体接口响应参数
 */
public class ResponseDeviceInfo  extends BaseVo {
	
	@ApiModelProperty(value = "广告展现形式0:只有视频；1:4个广告位", required = true)
    private int adStruct;

	@ApiModelProperty(value = "截屏参数对象")
	private ScreenShot screenShot;
	
	@ApiModelProperty(value = "布局结构对象")
	private List<Layout> layout;

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
