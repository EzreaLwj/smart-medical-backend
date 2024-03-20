package com.ezreal.domain.patient.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Ezreal
 * @Date 2024/3/20
 */
@Data
public class ReserveDoctorQueryRequest {

    @NotNull
    private Integer pageNo;

    @NotNull
    private Integer pageSize;

    private Long userId;

    private Integer type;
}
