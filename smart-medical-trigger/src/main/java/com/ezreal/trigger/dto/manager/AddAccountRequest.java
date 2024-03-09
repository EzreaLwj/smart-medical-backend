package com.ezreal.trigger.dto.manager;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class AddAccountRequest implements Serializable {

    private static final long serialVersionUID = 5126927606232910126L;
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
     * 0-管理员 1-病人 2-患者
     */
    @NotBlank(message = "类型不能为空")
    private Integer type;
}
