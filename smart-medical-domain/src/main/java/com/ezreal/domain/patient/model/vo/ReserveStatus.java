package com.ezreal.domain.patient.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Getter
@AllArgsConstructor
public enum ReserveStatus  {
    RESERVED(0, "已预约"),
    EFFECT(1, "生效中"),
    SUCCESS(2, "已完成");

    private final int code;

    private final String info;

}
