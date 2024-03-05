package com.ezreal.trigger.dto;

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
    private String loginType;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户密码
     */
    private String password;
}
