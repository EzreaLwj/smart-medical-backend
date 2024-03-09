package com.ezreal.infrastructure.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 医生信息表
 * @TableName medical_doctor
 */
@TableName(value ="medical_doctor")
@Data
public class MedicalDoctor implements Serializable {
    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 医生姓名
     */
    private String name;

    /**
     * 性别 0-男,1-女
     */
    private Integer gender;

    /**
     * 医生头衔/职位
     */
    private String position;

    /**
     * 科室id
     */
    private Integer department;

    /**
     * 医生简介
     */
    private String description;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}