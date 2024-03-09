package com.ezreal.trigger.dto.patient;

import com.ezreal.domain.patient.model.entity.PatientHeathMonitorEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class AddMonitorInfoRequest {

    @NotNull(message = "用户类型不能为空")
    private Integer type;

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    private PatientHeathMonitorEntity patientHeathMonitorEntity;
}
