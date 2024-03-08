package com.ezreal.domain.user.repository;

import com.ezreal.domain.user.model.entity.DocPlatformUserEntity;
import com.ezreal.domain.user.model.entity.UserAuthEntity;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
public interface MedicalUserRepository {


    /**
     * 查询用户信息
     * @param userId 用户id
     * @return
     */
    DocPlatformUserEntity queryUserInfo(Long userId);

    /**
     * 通过邮箱登录
     * @param account
     * @param password
     * @return
     */
    UserAuthEntity checkLoginByEmail(String account, String password, Integer type);

}
