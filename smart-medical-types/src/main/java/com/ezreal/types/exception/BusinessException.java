package com.ezreal.types.exception;

import com.ezreal.types.common.Constants;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {

    private String code;

    private String info;

    public BusinessException() {
    }

    public BusinessException(String code, String info) {
        super(info);
        this.code = code;
        this.info = info;
    }

    public BusinessException(Constants.ResponseCode code) {
        super(code.getCode());
        this.code = code.getCode();
        this.info = code.getInfo();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
