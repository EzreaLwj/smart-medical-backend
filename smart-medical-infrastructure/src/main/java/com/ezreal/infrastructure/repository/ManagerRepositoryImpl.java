package com.ezreal.infrastructure.repository;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.json.JSONUtil;
import com.ezreal.domain.manager.model.aggregate.AddAccountAggregate;
import com.ezreal.domain.manager.model.entity.DoctorEntity;
import com.ezreal.domain.manager.model.aggregate.AddDoctorInfoAggregate;
import com.ezreal.domain.manager.model.entity.MedicalUserEntity;
import com.ezreal.domain.manager.repository.ManagerRepository;
import com.ezreal.infrastructure.mapper.MedicalDoctorMapper;
import com.ezreal.infrastructure.mapper.MedicalUserMapper;
import com.ezreal.infrastructure.po.MedicalDoctor;
import com.ezreal.infrastructure.po.MedicalUser;
import com.ezreal.types.common.Constants;
import com.ezreal.types.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private MedicalDoctorMapper doctorMapper;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MedicalUserEntity addDoctorInfo(AddDoctorInfoAggregate addDoctorInfoAggregate) {

        // 创建账号
        AddAccountAggregate addAccountAggregate = new AddAccountAggregate();
        addAccountAggregate.setEmail(addDoctorInfoAggregate.getEmail());
        addAccountAggregate.setPassword(addDoctorInfoAggregate.getPassword());
        addAccountAggregate.setPhone(addDoctorInfoAggregate.getPhone());
        addAccountAggregate.setType(addDoctorInfoAggregate.getType());

        MedicalUserEntity userEntity = this.addAccount(addAccountAggregate);
        DoctorEntity doctorEntity = addDoctorInfoAggregate.getDoctorEntity();

        MedicalDoctor medicalDoctor = new MedicalDoctor();
        medicalDoctor.setUserId(userEntity.getUserId());
        medicalDoctor.setName(doctorEntity.getName());
        medicalDoctor.setGender(doctorEntity.getGender());
        medicalDoctor.setPosition(doctorEntity.getPosition());
        medicalDoctor.setDepartment(doctorEntity.getDepartment());
        medicalDoctor.setDescription(doctorEntity.getDescription());
        medicalDoctor.setPhone(userEntity.getPhone());
        doctorMapper.insertSelective(medicalDoctor);

        return userEntity;
    }
}
