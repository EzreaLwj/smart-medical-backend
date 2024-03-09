package com.ezreal.domain.patient.model.entity;

import lombok.Data;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class PatientHeathMonitorEntity {

    /**
     * 血氧检测
     */
    private String bloodOxygen;

    /**
     * 体重检测
     */
    private String weight;

    /**
     * 温度检测
     */
    private String temperature;

    /**
     * 脉搏检测
     */
    private String pulse;
}
