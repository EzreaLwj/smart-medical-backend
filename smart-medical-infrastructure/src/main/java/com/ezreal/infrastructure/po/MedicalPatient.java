package com.ezreal.infrastructure.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 患者信息表
 * @TableName medical_patient
 */
@TableName(value ="medical_patient")
@Data
public class MedicalPatient implements Serializable {
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
     * 患者姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 患者年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 患者身高,单位cm
     */
    private BigDecimal height;

    /**
     * 患者体重,单位kg
     */
    private BigDecimal weight;

    /**
     * 病因
     */
    private String sickReason;

    /**
     * 病史
     */
    private String sickHistory;

    /**
     * 科室id
     */
    private Integer department;

    /**
     * 身体检测数据
     */
    private String healthMonitor;

    /**
     * 家庭住址
     */
    private String location;

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