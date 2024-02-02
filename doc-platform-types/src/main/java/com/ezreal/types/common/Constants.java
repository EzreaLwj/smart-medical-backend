package com.ezreal.types.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Ezreal
 * @Date 2024/2/2
 */
public class Constants {

    @Getter
    public enum ResponseCode {
        SUCCESS("0000", "成功"),
        UN_ERROR("0001", "未知失败");

        private final String code;
        private final String info;

        ResponseCode(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }
}
