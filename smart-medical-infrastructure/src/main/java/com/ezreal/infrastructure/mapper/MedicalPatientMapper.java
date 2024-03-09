package com.ezreal.infrastructure.mapper;

import com.ezreal.infrastructure.po.MedicalPatient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Ezreal
* @description 针对表【medical_patient(患者信息表)】的数据库操作Mapper
* @createDate 2024-03-09 00:38:17
* @Entity com.ezreal.infrastructure.po.MedicalPatient
*/
@Mapper
public interface MedicalPatientMapper extends BaseMapper<MedicalPatient> {

}




