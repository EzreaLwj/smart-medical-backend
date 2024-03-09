package com.ezreal.infrastructure.mapper;
import java.util.List;

import com.ezreal.infrastructure.po.MedicalDoctor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Ezreal
* @description 针对表【medical_doctor(医生信息表)】的数据库操作Mapper
* @createDate 2024-03-09 16:55:49
* @Entity com.ezreal.infrastructure.po.MedicalDoctor
*/
@Mapper
public interface MedicalDoctorMapper extends BaseMapper<MedicalDoctor> {

    List<MedicalDoctor> selectAll(@Param("pageNo") Integer pageNo,
                                  @Param("pageSize") Integer pageSize);

    Long queryDoctorListTotal();
}




