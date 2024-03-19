package com.ezreal.domain.patient.model.entity;

import lombok.Data;

/**
 * @author Ezreal
 * @Date 2024/3/19
 */
@Data
public class PatientQueryEntity {

    private Integer pageNo;

    private Integer pageSize;

    private String name;

    private Integer departmentId;
}
