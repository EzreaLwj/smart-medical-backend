package com.ezreal.infrastructure.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ezreal.infrastructure.po.MedicalReservation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Ezreal
* @description 针对表【medical_reservation(预约信息记录表)】的数据库操作Mapper
* @createDate 2024-03-09 15:38:28
* @Entity com.ezreal.infrastructure.po.MedicalReservation
*/
@Mapper
public interface MedicalReservationMapper extends BaseMapper<MedicalReservation> {
    int insertSelective(MedicalReservation medicalReservation);
}




