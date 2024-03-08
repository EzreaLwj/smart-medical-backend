package com.ezreal.trigger.dto;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
@Data
public class LoginRequestDTO implements Serializable {

    private static final long serialVersionUID = -2603851990922608146L;

    /**
     * 登录方式
     */
    private Integer loginType;

    /**
     * 用户账号
     */
    private String email;

    /**
     * 用户密码
     */
    private String password;
}
