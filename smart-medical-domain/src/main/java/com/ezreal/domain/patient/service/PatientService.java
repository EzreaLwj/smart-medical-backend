package com.ezreal.domain.patient.service;

import com.ezreal.domain.patient.model.aggregates.PatientBaseInfoAggregate;
import com.ezreal.domain.patient.model.aggregates.PatientMonitorAggregate;
import com.ezreal.domain.patient.model.aggregates.ReserveAggregate;
import com.ezreal.domain.patient.model.entity.PatientInfoList;
import com.ezreal.domain.patient.model.entity.PatientMonitorRecordList;
import com.ezreal.domain.patient.model.request.PatientQueryRequest;
import com.ezreal.types.common.Response;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
public interface PatientService {

    Response<String> addPatientInfo(PatientBaseInfoAggregate baseInfoAggregate);

    Response<String> addPatientHealthMonitorInfo(PatientMonitorAggregate patientMonitorAggregate);

    PatientMonitorRecordList queryListHeathMonitorRecords(Long userId);

    Response<String> reserveDoctor(ReserveAggregate reserveAggregate);

    Response<PatientInfoList> queryPatientInfo(PatientQueryRequest patientQueryRequest);
}
