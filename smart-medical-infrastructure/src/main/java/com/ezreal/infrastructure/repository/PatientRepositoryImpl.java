package com.ezreal.infrastructure.repository;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.ezreal.domain.patient.model.aggregates.ReserveAggregate;
import com.ezreal.domain.patient.model.entity.*;
import com.ezreal.domain.patient.model.request.ReserveDoctorQueryRequest;
import com.ezreal.domain.patient.model.vo.ReserveStatus;
import com.ezreal.domain.patient.repository.PatientRepository;
import com.ezreal.infrastructure.mapper.*;
import com.ezreal.infrastructure.po.*;
import com.ezreal.types.common.Constants;
import com.ezreal.types.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Slf4j
@Repository
public class PatientRepositoryImpl implements PatientRepository {

    @Resource
    private MedicalPatientMapper patientMapper;

    @Resource
    private MedicalMonitorRecordMapper monitorRecordMapper;

    @Resource
    private MedicalReservationMapper reservationMapper;

    @Resource
    private MedicalUserMapper medicalUserMapper;

    @Resource
    private MedicalDoctorMapper doctorMapper;

    @Override
    public PatientQueryInfoEntity queryPatientInfo(Long userId, Integer type) {
        MedicalPatient medicalPatient = patientMapper.selectByUserId(userId);
        if (medicalPatient == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        PatientQueryInfoEntity patientInfoEntity = new PatientQueryInfoEntity();
        patientInfoEntity.setName(medicalPatient.getName());
        patientInfoEntity.setEmail(medicalPatient.getEmail());
        patientInfoEntity.setAge(medicalPatient.getAge());
        patientInfoEntity.setBirthday(simpleDateFormat.format(medicalPatient.getBirthday()));
        patientInfoEntity.setHeight(medicalPatient.getHeight());
        patientInfoEntity.setWeight(medicalPatient.getWeight());
        patientInfoEntity.setSickReason(medicalPatient.getSickReason());
        patientInfoEntity.setSickHistory(medicalPatient.getSickHistory());
        patientInfoEntity.setDepartmentName(Constants.DepartmentType.getByCode(medicalPatient.getDepartment()).getInfo());
        patientInfoEntity.setHealthMonitor(medicalPatient.getHealthMonitor());
        patientInfoEntity.setLocation(medicalPatient.getLocation());
        return patientInfoEntity;
    }

    @Override
    public void addPatientInfo(Long userId, Integer type, PatientInfoEntity patientInfoEntity) {

        MedicalUser medicalUser = medicalUserMapper.selectByUserId(userId);
        if (medicalUser == null) {
            throw new BusinessException(Constants.ResponseCode.PATIENT_NOT_FOUND);
        }

        MedicalPatient medicalPatient = new MedicalPatient();
        medicalPatient.setUserId(userId);
        medicalPatient.setName(patientInfoEntity.getName());
        medicalPatient.setEmail(medicalUser.getEmail());
        medicalPatient.setAge(patientInfoEntity.getAge());
        medicalPatient.setBirthday(patientInfoEntity.getBirthday());
        medicalPatient.setHeight(patientInfoEntity.getHeight());
        medicalPatient.setWeight(patientInfoEntity.getWeight());
        medicalPatient.setSickReason(patientInfoEntity.getSickReason());
        medicalPatient.setSickHistory(patientInfoEntity.getSickHistory());
        medicalPatient.setDepartment(patientInfoEntity.getDepartment());
        medicalPatient.setHealthMonitor(patientInfoEntity.getHealthMonitor());
        medicalPatient.setLocation(patientInfoEntity.getLocation());

        PatientQueryInfoEntity entity = queryPatientInfo(userId, type);
        if (entity == null) {
            patientMapper.insertSelective(medicalPatient);
            log.info("增加病人信息, userId:{}", userId);
        } else {
            patientMapper.updateSelective(medicalPatient);
            log.info("更新病人信息, userId:{}", userId);
        }
    }

    @Override
    public void addPatientMonitorInfo(Long userId, Integer type, PatientHeathMonitorEntity patientHeathMonitorEntity) {
        PatientQueryInfoEntity entity = queryPatientInfo(userId, type);
        if (entity == null) {
            log.error("患者不存在, userId:{}", userId);
            throw new BusinessException(Constants.ResponseCode.PATIENT_NOT_FOUND);
        }

        if (patientHeathMonitorEntity != null) {
            // 更新患者表中的检测信息
            MedicalPatient medicalPatient = new MedicalPatient();
            medicalPatient.setUserId(userId);
            medicalPatient.setHealthMonitor(JSONUtil.toJsonStr(patientHeathMonitorEntity));
            patientMapper.updateSelective(medicalPatient);

            // 添加记录
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String monitorTime = sdf.format(new Date());
            MedicalMonitorRecord record = monitorRecordMapper.selectByPatientIdAndMonitorTime(userId, monitorTime);
            if (record != null) {
                monitorRecordMapper.updateHealthMonitorByPatientIdAndMonitorTime(JSONUtil.toJsonStr(patientHeathMonitorEntity), userId, monitorTime);
                log.info("更新监测记录，userId:{}, date:{}", userId, monitorTime);
            } else {
                MedicalMonitorRecord medicalMonitorRecord = new MedicalMonitorRecord();
                medicalMonitorRecord.setMonitorTime(monitorTime);
                SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator();
                Long recordId = snowflakeGenerator.next();
                medicalMonitorRecord.setRecordId(recordId);
                medicalMonitorRecord.setPatientId(userId);
                medicalMonitorRecord.setHealthMonitor(JSONUtil.toJsonStr(patientHeathMonitorEntity));
                monitorRecordMapper.insertSelective(medicalMonitorRecord);
                log.info("增加监测记录, userId:{}, date:{}", userId, monitorTime);
            }
        }
    }

    @Override
    public List<PatientHeathMonitorEntity> queryPatientMonitorRecords(Long patientId, int days) {
        List<MedicalMonitorRecord> medicalMonitorRecords = monitorRecordMapper.selectByPatientId(patientId, days);
        return medicalMonitorRecords.stream().map((medicalMonitorRecord -> JSONUtil.toBean(medicalMonitorRecord.getHealthMonitor(), PatientHeathMonitorEntity.class))).collect(Collectors.toList());
    }

    @Override
    public void reserveDoctor(ReserveAggregate reserveAggregate) {

        MedicalReservation medicalReservation = new MedicalReservation();
        medicalReservation.setPatientId(reserveAggregate.getPatientId());
        medicalReservation.setDoctorId(reserveAggregate.getDoctorId());
        medicalReservation.setStatus(ReserveStatus.RESERVED.getCode());
        medicalReservation.setReservation(reserveAggregate.getReservation());
        medicalReservation.setBeginTime(DateUtil.parseDateTime(reserveAggregate.getBeginTime()));
        medicalReservation.setEndTime(DateUtil.parseDateTime(reserveAggregate.getEndTime()));
        medicalReservation.setReservation(reserveAggregate.getReservation());
        log.info("预约成功，patientId:{}, doctorId:{}", reserveAggregate.getPatientId(), reserveAggregate.getDoctorId());
        reservationMapper.insertSelective(medicalReservation);
    }

    @Override
    public List<PatientQueryInfoEntity> queryPatientInfoList(PatientQueryEntity patientQueryRequest) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        patientQueryRequest.setPageNo((patientQueryRequest.getPageNo() - 1) * patientQueryRequest.getPageSize());
        List<MedicalPatient> medicalPatients = patientMapper.queryPatientInfoList(patientQueryRequest);
        return medicalPatients.stream().map((medicalPatient -> {
            PatientQueryInfoEntity patientQueryInfoEntity = new PatientQueryInfoEntity();
            patientQueryInfoEntity.setName(medicalPatient.getName());
            patientQueryInfoEntity.setEmail(medicalPatient.getEmail());
            patientQueryInfoEntity.setAge(medicalPatient.getAge());
            patientQueryInfoEntity.setBirthday(simpleDateFormat.format(medicalPatient.getBirthday()));
            patientQueryInfoEntity.setHeight(medicalPatient.getHeight());
            patientQueryInfoEntity.setWeight(medicalPatient.getWeight());
            patientQueryInfoEntity.setSickReason(medicalPatient.getSickReason());
            patientQueryInfoEntity.setSickHistory(medicalPatient.getSickHistory());
            patientQueryInfoEntity.setDepartmentName(Constants.DepartmentType.getByCode(medicalPatient.getDepartment()).getInfo());
            patientQueryInfoEntity.setHealthMonitor(medicalPatient.getHealthMonitor());
            patientQueryInfoEntity.setLocation(medicalPatient.getLocation());
            return patientQueryInfoEntity;
        })).collect(Collectors.toList());
    }

