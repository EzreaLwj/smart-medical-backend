package com.ezreal.domain.patient.model.aggregates;

import com.ezreal.domain.patient.model.entity.PatientInfoEntity;
import lombok.Data;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class PatientBaseInfoAggregate {

    private Integer type;

    private Long userId;

    private PatientInfoEntity patientInfoEntity;
}
