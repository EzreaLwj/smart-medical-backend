package com.ezreal.trigger.dto.patient;

import com.ezreal.domain.patient.model.entity.MonitorData;
import com.ezreal.domain.patient.model.entity.PatientHeathMonitorEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class QueryHealthMonitorRecordResponse implements Serializable {

    private static final long serialVersionUID = 6030407300376091016L;

    private Integer days;

    private MonitorData oxygenData;

    private MonitorData weightData;

    private MonitorData pulseData;

    private MonitorData temperature;

    private Double bmi;
}
