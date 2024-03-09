package com.ezreal.domain.patient.service.impl;

import com.ezreal.domain.patient.model.aggregates.PatientBaseInfoAggregate;
import com.ezreal.domain.patient.model.aggregates.PatientMonitorAggregate;
import com.ezreal.domain.patient.model.aggregates.ReserveAggregate;
import com.ezreal.domain.patient.model.entity.PatientHeathMonitorEntity;
import com.ezreal.domain.patient.model.entity.PatientMonitorRecordList;
import com.ezreal.domain.patient.repository.PatientRepository;
import com.ezreal.domain.patient.service.PatientService;
import com.ezreal.types.common.Response;
import com.ezreal.types.common.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Service
public class PatientServiceImpl implements PatientService {

    private final int days = 5;

    @Resource
    private PatientRepository patientRepository;

    @Override
    public Response<String> addPatientInfo(PatientBaseInfoAggregate baseInfoAggregate) {
        patientRepository.addPatientInfo(baseInfoAggregate.getUserId(), baseInfoAggregate.getType(), baseInfoAggregate.getPatientInfoEntity());
        return ResultUtils.success("成功提交患者信息");
    }

    @Override
    public Response<String> addPatientHealthMonitorInfo(PatientMonitorAggregate patientMonitorAggregate) {
        patientRepository.addPatientMonitorInfo(patientMonitorAggregate.getUserId(), patientMonitorAggregate.getType(), patientMonitorAggregate.getHeathMonitor());
        return ResultUtils.success("成功提交患者检测信息");
    }

    @Override
    public PatientMonitorRecordList queryListHeathMonitorRecords(Long userId) {
        List<PatientHeathMonitorEntity> patientHeathMonitorEntityList
                = patientRepository.queryPatientMonitorRecords(userId, days);

        PatientMonitorRecordList patientMonitorRecordList = new PatientMonitorRecordList();
        patientMonitorRecordList.setDays(days);
        patientMonitorRecordList.setPatientHeathMonitorEntities(patientHeathMonitorEntityList);
        return patientMonitorRecordList;
    }

    @Override
    public Response<String> reserveDoctor(ReserveAggregate reserveAggregate) {
        patientRepository.reserveDoctor(reserveAggregate);
        return ResultUtils.success("预约成功");
    }

}