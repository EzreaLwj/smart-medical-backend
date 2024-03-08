package com.ezreal.infrastructure.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户登录表
 * @TableName medical_user
 */
@TableName(value ="medical_user")
@Data
public class MedicalUser implements Serializable {
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
     * 个人邮箱
     */
    private String email;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 0-管理员 1-病人 2-患者
     */
    private Integer type;

    /**
     * 账号状态 0-正常 1-封禁
     */
    private Integer status;

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