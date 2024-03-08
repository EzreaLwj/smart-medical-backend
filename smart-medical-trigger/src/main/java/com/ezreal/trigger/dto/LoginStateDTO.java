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
    private Long userId;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 个人邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户头像
     */
    private String avatar;


}
