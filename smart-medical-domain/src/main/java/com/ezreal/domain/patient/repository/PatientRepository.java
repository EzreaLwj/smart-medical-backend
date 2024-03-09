package com.ezreal.domain.patient.repository;

import com.ezreal.domain.patient.model.aggregates.ReserveAggregate;
import com.ezreal.domain.patient.model.entity.PatientHeathMonitorEntity;
import com.ezreal.domain.patient.model.entity.PatientInfoEntity;

import java.util.List;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
public interface PatientRepository {

    PatientInfoEntity queryPatientInfo(Long userId, Integer type);

    void addPatientInfo(Long userId, Integer type, PatientInfoEntity patientInfoEntity);

    void addPatientMonitorInfo(Long userId, Integer type, PatientHeathMonitorEntity patientHeathMonitorEntity);

    List<PatientHeathMonitorEntity> queryPatientMonitorRecords(Long patientId, int days);

    void reserveDoctor(ReserveAggregate reserveAggregate);
}
