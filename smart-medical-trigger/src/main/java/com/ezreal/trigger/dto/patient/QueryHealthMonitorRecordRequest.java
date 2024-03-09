package com.ezreal.trigger.dto.patient;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class QueryHealthMonitorRecordRequest implements Serializable {

    private static final long serialVersionUID = -6423337464958736189L;

    @NotNull(message = "用户id不能为空")
    private Long userId;
}
