package com.gochinatv.accelarator.bmapi.bean;

import java.sql.Timestamp;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 返回信息
 * @author LBQ-PC
 *
 */
public class BaseVo {

	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态(默认0 成功 1失败)") 
	private int status;
	
	/**
	 * 消息
	 */
	@ApiModelProperty(value = "消息")
	private String message;
	
	/**
	 * 服务器当前时间
	 */
	@ApiModelProperty(value = "服务器当前时间戳，sample： 1434553831")
	private Long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}


}
