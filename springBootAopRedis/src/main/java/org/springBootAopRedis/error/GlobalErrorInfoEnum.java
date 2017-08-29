package org.springBootAopRedis.error;

/***
 * 应用系统级别的错误码
 * @author Administrator
 *
 */
public enum GlobalErrorInfoEnum implements ErrorInfoInterface{

	/** 成功 */
    SUCCESS("0", "success"),
    /** 服务不存在 */
    NOT_FOUND("-1", "service not found"),
    /** 权限验证失败 */
	NOT_PERMIS("20000","Permission validation failed"),
	/** 登录超时 */
	TIME_OUT("20001","Login timeout");

    private String code;

    private String message;

    GlobalErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }

}
