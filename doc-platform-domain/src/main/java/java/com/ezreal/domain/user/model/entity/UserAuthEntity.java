package java.com.ezreal.domain.user.model.entity;

import lombok.Data;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
@Data
public class UserAuthEntity {

    private String userId;

    private String token;

    private String code;

    private String info;

}
