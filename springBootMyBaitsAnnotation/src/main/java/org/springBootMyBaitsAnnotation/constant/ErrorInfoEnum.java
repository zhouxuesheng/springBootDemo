package org.springBootMyBaitsAnnotation.constant;

import org.springBootMyBaitsAnnotation.error.ErrorInfoInterface;

/***
 * 业务代码错误一层
 * @author Administrator
 *
 */
public enum ErrorInfoEnum implements ErrorInfoInterface {

	PARAMS_NO_COMPLETE("000001","params no complete");
	
	
	private String code;
	private String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private ErrorInfoEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	
}
