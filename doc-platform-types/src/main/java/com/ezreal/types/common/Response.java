package com.ezreal.types;

import lombok.Data;

/**
 * @author Ezreal
 * @Date 2024/2/2
 */
@Data
public class Response<T> {

    private T data;

    private String code;

    private String info;
}
