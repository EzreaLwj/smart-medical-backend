package com.ezreal.domain.doctor.service;

import com.ezreal.domain.doctor.model.resp.DoctorQueryListResponse;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
public interface DoctorService {

    DoctorQueryListResponse queryDoctorList(Integer pageNo, Integer pageSize);

}
