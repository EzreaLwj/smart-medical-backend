package com.ezreal.infrastructure.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ezreal.infrastructure.po.MedicalMonitorRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Ezreal
* @description 针对表【medical_monitor_record(患者健康数据检测表)】的数据库操作Mapper
* @createDate 2024-03-09 14:09:54
* @Entity com.ezreal.infrastructure.po.MedicalMonitorRecord
*/
@Mapper
public interface MedicalMonitorRecordMapper extends BaseMapper<MedicalMonitorRecord> {

    MedicalMonitorRecord selectByPatientIdAndMonitorTime(@Param("patientId") Long patientId, @Param("monitorTime") String monitorTime);

    int insertSelective(MedicalMonitorRecord medicalMonitorRecord);

    int updateHealthMonitorByPatientIdAndMonitorTime(@Param("healthMonitor") String healthMonitor, @Param("patientId") Long patientId, @Param("monitorTime") String monitorTime);

    List<MedicalMonitorRecord> selectByPatientId(@Param("patientId") Long patientId, @Param("limit") Integer limit);
}




