package com.ezreal.trigger.dto.doctor;

import com.ezreal.domain.doctor.model.entity.DoctorEntity;
import lombok.Data;

import java.util.List;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class QueryDoctorResponse {

    private Long total;

    private List<DoctorEntity> doctorEntityList;
}
