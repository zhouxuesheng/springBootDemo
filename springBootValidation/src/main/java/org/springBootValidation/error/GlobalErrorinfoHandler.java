package org.springBootValidation.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/***
 * 统一的异常处理
 * @author Administrator
 *
 */
@RestControllerAdvice
public class GlobalErrorinfoHandler {

    @ExceptionHandler(value = GlobalErrorInfoException.class)
    public ResultBody errorHandlerOverJson(HttpServletRequest request, GlobalErrorInfoException exception) {
        ErrorInfoInterface errorInfo = exception.getInfoInterface();
        ResultBody result = new ResultBody(errorInfo);
        return result;
    }
}
