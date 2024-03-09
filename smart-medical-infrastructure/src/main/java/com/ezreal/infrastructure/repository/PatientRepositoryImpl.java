package com.ezreal.infrastructure.repository;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.ezreal.domain.patient.model.aggregates.ReserveAggregate;
import com.ezreal.domain.patient.model.entity.PatientHeathMonitorEntity;
import com.ezreal.domain.patient.model.entity.PatientInfoEntity;
import com.ezreal.domain.patient.model.vo.ReserveStatus;
import com.ezreal.domain.patient.repository.PatientRepository;
import com.ezreal.infrastructure.mapper.MedicalMonitorRecordMapper;
import com.ezreal.infrastructure.mapper.MedicalPatientMapper;
import com.ezreal.infrastructure.mapper.MedicalReservationMapper;
import com.ezreal.infrastructure.po.MedicalMonitorRecord;
import com.ezreal.infrastructure.po.MedicalPatient;
import com.ezreal.infrastructure.po.MedicalReservation;
import com.ezreal.types.common.Constants;
import com.ezreal.types.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    @Override
    public PatientInfoEntity queryPatientInfo(Long userId, Integer type) {
        MedicalPatient medicalPatient = patientMapper.selectByUserId(userId);

        PatientInfoEntity patientInfoEntity = new PatientInfoEntity();
        patientInfoEntity.setName(medicalPatient.getName());
        patientInfoEntity.setEmail(medicalPatient.getEmail());
        patientInfoEntity.setAge(medicalPatient.getAge());
        patientInfoEntity.setBirthday(medicalPatient.getBirthday());
        patientInfoEntity.setHeight(medicalPatient.getHeight());
        patientInfoEntity.setWeight(medicalPatient.getWeight());
        patientInfoEntity.setSickReason(medicalPatient.getSickReason());
        patientInfoEntity.setSickHistory(medicalPatient.getSickHistory());
        patientInfoEntity.setDepartment(medicalPatient.getDepartment());
        patientInfoEntity.setHealthMonitor(medicalPatient.getHealthMonitor());
        patientInfoEntity.setLocation(medicalPatient.getLocation());
        return patientInfoEntity;
    }

    @Override
    public void addPatientInfo(Long userId, Integer type, PatientInfoEntity patientInfoEntity) {

        MedicalPatient medicalPatient = new MedicalPatient();
        medicalPatient.setUserId(userId);
        medicalPatient.setName(patientInfoEntity.getName());
        medicalPatient.setEmail(patientInfoEntity.getEmail());
        medicalPatient.setAge(patientInfoEntity.getAge());
        medicalPatient.setBirthday(patientInfoEntity.getBirthday());
        medicalPatient.setHeight(patientInfoEntity.getHeight());
        medicalPatient.setWeight(patientInfoEntity.getWeight());
        medicalPatient.setSickReason(patientInfoEntity.getSickReason());
        medicalPatient.setSickHistory(patientInfoEntity.getSickHistory());
        medicalPatient.setDepartment(patientInfoEntity.getDepartment());
        medicalPatient.setHealthMonitor(patientInfoEntity.getHealthMonitor());
        medicalPatient.setLocation(patientInfoEntity.getLocation());

        PatientInfoEntity entity = queryPatientInfo(userId, type);
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
        PatientInfoEntity entity = queryPatientInfo(userId, type);
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
                medicalMonitorRecord.setRecordId(RandomUtil.randomLong());
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
        medicalReservation.setBeginTime(DateUtil.parseTime(reserveAggregate.getBeginTime()));
        medicalReservation.setEndTime(DateUtil.parseTime(reserveAggregate.getEndTime()));
        medicalReservation.setReservation(reserveAggregate.getReservation());
        log.info("预约成功，patientId:{}, doctorId:{}", reserveAggregate.getPatientId(), reserveAggregate.getDoctorId());
        reservationMapper.insertSelective(medicalReservation);
    }

}