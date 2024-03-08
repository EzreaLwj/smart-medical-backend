package com.ezreal.domain.user.service.impl;

import com.auth0.jwt.interfaces.Claim;
import com.ezreal.domain.user.model.aggregates.UserLoginAggregate;
import com.ezreal.domain.user.model.entity.DocPlatformUserEntity;
import com.ezreal.domain.user.model.entity.UserAuthEntity;
import com.ezreal.domain.user.repository.MedicalUserRepository;
import com.ezreal.domain.user.service.MedicalUserService;
import com.ezreal.types.common.Constants;
import com.ezreal.types.exception.BusinessException;
import com.ezreal.types.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
@Slf4j
@Service
public class MedicalUserServiceImpl implements MedicalUserService {

    @Resource
    private MedicalUserRepository userRepository;

    @Override
    public UserAuthEntity login(UserLoginAggregate aggregate) {
        Integer loginType = aggregate.getLoginType();
        String email = aggregate.getEmail();
        String password = aggregate.getPassword();
        log.info("用户登录：{}, type:{}", email, loginType);
        return userRepository.checkLoginByEmail(email, password, loginType);
    }

    @Override
    public DocPlatformUserEntity getLoginState(String token) {
        boolean verify = JwtUtils.verify(token);
        if (!verify) {
            log.error("token失效");
            throw new BusinessException(Constants.ResponseCode.UN_LOGIN);
        }

        Map<String, Claim> claimMap = JwtUtils.decode(token);
        String userId = claimMap.get("userId").asString();
        DocPlatformUserEntity userEntity = userRepository.queryUserInfo(Long.valueOf(userId));
        return userEntity;
    }

}
