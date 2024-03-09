package com.ezreal.domain.manager.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class MedicalUserEntity {
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

    /**
     * 账号状态 0-正常 1-封禁
     */
    private Integer status;
}
