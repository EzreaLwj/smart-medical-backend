package com.ezreal.domain.patient.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ezreal.domain.patient.model.aggregates.PatientBaseInfoAggregate;
import com.ezreal.domain.patient.model.aggregates.PatientMonitorAggregate;
import com.ezreal.domain.patient.model.aggregates.ReserveAggregate;
import com.ezreal.domain.patient.model.entity.*;
import com.ezreal.domain.patient.model.request.PatientQueryRequest;
import com.ezreal.domain.patient.model.request.ReserveDoctorQueryRequest;
import com.ezreal.domain.patient.repository.PatientRepository;
import com.ezreal.domain.patient.service.PatientService;
import com.ezreal.types.common.Constants;
import com.ezreal.types.common.Response;
import com.ezreal.types.common.ResultUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

        Double bmi = patientRepository.queryBMI(userId);
        patientMonitorRecordList.setBmi(bmi);
        return patientMonitorRecordList;
    }

    @Override
    public Response<String> reserveDoctor(ReserveAggregate reserveAggregate) {
        patientRepository.reserveDoctor(reserveAggregate);
        return ResultUtils.success("预约成功");
    }

    @Override
    public Response<PatientInfoList> queryPatientInfo(PatientQueryRequest patientQueryRequest) {

        PatientQueryEntity queryEntity = new PatientQueryEntity();
        queryEntity.setPageNo(patientQueryRequest.getPageNo());
        queryEntity.setPageSize(patientQueryRequest.getPageSize());
        queryEntity.setName(patientQueryRequest.getName());
        if (StrUtil.isNotEmpty(patientQueryRequest.getDepartmentName())) {
            queryEntity.setDepartmentId(Constants.DepartmentType.getByName(patientQueryRequest.getDepartmentName()).getCode());
        }

        List<PatientQueryInfoEntity> patientQueryInfoEntities = patientRepository.queryPatientInfoList(queryEntity);
        Long total = patientRepository.queryPatientInfoTotal(queryEntity);
        PatientInfoList patientInfoList = new PatientInfoList();
        patientInfoList.setPatientQueryInfoEntities(patientQueryInfoEntities);
        patientInfoList.setTotal(total);
        return ResultUtils.success(patientInfoList);
    }

    @Override
    public Response<ReserveDoctorList> queryReserveDoctorList(ReserveDoctorQueryRequest queryRequest) {
        List<ReserveDoctorEntity> reserveDoctorEntityList = patientRepository.queryReserveDoctorList(queryRequest);
        Long total = patientRepository.queryReserveDoctorCount(queryRequest);
        ReserveDoctorList reserveDoctorList = new ReserveDoctorList();
        reserveDoctorList.setReserveDoctorEntityList(reserveDoctorEntityList);
        reserveDoctorList.setTotal(total);
        return ResultUtils.success(reserveDoctorList);
    }

}
