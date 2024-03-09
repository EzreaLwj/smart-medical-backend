package com.ezreal.trigger.dto.patient;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class ReserveDoctorRequest implements Serializable {

    private static final long serialVersionUID = -1427670638511931613L;

    /**
     * 患者id
     */
    private Long patientId;

    /**
     * 医生id
     */
    private Long doctorId;

    /**
     * 预约介绍
     */
    private String reservation;

    /**
     * 起始时间
     */
    private String beginTime;

    /**
     * 结束时间
     */
    private String endTime;
}
