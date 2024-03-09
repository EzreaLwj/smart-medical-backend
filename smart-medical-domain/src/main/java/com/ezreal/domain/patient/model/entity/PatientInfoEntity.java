package com.ezreal.domain.patient.model.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class PatientInfoEntity {

    /**
     * 患者姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 患者年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 患者身高,单位cm
     */
    private BigDecimal height;

    /**
     * 患者体重,单位kg
     */
    private BigDecimal weight;

    /**
     * 病因
     */
    private String sickReason;

    /**
     * 病史
     */
    private String sickHistory;

    /**
     * 科室id
     */
    private Integer department;

    /**
     * 身体检测数据
     */
    private String healthMonitor;

    /**
     * 家庭住址
     */
    private String location;
}
