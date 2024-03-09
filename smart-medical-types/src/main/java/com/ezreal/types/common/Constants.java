package com.ezreal.types.common;

import lombok.AllArgsConstructor;
import lombok.Data;
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
        USER_EXIST("0005", "用户已经存在"),
        UPDATE_FAIL("0006", "更新失败"),
        PATIENT_NOT_FOUND("0007", "患者不存在"),
        DOCTOR_NOT_FOUND("0008", "医生不存在");

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

    @Getter
    @AllArgsConstructor
    public enum DepartmentType {

        NEI_KE(1, "内科"),
        WAI_KE(2, "外科"),
        PIFU_KE(3, "皮肤科")
        ;

        private final int code;

        private final String info;

        public static DepartmentType getByCode(int code) {
            for (DepartmentType value : values()) {
                if (value.getCode() == code) {
                    return value;
                }
            }
            return NEI_KE;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum UserType {

        MANAGER(0, "管理员"),
        DOCTOR(1, "医生"),
        PATIENT(2, "患者");
        private final int code;

        private final String info;

        public static UserType getByCode(int code) {
            UserType[] values = UserType.values();
            for (UserType value : values) {
                if (value.getCode() == code) {
                    return value;
                }
            }

            return MANAGER;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum Gender {

        MAN(0, "男"),
        WOMAN(1, "女");

        private final int code;
        private final String info;

        public static Gender getByCode(int code) {
            Gender[] values = Gender.values();
            for (Gender value : values) {
                if (value.getCode() == code) {
                    return value;
                }
            }

            return MAN;
        }
    }
}
