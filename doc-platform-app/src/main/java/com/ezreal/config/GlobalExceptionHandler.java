package com.ezreal.config;


import com.ezreal.types.common.Constants;
import com.ezreal.types.common.Response;
import com.ezreal.types.common.ResultUtils;
import com.ezreal.types.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Response<String> handleBusinessException(BusinessException exception) {
        return ResultUtils.fail(exception.getCode(), exception.getInfo());
    }

    /**
     * 处理@Validated参数校验失败异常
     *
     * @param e 异常类
     * @return 响应
     */
    // <1> 处理 form data方式调用接口校验失败抛出的异常
    @ExceptionHandler(BindException.class)
    public Response<String> bindExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : collect) {
            stringBuilder.append(s).append(",");
        }
        String message = stringBuilder.substring(0, stringBuilder.length() - 1);
        log.error("参数错误，{}，message：{}", e.getBindingResult().getFieldErrors(), message);
        return ResultUtils.fail(Constants.ResponseCode.ERROR_PARAMS.getCode(), message);
    }

    // <2> 处理 json 请求体调用接口校验失败抛出的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : collect) {
            stringBuilder.append(s).append(",");
        }
        String message = stringBuilder.substring(0, stringBuilder.length() - 1);
        log.error("参数错误，{}，message：{}", e.getBindingResult().getFieldErrors(), message);
        return ResultUtils.fail(Constants.ResponseCode.ERROR_PARAMS.getCode(), message);
    }

    // <3> 处理单个参数校验失败抛出的异常
    @ExceptionHandler(ConstraintViolationException.class)
    public Response<String> constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> collect = constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : collect) {
            stringBuilder.append(s).append(",");
        }
        String message = stringBuilder.substring(0, stringBuilder.length() - 1);
        log.error("参数错误，{}，message：{}", e.getMessage(), message);
        return ResultUtils.fail(Constants.ResponseCode.ERROR_PARAMS.getCode(), message);
    }
}
