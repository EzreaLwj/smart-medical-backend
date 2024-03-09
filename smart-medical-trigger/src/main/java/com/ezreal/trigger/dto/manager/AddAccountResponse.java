package com.ezreal.trigger.dto.manager;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Data
public class AddAccountResponse implements Serializable {

    private static final long serialVersionUID = -1034891992957567402L;

    private Long userId;

    private String email;

    private String password;
}
