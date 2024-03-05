package com.ezreal.domain.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ezreal
 * @Date 2024/2/5
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterEntity {

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 个人邮箱
     */
    private String email;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;
}
