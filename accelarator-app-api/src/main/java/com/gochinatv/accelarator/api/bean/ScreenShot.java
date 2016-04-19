package com.gochinatv.accelarator.api.bean;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class ScreenShot {
	
	@ApiModelProperty(value = "截屏时间间隔(以秒为单位)", required = true)
    private int screenShotInterval;
	
	@ApiModelProperty(value = "截图宽度", required = true)
    private int screenShotImgW;
	
	@ApiModelProperty(value = "截图高度", required = true)
    private int screenShotImgH;

	public int getScreenShotInterval() {
		return screenShotInterval;
	}

	public void setScreenShotInterval(int screenShotInterval) {
		this.screenShotInterval = screenShotInterval;
	}

	public int getScreenShotImgW() {
		return screenShotImgW;
	}

	public void setScreenShotImgW(int screenShotImgW) {
		this.screenShotImgW = screenShotImgW;
	}

	public int getScreenShotImgH() {
		return screenShotImgH;
	}

	public void setScreenShotImgH(int screenShotImgH) {
		this.screenShotImgH = screenShotImgH;
	}

	
}
