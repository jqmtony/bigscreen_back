package com.gochinatv.accelarator.bmapi.util.imageUpload;

import java.io.Serializable;

public class ProgramException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1913263321312485172L;

	/**
	 * 默认构造函数，反序列化时使用
	 */
	public ProgramException() {

	}

	/**
	 * @param cause
	 *            原因异常
	 */
	public ProgramException(Throwable cause) {
		super(cause.getMessage(), cause);
	}

	/**
	 * @param message
	 *            错误消息
	 */
	public ProgramException(String message) {
		super(message);
	}

	/**
	 * @param message
	 *            错误消息
	 * @param cause
	 *            原因异常
	 */
	public ProgramException(String message, Throwable cause) {
		super(message, cause);
	}

}
