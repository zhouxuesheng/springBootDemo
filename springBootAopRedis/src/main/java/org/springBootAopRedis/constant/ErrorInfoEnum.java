package org.springBootAopRedis.constant;

import org.springBootAopRedis.error.ErrorInfoInterface;

/***
 * 业务代码错误一层
 * @author Administrator
 *
 */
public enum ErrorInfoEnum implements ErrorInfoInterface {

	PARAMS_NO_COMPLETE("000001","params no complete"),
	RESULT_NOT_NULL("000002","result  not null"),
	NOT_LOGGED_IN("000000","Not logged in");
	
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
