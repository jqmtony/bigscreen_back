package com.gochinatv.accelarator.api.bean;

import java.io.Serializable;

public class UploadLog  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long id;
	private String mac;//mac地址
	private String msg;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
