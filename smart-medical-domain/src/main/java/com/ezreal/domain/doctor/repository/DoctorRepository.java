package com.ezreal.domain.doctor.repository;

import com.ezreal.domain.doctor.model.entity.DoctorEntity;

import java.util.List;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
public interface DoctorRepository {

    List<DoctorEntity> queryDoctorList(Integer pageNo, Integer pageSize);

    Long queryDoctorListTotal();

    DoctorEntity queryDoctorEntity(Long userId);

}
