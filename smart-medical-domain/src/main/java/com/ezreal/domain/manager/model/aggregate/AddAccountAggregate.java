package com.ezreal.domain.manager.model.aggregate;

import lombok.Data;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class AddAccountAggregate {

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
     * 0-管理员 1-病人 2-患者
     */
    private Integer type;
}