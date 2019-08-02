package com.fuyi.ex;

import com.fuyi.common.ApiResult;
import com.fuyi.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知异常
     *
     * @param e 异常
     * @return json结果
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult handleException(Exception e) {
        // 打印异常堆栈信息
        log.error(e.getMessage(), e);
        return ApiResult.of(ResultCode.INTERNAL_ERROR);
    }

    /**
     * 处理所有业务异常
     *
     * @param e 业务异常
     * @return json结果
     */
    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public ApiResult handleOpdRuntimeException(AuthException e) {
        log.error(e.getMessage(), e);
        return ApiResult.of(e.getResultCode());
    }


    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return FebsResponse
     */
//    @ExceptionHandler(BindException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiResult validExceptionHandler(BindException e) {
//        StringBuilder message = new StringBuilder();
//        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
//        for (FieldError error : fieldErrors) {
//            message.append(error.getField()).append(error.getDefaultMessage()).append(StringPool.COMMA);
//        }
//        message = new StringBuilder(message.substring(0, message.length() - 1));
//        return ApiResult.of(ResultCode.PARAM_ERROR);
//    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return FebsResponse
     */
//    @ExceptionHandler(value = ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public FebsResponse handleConstraintViolationException(ConstraintViolationException e) {
//        StringBuilder message = new StringBuilder();
//        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
//        for (ConstraintViolation<?> violation : violations) {
//            Path path = violation.getPropertyPath();
//            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), StringPool.DOT);
//            message.append(pathArr[1]).append(violation.getMessage()).append(StringPool.COMMA);
//        }
//        message = new StringBuilder(message.substring(0, message.length() - 1));
//        return new FebsResponse().message(message.toString());
//    }

//
//    @ExceptionHandler(value = UnauthorizedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public void handleUnauthorizedException(Exception e) {
//        log.error("权限不足，{}", e.getMessage());
//    }
}
