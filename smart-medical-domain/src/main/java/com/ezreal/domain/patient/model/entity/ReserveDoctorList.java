package com.ezreal.domain.patient.model.entity;

import lombok.Data;

import java.util.List;

/**
 * @author Ezreal
 * @Date 2024/3/20
 */
@Data
public class ReserveDoctorList {

    private List<ReserveDoctorEntity> reserveDoctorEntityList;

    private Long total;
}
