package com.ezreal.infrastructure.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 患者健康数据检测表
 * @TableName medical_monitor_record
 */
@TableName(value ="medical_monitor_record")
@Data
public class MedicalMonitorRecord implements Serializable {
    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 记录id
     */
    private Long recordId;

    /**
     * 患者id
     */
    private Long patientId;

    /**
     * 身体检测数据
     */
    private String healthMonitor;

    /**
     * 监测日期
     */
    private String monitorTime;

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