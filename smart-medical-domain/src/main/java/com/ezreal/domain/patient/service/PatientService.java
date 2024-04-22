package com.ezreal.domain.patient.service;

import com.ezreal.domain.patient.model.aggregates.PatientBaseInfoAggregate;
import com.ezreal.domain.patient.model.aggregates.PatientMonitorAggregate;
import com.ezreal.domain.patient.model.aggregates.ReserveAggregate;
import com.ezreal.domain.patient.model.entity.*;
import com.ezreal.domain.patient.model.request.PatientInfoQueryRequest;
import com.ezreal.domain.patient.model.request.PatientQueryRequest;
import com.ezreal.domain.patient.model.request.ReserveDoctorQueryRequest;
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

    Response<ReserveDoctorList> queryReserveDoctorList(ReserveDoctorQueryRequest queryRequest);

    Response<PatientQueryInfoEntity> queryPatientDetailInfo(PatientInfoQueryRequest request);
}
