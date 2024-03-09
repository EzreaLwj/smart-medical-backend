package com.ezreal.infrastructure.repository;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.ezreal.domain.manager.model.aggregate.AddAccountAggregate;
import com.ezreal.domain.manager.model.entity.MedicalUserEntity;
import com.ezreal.domain.manager.repository.ManagerRepository;
import com.ezreal.infrastructure.mapper.MedicalUserMapper;
import com.ezreal.infrastructure.po.MedicalUser;
import com.ezreal.types.common.Constants;
import com.ezreal.types.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author Ezreal
 * @Date 2024/3/9
 */
@Slf4j
@Repository
public class ManagerRepositoryImpl implements ManagerRepository {

    @Resource
    private MedicalUserMapper medicalUserMapper;

    @Override
    public MedicalUserEntity addAccount(AddAccountAggregate addAccountAggregate) {

        String email = addAccountAggregate.getEmail();
        String password = addAccountAggregate.getPassword();
        Integer type = addAccountAggregate.getType();
        String phone = addAccountAggregate.getPhone();

        SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator();
        Long userId = snowflakeGenerator.next();

        MedicalUser medicalUser = new MedicalUser();
        medicalUser.setUserId(userId);
        medicalUser.setEmail(email);
        medicalUser.setPassword(password);
        medicalUser.setType(type);
        medicalUser.setPhone(phone);
        int insert = 0;
        try {
            insert = medicalUserMapper.insert(medicalUser);
        } catch (DuplicateKeyException e) {
            log.error("该账号已经存在, email:{}", email);
            throw new DuplicateKeyException("该账号已经存在");
        }

        if (insert == 0) {
            log.error("账号创建失败，info:{}", JSONUtil.toJsonStr(addAccountAggregate));
            throw new BusinessException(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
        }

        log.info("用户账号创建成功, email:{}, phone:{}, type:{}", email, phone, type);
        MedicalUserEntity medicalUserEntity = new MedicalUserEntity();
        medicalUserEntity.setUserId(userId);
        medicalUserEntity.setType(type);
        medicalUserEntity.setPhone(phone);
        medicalUserEntity.setPassword(password);
        medicalUserEntity.setEmail(email);
        return medicalUserEntity;

    }
}
