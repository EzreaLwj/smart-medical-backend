package com.ezreal.trigger.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Ezreal
 * @Date 2024/2/5
 */
@Data
@Builder
public class LoginStateDTO implements Serializable {

    private static final long serialVersionUID = 2948458736974505374L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 个人介绍
     */
    private String description;

    /**
     * 个人邮箱
     */
    private String email;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户住址
     */
    private String location;

}
