package java.com.ezreal.trigger.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
@Data
public class LoginResponseDTO implements Serializable {

    private static final long serialVersionUID = -837652264428444885L;

    private String userId;

    private String token;
}
