package com.ezreal.trigger.dto.manager;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class AddDoctorInfoRequest implements Serializable {

    private static final long serialVersionUID = -1323872924573486351L;

    /**
     * 个人邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 手机号码
     */
    @NotBlank(message = "手机不能为空")
    private String phone;

    /**
     * 0-管理员 1-医生 2-患者
     */
    @NotBlank(message = "类型不能为空")
    private Integer type;

    /**
     * 医生姓名
     */
    @NotBlank(message = "医生姓名不能为空")
    private String name;

    /**
     * 性别 0-男,1-女
     */
    @NotNull(message = "医生性别不能为空")
    private Integer gender;

    /**
     * 医生头衔/职位
     */
    @NotBlank(message = "医生头衔不能为空")
    private String position;

    /**
     * 科室id
     */
    @NotNull(message = "医生部门id不能为空")
    private Integer department;

    /**
     * 医生简介
     */
    @NotBlank(message = "医生简介不能为空")
    private String description;

}
