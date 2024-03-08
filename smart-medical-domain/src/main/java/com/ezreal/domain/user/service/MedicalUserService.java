package com.ezreal.domain.user.service;

import com.ezreal.domain.user.model.aggregates.UserLoginAggregate;
import com.ezreal.domain.user.model.entity.DocPlatformUserEntity;
import com.ezreal.domain.user.model.entity.UserAuthEntity;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
public interface MedicalUserService {

    UserAuthEntity login(UserLoginAggregate aggregate);

    DocPlatformUserEntity getLoginState(String token);

}
