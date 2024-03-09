package com.ezreal.domain.doctor.model.resp;

import com.ezreal.domain.doctor.model.entity.DoctorEntity;
import lombok.Data;

import java.util.List;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class DoctorQueryListResponse {

    private List<DoctorEntity> doctorEntityList;

    private Long total;
}
