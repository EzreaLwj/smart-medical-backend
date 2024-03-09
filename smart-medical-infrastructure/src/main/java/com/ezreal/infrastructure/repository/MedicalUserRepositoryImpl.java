package com.ezreal.infrastructure.repository;

import com.ezreal.domain.user.model.entity.DocPlatformUserEntity;
import com.ezreal.domain.user.model.entity.UserAuthEntity;
import com.ezreal.domain.user.repository.MedicalUserRepository;
import com.ezreal.infrastructure.mapper.MedicalUserMapper;
import com.ezreal.infrastructure.po.MedicalUser;
import com.ezreal.types.common.Constants;
import com.ezreal.types.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author Ezreal
 * @Date 2024/2/3
 */
@Slf4j
@Repository
public class MedicalUserRepositoryImpl implements MedicalUserRepository {

    @Resource
    private MedicalUserMapper userMapper;

    @Override
    public UserAuthEntity checkLoginByEmail(String email, String password, Integer type) {
        MedicalUser medicalUser = userMapper.selectByEmailAndPasswordAndType(email, password, type);
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        return getUserAuthEntity(email, medicalUser, userAuthEntity);
    }

    private UserAuthEntity getUserAuthEntity(String account, MedicalUser medicalUser, UserAuthEntity userAuthEntity) {
        if (medicalUser == null) {
            userAuthEntity.setCode(Constants.ResponseCode.LOGIN_FAIL.getCode());
            userAuthEntity.setInfo(Constants.ResponseCode.LOGIN_FAIL.getInfo());
            return userAuthEntity;
        }
        userAuthEntity.setCode(Constants.ResponseCode.SUCCESS.getCode());
        userAuthEntity.setInfo(Constants.ResponseCode.SUCCESS.getInfo());
        HashMap<String, String> claimMap = new HashMap<>();
        claimMap.put("userId", String.valueOf(medicalUser.getUserId()));
        claimMap.put("type", String.valueOf(medicalUser.getType()));
        String token = JwtUtils.encode(account, claimMap, 10);
        userAuthEntity.setToken(token);
        userAuthEntity.setType(medicalUser.getType());
        userAuthEntity.setUserId(medicalUser.getUserId());
        return userAuthEntity;
    }


    @Override
    public DocPlatformUserEntity queryUserInfo(Long userId) {
        MedicalUser medicalUser = userMapper.selectByUserId(userId);
        DocPlatformUserEntity userEntity = new DocPlatformUserEntity();
        userEntity.setUserId(medicalUser.getUserId());
        userEntity.setAvatar(medicalUser.getAvatar());
        userEntity.setPhone(medicalUser.getPhone());
        userEntity.setPassword(medicalUser.getPassword());
        userEntity.setEmail(medicalUser.getEmail());
        userEntity.setType(medicalUser.getType());
        userEntity.setAvatar(medicalUser.getAvatar());
        return userEntity;
    }

}
