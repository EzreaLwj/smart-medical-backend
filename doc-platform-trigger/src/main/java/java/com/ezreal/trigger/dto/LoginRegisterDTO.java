package java.com.ezreal.trigger.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Ezreal
 * @Date 2024/2/5
 */
@Data
public class LoginRegisterDTO implements Serializable {

    private static final long serialVersionUID = -1121570302406635287L;

    /**
     * 用户昵称
     */
    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(name = "用户昵称")
    private String nickName;

    /**
     * 个人邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty(name = "用户邮箱")
    private String email;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(name = "用户密码")
    private String password;

    /**
     * 手机号码
     */
    @ApiModelProperty(name = "手机号码")
    private String phone;

}