    @Override
    public Long queryPatientInfoTotal(PatientQueryEntity patientQueryRequest) {
        return patientMapper.queryPatientInfoListCount(patientQueryRequest);
    }

    @Override
    public List<ReserveDoctorEntity> queryReserveDoctorList(ReserveDoctorQueryRequest queryRequest) {
        Integer pageSize = queryRequest.getPageSize();
        Integer pageNo = queryRequest.getPageNo();
        pageNo = (pageNo - 1) * pageSize;

        List<MedicalDoctor> medicalDoctors = doctorMapper.selectAll(pageNo, pageSize);
        Set<Long> doctorIds = reservationMapper.queryByPatientId(queryRequest.getUserId()).stream().map(MedicalReservation::getDoctorId).collect(Collectors.toSet());
        List<ReserveDoctorEntity> reserveDoctorEntityList = medicalDoctors.stream().map(medicalDoctor -> {
            ReserveDoctorEntity reserveDoctorEntity = new ReserveDoctorEntity();
            reserveDoctorEntity.setUserId(medicalDoctor.getUserId());
            reserveDoctorEntity.setName(medicalDoctor.getName());
            reserveDoctorEntity.setGender(Constants.Gender.getByCode(medicalDoctor.getGender()).getInfo());
            reserveDoctorEntity.setPosition(medicalDoctor.getPosition());
            reserveDoctorEntity.setDepartment(Constants.DepartmentType.getByCode(medicalDoctor.getDepartment()).getInfo());
            reserveDoctorEntity.setDescription(medicalDoctor.getDescription());
            reserveDoctorEntity.setPhone(medicalDoctor.getPhone());
            reserveDoctorEntity.setReserved(doctorIds.contains(medicalDoctor.getUserId()));
            return reserveDoctorEntity;
        }).collect(Collectors.toList());
        return reserveDoctorEntityList;
    }

    @Override
    public Long queryReserveDoctorCount(ReserveDoctorQueryRequest queryRequest) {
        return doctorMapper.queryDoctorListTotal();
    }

}
