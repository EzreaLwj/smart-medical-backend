package com.ezreal.domain.user.service;

import com.ezreal.domain.user.model.aggregates.UserLoginAggregate;
import com.ezreal.domain.user.model.entity.DocPlatformUserEntity;
import com.ezreal.domain.user.model.entity.UserAuthEntity;
import com.ezreal.domain.user.model.entity.UserRegisterEntity;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
public interface DocPlatformUserService {

    UserAuthEntity login(UserLoginAggregate aggregate);

    DocPlatformUserEntity getLoginState(String token);

    UserAuthEntity register(UserRegisterEntity registerEntity);

    boolean updateUserInfo(DocPlatformUserEntity docPlatformUser);
}
