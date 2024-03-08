package com.ezreal.domain.user.model.aggregates;

import lombok.Data;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
@Data
public class UserLoginAggregate {

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
