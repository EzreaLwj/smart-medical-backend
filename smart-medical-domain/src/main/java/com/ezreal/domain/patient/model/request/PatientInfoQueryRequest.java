package com.ezreal.domain.patient.model.request;

import lombok.Data;

/**
 * @author Ezreal
 * @Date 2024/4/22
 */
@Data
public class PatientInfoQueryRequest {

    private Long userId;

    private Integer type;

}
