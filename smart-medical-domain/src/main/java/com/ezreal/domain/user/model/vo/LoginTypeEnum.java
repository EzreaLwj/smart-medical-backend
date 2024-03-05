package com.ezreal.domain.user.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {

    PASSWORD("PASSWORD"),
    EMAIL("EMAIL");

    private final String type;

}
