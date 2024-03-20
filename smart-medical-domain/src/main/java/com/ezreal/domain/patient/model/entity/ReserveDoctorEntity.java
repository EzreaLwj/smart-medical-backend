package com.ezreal.domain.patient.model.entity;

import lombok.Data;

/**
 * @author Ezreal
 * @Date 2024/3/20
 */
@Data
public class ReserveDoctorEntity {

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
    private String gender;

    /**
     * 医生头衔/职位
     */
    private String position;

    /**
     * 科室id
     */
    private String department;

    /**
     * 医生简介
     */
    private String description;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 是否已经预约
     */
    private boolean isReserved;

}
