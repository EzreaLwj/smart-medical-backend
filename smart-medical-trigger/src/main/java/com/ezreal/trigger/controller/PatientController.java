package com.ezreal.trigger.controller;

import com.ezreal.domain.patient.model.aggregates.PatientBaseInfoAggregate;
import com.ezreal.domain.patient.model.aggregates.PatientMonitorAggregate;
import com.ezreal.domain.patient.model.aggregates.ReserveAggregate;
import com.ezreal.domain.patient.model.entity.*;
import com.ezreal.domain.patient.model.request.PatientQueryRequest;
import com.ezreal.domain.patient.model.request.ReserveDoctorQueryRequest;
import com.ezreal.domain.patient.service.PatientService;
import com.ezreal.trigger.dto.patient.*;
import com.ezreal.types.common.Response;
import com.ezreal.types.common.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Api("患者模块")
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Resource
    private PatientService patientService;

    @GetMapping("/queryPatientInfo")
    public Response<PatientInfoList> queryPatientInfo(@Validated PatientQueryRequest patientQueryRequest) {
        return patientService.queryPatientInfo(patientQueryRequest);
    }

    @ApiOperation("添加基本信息")
    @PostMapping("/addBaseInfo")
    public Response<String> addPatientBaseInfo(@RequestBody AddPatientBaseInfoRequest baseInfoRequest) {

        PatientBaseInfoAggregate patientBaseInfoAggregate = new PatientBaseInfoAggregate();
        patientBaseInfoAggregate.setType(baseInfoRequest.getType());
        patientBaseInfoAggregate.setUserId(baseInfoRequest.getUserId());
        patientBaseInfoAggregate.setPatientInfoEntity(baseInfoRequest.getPatientInfoEntity());

        return patientService.addPatientInfo(patientBaseInfoAggregate);
    }

    @ApiOperation("添加患者检测数据")
    @PostMapping("/addPatientMonitorInfo")
    public Response<String> addPatientMonitorInfo(@RequestBody AddMonitorInfoRequest addMonitorInfoRequest) {

        PatientMonitorAggregate patientMonitorAggregate = new PatientMonitorAggregate();
        patientMonitorAggregate.setType(addMonitorInfoRequest.getType());
        patientMonitorAggregate.setUserId(addMonitorInfoRequest.getUserId());
        patientMonitorAggregate.setHeathMonitor(addMonitorInfoRequest.getPatientHeathMonitorEntity());

        return patientService.addPatientHealthMonitorInfo(patientMonitorAggregate);
    }

    @ApiOperation("查询患者检测数据")
    @GetMapping("/queryHealthMonitorRecord")
    public Response<QueryHealthMonitorRecordResponse> queryHealthMonitorRecord(QueryHealthMonitorRecordRequest queryHealthMonitorRecordRequest) {
        PatientMonitorRecordList patientMonitorRecordList = patientService.queryListHeathMonitorRecords(queryHealthMonitorRecordRequest.getUserId());

        QueryHealthMonitorRecordResponse response = new QueryHealthMonitorRecordResponse();
        response.setDays(patientMonitorRecordList.getDays());

        List<PatientHeathMonitorEntity> patientHeathMonitorEntities = patientMonitorRecordList.getPatientHeathMonitorEntities();

        List<String> times = patientHeathMonitorEntities.stream().map(PatientHeathMonitorEntity::getTime).collect(Collectors.toList());

        List<Double> bloodOxygens = patientHeathMonitorEntities.stream().map((entity) -> {
            String bloodOxygen = entity.getBloodOxygen();
            return Double.parseDouble(bloodOxygen);
        }).collect(Collectors.toList());

        MonitorData oxygenData = new MonitorData(bloodOxygens, "oxygen", times, "%");
        response.setOxygenData(oxygenData);

        List<Double> weights = patientHeathMonitorEntities.stream().map((entity) -> {
            String weight = entity.getWeight();
            return Double.parseDouble(weight);
        }).collect(Collectors.toList());

        MonitorData weightData = new MonitorData(weights, "weight", times, "kg");
        response.setWeightData(weightData);

        List<Double> pulses = patientHeathMonitorEntities.stream().map((entity) -> {
            String pulse = entity.getPulse();
            return Double.parseDouble(pulse);
        }).collect(Collectors.toList());

        MonitorData pulseData = new MonitorData(pulses, "weight", times, "次/分钟", 40.0, 120.0, 20.0);
        response.setPulseData(pulseData);

        List<Double> temperatures = patientHeathMonitorEntities.stream().map((entity) -> {
            String temperature = entity.getTemperature();
            return Double.parseDouble(temperature);
        }).collect(Collectors.toList());

        MonitorData temperatureData = new MonitorData(temperatures, "temperature", times, "℃", 34.0, 39.0, 1.0);
        response.setTemperature(temperatureData);

        return ResultUtils.success(response);
    }

    @ApiOperation("预约医生")
    @PostMapping("/reserveDoctor")
    public Response<String> reserveDoctor(@RequestBody ReserveDoctorRequest reserveDoctorRequest) {

        ReserveAggregate reserveAggregate = new ReserveAggregate();
        reserveAggregate.setPatientId(reserveDoctorRequest.getPatientId());
        reserveAggregate.setDoctorId(reserveDoctorRequest.getDoctorId());
        reserveAggregate.setReservation(reserveDoctorRequest.getReservation());
        reserveAggregate.setBeginTime(reserveDoctorRequest.getBeginTime());
        reserveAggregate.setEndTime(reserveDoctorRequest.getEndTime());

        return patientService.reserveDoctor(reserveAggregate);
    }

    @ApiOperation("查询预约医生")
    @GetMapping("/查询预约医生列表")
    public Response<ReserveDoctorList> queryReserveDoctorList(@Validated ReserveDoctorQueryRequest reserveDoctorQueryRequest) {
        return patientService.queryReserveDoctorList(reserveDoctorQueryRequest);
    }

}
