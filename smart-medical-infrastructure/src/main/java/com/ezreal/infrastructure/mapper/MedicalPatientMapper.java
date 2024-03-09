package com.ezreal.infrastructure.mapper;
import java.util.List;

import com.ezreal.domain.patient.model.request.PatientQueryRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ezreal.infrastructure.po.MedicalPatient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Ezreal
* @description 针对表【medical_patient(患者信息表)】的数据库操作Mapper
* @createDate 2024-03-09 15:45:36
* @Entity com.ezreal.infrastructure.po.MedicalPatient
*/
@Mapper
public interface MedicalPatientMapper extends BaseMapper<MedicalPatient> {
    MedicalPatient selectByUserId(@Param("userId") Long userId);

    int insertSelective(MedicalPatient medicalPatient);

    int updateSelective(MedicalPatient medicalPatient);

    List<MedicalPatient> queryPatientInfoList(@Param("request") PatientQueryRequest patientQueryRequest);

    Long queryPatientInfoListCount(PatientQueryRequest patientQueryRequest);
}




