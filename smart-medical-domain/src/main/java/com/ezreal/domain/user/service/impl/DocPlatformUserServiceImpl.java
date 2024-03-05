package com.ezreal.domain.user.service.impl;

import com.auth0.jwt.interfaces.Claim;
import com.ezreal.domain.user.model.aggregates.UserLoginAggregate;
import com.ezreal.domain.user.model.entity.DocPlatformUserEntity;
import com.ezreal.domain.user.model.entity.UserAuthEntity;
import com.ezreal.domain.user.model.entity.UserRegisterEntity;
import com.ezreal.domain.user.model.vo.LoginTypeEnum;
import com.ezreal.domain.user.repository.DocPlatformUserRepository;
import com.ezreal.domain.user.service.DocPlatformUserService;
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
public class DocPlatformUserServiceImpl implements DocPlatformUserService {

    @Resource
    private DocPlatformUserRepository userRepository;

    @Override
    public UserAuthEntity login(UserLoginAggregate aggregate) {
        String loginType = aggregate.getLoginType();
        String account = aggregate.getAccount();
        String password = aggregate.getPassword();
        log.info("用户登录：{}, type:{}", account, loginType);

        if (LoginTypeEnum.PASSWORD.getType().equals(loginType)) {
            return userRepository.checkLoginByAccount(account, password);

        }

        if (LoginTypeEnum.EMAIL.getType().equals(loginType)) {
            return userRepository.checkLoginByEmail(account, password);
        }

        log.error("当前登录方式不支持, account:{},loginType:{}", account, loginType);
        throw new BusinessException(Constants.ResponseCode.UN_ERROR);
    }

    @Override
    public boolean updateUserInfo(DocPlatformUserEntity docPlatformUser) {
        return userRepository.updateUserInfo(docPlatformUser);
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
        DocPlatformUserEntity userEntity = userRepository.queryUserInfo(userId);
        return userEntity;
    }

    @Override
    public UserAuthEntity register(UserRegisterEntity registerEntity) {
        UserAuthEntity userAuthEntity = userRepository.userRegister(registerEntity);
        return userAuthEntity;
    }

}
