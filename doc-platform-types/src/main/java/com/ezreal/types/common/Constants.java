package com.ezreal.types.common;

import lombok.Getter;

/**
 * @author Ezreal
 * @Date 2024/2/2
 */
public class Constants {

    @Getter
    public enum ResponseCode {
        SUCCESS("0000", "成功"),
        UN_ERROR("0001", "未知失败"),
        LOGIN_FAIL("0002", "账号或者密码错误"),
        UN_LOGIN("0003", "用户未登录"),
        ERROR_PARAMS("0004", "参数错误"),
        USER_EXIST("0005", "用户已经存在");

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
