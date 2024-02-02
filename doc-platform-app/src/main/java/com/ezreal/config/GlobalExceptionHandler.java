package com.ezreal.config;


import com.ezreal.types.common.Response;
import com.ezreal.types.common.ResultUtils;
import com.ezreal.types.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Response<String> handleBusinessException(BusinessException exception) {
        return ResultUtils.fail(exception.getCode(), exception.getInfo());
    }
}
