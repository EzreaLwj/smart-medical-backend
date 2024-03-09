package com.ezreal.types.common;

/**
 * @author Ezreal
 * @Date 2024/2/2
 */
public class ResultUtils {

    public static <T> Response<T> success(T data) {
        return new Response<>(data, Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
    }

    public static <T> Response<T> fail(String code, String info) {
        return new Response<>(code, info);
    }

    public static <T> Response<T> fail(Constants.ResponseCode code) {
        return new Response<>(code.getCode(), code.getInfo());
    }
}
