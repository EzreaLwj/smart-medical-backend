package com.ezreal.infrastructure.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 预约信息记录表
 * @TableName medical_reservation
 */
@TableName(value ="medical_reservation")
@Data
public class MedicalReservation implements Serializable {
    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 患者id
     */
    private Long patientId;

    /**
     * 医生id
     */
    private Long doctorId;

    /**
     * 预约状态：0-已预约 1-生效中 2-已完成 3-已取消
     */
    private Integer status;

    /**
     * 预约介绍
     */
    private String reservation;

    /**
     * 起始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

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