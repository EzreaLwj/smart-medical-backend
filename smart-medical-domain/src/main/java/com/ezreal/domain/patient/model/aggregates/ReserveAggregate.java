package com.ezreal.domain.patient.model.aggregates;

import lombok.Data;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class ReserveAggregate {

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
