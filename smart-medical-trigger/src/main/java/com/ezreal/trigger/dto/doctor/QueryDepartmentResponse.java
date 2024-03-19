package com.ezreal.trigger.dto.doctor;

import com.ezreal.domain.doctor.model.entity.DepartmentLabelEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ezreal
 * @Date 2024/3/19
 */
@Data
public class QueryDepartmentResponse implements Serializable {

    private static final long serialVersionUID = 6598504298436629952L;

    private List<DepartmentLabelEntity> departmentLabelEntityList;
}
