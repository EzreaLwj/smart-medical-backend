package com.ezreal.domain.doctor.service.impl;

import com.ezreal.domain.doctor.model.entity.DoctorEntity;
import com.ezreal.domain.doctor.model.resp.DoctorQueryListResponse;
import com.ezreal.domain.doctor.repository.DoctorRepository;
import com.ezreal.domain.doctor.service.DoctorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Service
public class DoctorServiceImpl implements DoctorService {

    @Resource
    private DoctorRepository doctorRepository;

    @Override
    public DoctorQueryListResponse queryDoctorList(Integer pageNo, Integer pageSize) {
        List<DoctorEntity> doctorEntities = doctorRepository.queryDoctorList(pageNo, pageSize);
        Long total = doctorRepository.queryDoctorListTotal();
        DoctorQueryListResponse response = new DoctorQueryListResponse();
        response.setDoctorEntityList(doctorEntities);
        response.setTotal(total);
        return response;
    }

}
