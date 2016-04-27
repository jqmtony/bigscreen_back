package com.gochinatv.accelarator.api.bean;

import java.io.Serializable;

public class ErrorMsg  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
		 * 
		 */
	private String errorCode;
	private String errorMsg;
	private String description;
	private String helpInfo;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHelpInfo() {
		return helpInfo;
	}

	public void setHelpInfo(String helpInfo) {
		this.helpInfo = helpInfo;
	}

}
