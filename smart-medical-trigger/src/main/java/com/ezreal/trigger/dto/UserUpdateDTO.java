package com.ezreal.trigger.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Ezreal
 * @Date 2024/2/5
 */
@Data
public class UserUpdateDTO implements Serializable {

    private static final long serialVersionUID = -4676165073153759917L;

    /**
     * 用户id
     */
    @ApiModelProperty(name = "用户id")
    @NotBlank(message = "用户id不能为空")
    private String userId;

    /**
     * 用户昵称
     */
    @ApiModelProperty(name = "用户昵称")
    private String nickName;

    /**
     * 个人介绍
     */
    @ApiModelProperty(name = "个人介绍")
    private String description;

    /**
     * 个人邮箱
     */
    @ApiModelProperty(name = "个人邮箱")
    private String email;

    /**
     * 用户账号
     */
    @ApiModelProperty(name = "用户账号")
    private String account;

    /**
     * 用户密码
     */
    @ApiModelProperty(name = "用户密码")
    private String password;

    /**
     * 手机号码
     */
    @ApiModelProperty(name = "手机号码")
    private String phone;

    /**
     * 用户头像
     */
    @ApiModelProperty(name = "用户头像")
    private String avatar;

    /**
     * 用户住址
     */
    @ApiModelProperty(name = "用户住址")
    private String location;
}
