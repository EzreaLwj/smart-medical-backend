package com.ezreal.domain.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocPlatformUserEntity {

    /**
     * 用户id
     */
    private Long userId;

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

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 0-管理员 1-病人 2-患者
     */
    private Integer type;
}
