package com.ezreal.domain.doctor.service;

import com.ezreal.domain.doctor.model.entity.DepartmentLabelEntity;
import com.ezreal.domain.doctor.model.resp.DoctorQueryListResponse;

import java.util.List;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
public interface DoctorService {

    DoctorQueryListResponse queryDoctorList(Integer pageNo, Integer pageSize);

    List<DepartmentLabelEntity> queryDepartmentList();
}
