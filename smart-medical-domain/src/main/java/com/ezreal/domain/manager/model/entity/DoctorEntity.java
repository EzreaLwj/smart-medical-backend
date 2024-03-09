package com.ezreal.domain.manager.model.entity;

import lombok.Data;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class DoctorEntity {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 医生姓名
     */
    private String name;

    /**
     * 性别 0-男,1-女
     */
    private Integer gender;

    /**
     * 医生头衔/职位
     */
    private String position;

    /**
     * 科室id
     */
    private Integer department;

    /**
     * 医生简介
     */
    private String description;

    /**
     * 手机号码
     */
    private String phone;
}
