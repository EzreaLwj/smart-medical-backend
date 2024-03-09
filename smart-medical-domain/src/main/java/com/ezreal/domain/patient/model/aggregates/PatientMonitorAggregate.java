package com.ezreal.domain.patient.model.aggregates;

import com.ezreal.domain.patient.model.entity.PatientHeathMonitorEntity;
import lombok.Data;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class PatientMonitorAggregate {

    private Long userId;

    private Integer type;

    private PatientHeathMonitorEntity heathMonitor;

}
