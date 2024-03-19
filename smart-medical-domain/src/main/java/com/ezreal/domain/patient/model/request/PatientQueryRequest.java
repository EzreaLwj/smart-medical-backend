package com.ezreal.domain.patient.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class PatientQueryRequest {

    @NotNull
    private Integer pageNo;

    @NotNull
    private Integer pageSize;

    private String name;

    private String departmentName;
}
