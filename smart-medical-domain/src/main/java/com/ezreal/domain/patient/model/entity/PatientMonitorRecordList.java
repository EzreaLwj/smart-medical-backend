package com.ezreal.domain.patient.model.entity;

import lombok.Data;

import java.util.List;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class PatientMonitorRecordList {

    private Integer days;

    private List<PatientHeathMonitorEntity> patientHeathMonitorEntities;

}
